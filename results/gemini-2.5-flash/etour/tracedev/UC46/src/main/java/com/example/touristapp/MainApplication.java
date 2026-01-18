package com.example.touristapp;

import com.example.touristapp.controller.SearchPreferencesController;
import com.example.touristapp.dto.SearchPreferencesDTO;
import com.example.touristapp.infrastructure.DatabaseConnection;
import com.example.touristapp.repository.ISearchPreferencesRepository;
import com.example.touristapp.repository.SearchPreferencesRepositoryImpl;
import com.example.touristapp.service.AuthenticationService;
import com.example.touristapp.service.EditSearchPreferencesUseCase;
import com.example.touristapp.service.SystemNotifications;
import com.example.touristapp.view.SearchPreferencesView;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Main application class to demonstrate the interaction flow
 * based on the provided Class and Sequence Diagrams.
 * This class handles dependency injection and orchestrates the demo.\n */
public class MainApplication {

    public static void main(String[] args) {
        // --- Dependency Injection / Object Graph Assembly ---
        System.out.println("--- Application Startup: Assembling Components ---");

        DatabaseConnection dbConnection = new DatabaseConnection();
        ISearchPreferencesRepository searchPreferencesRepository = new SearchPreferencesRepositoryImpl(dbConnection);
        EditSearchPreferencesUseCase editSearchPreferencesUseCase = new EditSearchPreferencesUseCase(searchPreferencesRepository);
        AuthenticationService authenticationService = new AuthenticationService();
        SystemNotifications systemNotifications = new SystemNotifications();

        // Controller needs a view, and view needs a controller (circular dependency)
        // Instantiate them first, then set the cross-dependencies.
        SearchPreferencesController searchPreferencesController = new SearchPreferencesController(
                editSearchPreferencesUseCase,
                authenticationService
        );
        SearchPreferencesView searchPreferencesView = new SearchPreferencesView(searchPreferencesController, systemNotifications);

        // Resolve circular dependency by setting the view on the controller
        searchPreferencesController.setSearchPreferencesView(searchPreferencesView);

        System.out.println("--- Components Assembled. Starting Simulation ---");

        // --- Sequence Diagram Simulation ---
        String touristId = "T1001"; // Example tourist ID

        Scanner consoleScanner = new Scanner(System.in);
        String scenarioChoice;

        System.out.println("\n*** SIMULATING TOURIST EDITING SEARCH PREFERENCES ***");
        System.out.println("Tourist ID for this session: " + touristId);

        // Tourist -> View: requestEditPreferences()
        searchPreferencesView.requestEditPreferences(touristId);

        // After displayPreferencesForm, View internally calls getEditedPreferences() and submitEditedPreferences()
        // This is handled within the View's displayPreferencesForm method in this simulation
        // (to streamline the console interaction).
        // The View then calls controller.handleSubmit(touristId, formData)

        // After handleSubmit, Controller calls View.showConfirmationPrompt()
        // After showConfirmationPrompt, View internally collects user input and calls confirmUpdate()
        // which then calls controller.handleConfirm() or controller.handleCancel()

        System.out.println("\n--- SCENARIO 1: Successful Update ---");
        System.out.print("Simulate successful update? (yes/no): ");
        scenarioChoice = consoleScanner.nextLine();
        if (scenarioChoice.equalsIgnoreCase("yes")) {
            // The flow for successful update should have already happened if user confirmed 'yes' in View.showConfirmationPrompt.
            // We just let the previous interactive flow finish.
            System.out.println("Scenario 1 finished through interactive prompt.");
        } else {
            System.out.println("Skipping Scenario 1 (Successful Update).");
        }


        System.out.println("\n--- SCENARIO 2: Connection Interrupted during Save ---");
        System.out.print("Simulate connection interruption during save? (yes/no): ");
        scenarioChoice = consoleScanner.nextLine();
        if (scenarioChoice.equalsIgnoreCase("yes")) {
            System.out.println("\nSetting up for connection interruption scenario...");
            // Reset state (optional, just to show a clean flow for this scenario)
            System.out.println("Re-requesting preferences to start a new flow...");
            searchPreferencesView.requestEditPreferences(touristId); // Start a new interaction cycle

            System.out.println("\n[SIMULATION] Interrupting database connection before save attempt.");
            dbConnection.simulateConnectionInterruption(); // Interrupt connection

            // The View.showConfirmationPrompt will lead to controller.handleConfirm, which will hit the interrupted connection
            // if the user chooses 'yes'.
            System.out.println("Observe the error notification if you confirm the update now.");
            System.out.print("Confirm update for interrupted connection test? (yes/no): ");
            String confirmInterruption = consoleScanner.nextLine();
            if (confirmInterruption.equalsIgnoreCase("yes")) {
                // User input handled by SearchPreferencesView.showConfirmationPrompt -> SearchPreferencesView.confirmUpdate
                // This will trigger the controller.handleConfirm which then calls useCase.updatePreferences,
                // and repository.save will throw an exception.
                System.out.println("Scenario 2 finished through interactive prompt.");
            } else {
                searchPreferencesController.handleCancel(); // Manually cancel if user doesn't want to confirm the bad save
                System.out.println("Skipped confirmation for Scenario 2.");
            }
            dbConnection.restoreConnection(); // Restore for future operations if any
        } else {
            System.out.println("Skipping Scenario 2 (Connection Interrupted).");
        }

        System.out.println("\n--- SCENARIO 3: User Cancels Operation ---");
        System.out.print("Simulate user cancellation? (yes/no): ");
        scenarioChoice = consoleScanner.nextLine();
        if (scenarioChoice.equalsIgnoreCase("yes")) {
            System.out.println("\nRe-requesting preferences to start a new flow for cancellation scenario...");
            searchPreferencesView.requestEditPreferences(touristId); // Start a new interaction cycle

            System.out.print("Confirm update for cancellation test? (yes/no): ");
            String confirmCancellation = consoleScanner.nextLine();
            if (confirmCancellation.equalsIgnoreCase("no")) {
                // User input handled by SearchPreferencesView.showConfirmationPrompt -> SearchPreferencesView.confirmUpdate
                // This will trigger the controller.handleCancel.
                System.out.println("Scenario 3 finished through interactive prompt.");
            } else {
                System.out.println("Skipped cancellation for Scenario 3 (user confirmed instead).");
            }
        } else {
            System.out.println("Skipping Scenario 3 (User Cancels).");
        }


        System.out.println("\n--- Application Simulation Complete ---");
        searchPreferencesView.closeScanner(); // Close scanner opened in view
        consoleScanner.close(); // Close local scanner
    }
}