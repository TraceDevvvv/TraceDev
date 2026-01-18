package com.example;

import com.example.presentation.DeleteDailyMenuView;
import com.example.presentation.DeleteDailyMenuController;
import com.example.application.DeleteDailyMenuInteractor;
import com.example.application.IDeleteDailyMenuUseCase;
import com.example.auth.AuthenticationServiceImpl;
import com.example.auth.IAuthenticationService;
import com.example.auth.IUserRepository;
import com.example.command.DailyMenuCommandFactory;
import com.example.infrastructure.DailyMenuRepositoryImpl;
import com.example.infrastructure.IDailyMenuRepository;
import com.example.auth.Credentials;
import com.example.domain.DayOfWeek;
import java.util.Scanner;

/**
 * Main application class to set up and run the system.
 * This class wires all components together and simulates the operator interaction.
 */
public class MainApp {
    public static void main(String[] args) {
        // Setup infrastructure
        IDailyMenuRepository repository = new DailyMenuRepositoryImpl(null);
        DailyMenuCommandFactory commandFactory = new DailyMenuCommandFactory(repository);

        // Setup authentication (simplified)
        IUserRepository userRepo = new IUserRepository() { /* dummy implementation */ };
        IAuthenticationService authService = new AuthenticationServiceImpl(userRepo);

        // Create controller
        DeleteDailyMenuController controller = new DeleteDailyMenuController(null, authService, null);
        // Create view
        DeleteDailyMenuView view = new DeleteDailyMenuView(controller);
        // Create interactor
        DeleteDailyMenuInteractor interactor = new DeleteDailyMenuInteractor(commandFactory, controller);
        // Wire controller
        controller = new DeleteDailyMenuController(interactor, authService, view);
        view = new DeleteDailyMenuView(controller);
        interactor = new DeleteDailyMenuInteractor(commandFactory, controller);

        // Simulate operator interaction
        System.out.println("=== Restaurant Operator System ===");
        view.enableDeleteFunctionality();
        view.shows7DaySelectionForm();

        // Simulate day selection (e.g., Monday)
        DayOfWeek selectedDay = DayOfWeek.MONDAY;
        view.selectDay(selectedDay);
        view.submitForm();
        view.submitDaySelection(selectedDay);

        // Simulate confirmation dialog
        view.showsConfirmationPopup();

        // Simulate operator confirmation
        Scanner scanner = new Scanner(System.in);
        System.out.print("Confirm deletion? (yes/no): ");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("yes")) {
            view.confirmDeletion();
            // Interactor proceeds with deletion
            interactor.proceedWithDeletion(selectedDay);
        } else {
            view.cancelDeletion();
        }

        scanner.close();
    }
}