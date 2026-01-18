package com.example;

import com.example.application.AuthenticationService;
import com.example.application.ErrorHandlingService;
import com.example.application.MenuMapper;
import com.example.application.MenuService;
import com.example.application.ValidationService;
import com.example.domain.Credentials;
import com.example.domain.DayOfWeek;
import com.example.domain.MenuItem;
import com.example.infrastructure.ErrorLogger;
import com.example.infrastructure.MenuRepositoryImpl;
import com.example.presentation.ConfirmationView;
import com.example.presentation.DailyMenuFormView;
import com.example.presentation.MenuController;
import com.example.presentation.MenuEditFormView;
import com.example.presentation.MenuViewModel;
import com.example.presentation.NotificationView;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Main application class to wire up components and simulate the sequence diagram.
 */
public class Application {

    public static void main(String[] args) {
        // 1. Instantiate Infrastructure Layer
        ErrorLogger errorLogger = new ErrorLogger();
        MenuRepositoryImpl menuRepository = new MenuRepositoryImpl();

        // 2. Instantiate Application Layer
        ValidationService validationService = new ValidationService();
        MenuService menuService = new MenuService(menuRepository, validationService, errorLogger);
        MenuMapper menuMapper = new MenuMapper();
        ErrorHandlingService errorHandlingService = new ErrorHandlingService();
        AuthenticationService authenticationService = new AuthenticationService();

        // 3. Instantiate Presentation Layer Views
        DailyMenuFormView dailyMenuFormView = new DailyMenuFormView();
        MenuEditFormView menuEditFormView = new MenuEditFormView();
        ConfirmationView confirmationView = new ConfirmationView();
        NotificationView notificationView = new NotificationView();

        // 4. Instantiate Presentation Layer Controller with all dependencies (Dependency Injection)
        MenuController menuController = new MenuController(
                menuService, menuMapper, errorHandlingService, authenticationService,
                dailyMenuFormView, menuEditFormView, confirmationView, notificationView
        );

        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Starting Menu Management Application ---");

        // Simulate operator authentication (R3)
        System.out.println("\n--- Step 0: Operator Authentication ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        Credentials operatorCredentials = new Credentials(username, password);

        if (!menuController.authenticate(operatorCredentials)) {
            System.out.println("Application terminated due to failed authentication.");
            scanner.close();
            return;
        }

        // Simulate the main flow
        System.out.println("\n--- Step 1: Operator initiates menu editing ---");
        menuController.activateMenuEditing();

        System.out.println("\n--- Step 2: Operator selects a day (e.g., MONDAY) ---");
        DayOfWeek selectedDay = DayOfWeek.MONDAY;
        menuController.selectDay(selectedDay);

        // --- Simulate a network error during getMenuForDay ---
        System.out.println("\n--- Simulating network error during menu fetch for TUESDAY ---");
        menuRepository.setSimulateNetworkError(true); // Enable network error simulation
        menuController.selectDay(DayOfWeek.TUESDAY);
        menuRepository.setSimulateNetworkError(false); // Disable network error simulation for subsequent operations
        System.out.println("--- Network error simulation complete, retrying MONDAY flow ---");
        menuController.selectDay(selectedDay); // Continue with MONDAY as the error for TUESDAY would restart the flow


        System.out.println("\n--- Step 7: Operator edits the menu for " + selectedDay + " ---");
        // Simulate user input for updated menu
        MenuViewModel updatedMenuData = menuController.createSampleMenuViewModel(
                selectedDay,
                "Updated menu items for " + selectedDay,
                false // Not an empty menu
        );

        // Add an item that makes it invalid for the validation service later
        // updatedMenuData.getMenuItems().add(new MenuItem("", "Invalid item", 1.0, true)); // Uncomment to test validation failure

        System.out.println("\n--- Step 8: Operator submits the updated menu ---");
        menuController.submitDailyMenu(updatedMenuData);

        System.out.println("\n--- Step 12: Operator confirms the operation ---");
        menuController.confirmOperation();

        // --- Simulate a network error during saveConfirmedMenu ---
        System.out.println("\n--- Simulating network error during menu save for WEDNESDAY ---");
        selectedDay = DayOfWeek.WEDNESDAY;
        menuController.selectDay(selectedDay); // Select a new day
        MenuViewModel wednesdayMenuData = menuController.createSampleMenuViewModel(
                selectedDay,
                "Wednesday menu to be saved with error",
                false
        );
        menuController.submitDailyMenu(wednesdayMenuData);
        menuRepository.setSimulateNetworkError(true); // Enable network error simulation for save
        menuController.confirmOperation();
        menuRepository.setSimulateNetworkError(false); // Disable network error simulation

        // --- Simulate validation failure ---
        System.out.println("\n--- Simulating validation failure for THURSDAY ---");
        selectedDay = DayOfWeek.THURSDAY;
        menuController.selectDay(selectedDay); // Select a new day
        MenuViewModel invalidMenuData = menuController.createSampleMenuViewModel(
                selectedDay,
                "Invalid menu for " + selectedDay,
                true // An empty menu, which our ValidationService deems invalid
        );
        menuController.submitDailyMenu(invalidMenuData);


        System.out.println("\n--- Optional: Operator cancels operation for FRIDAY ---");
        selectedDay = DayOfWeek.FRIDAY;
        menuController.selectDay(selectedDay); // Select a new day
        // Simulate some editing, but then cancel
        MenuViewModel fridayMenuData = menuController.createSampleMenuViewModel(
                selectedDay,
                "Friday menu being edited",
                false
        );
        menuController.submitDailyMenu(fridayMenuData); // This would bring it to confirmation
        // Operator decides to cancel
        menuController.cancelOperation();


        System.out.println("\n--- Application simulation finished ---");
        scanner.close();
    }
}