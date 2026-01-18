package com.example.touristmgmt;

import java.util.Scanner;

/**
 * Main class to demonstrate the Tourist Management System.
 * This sets up the dependencies and simulates the user interaction flow
 * described in the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // --- Dependency Injection / Object Creation ---
        // Instantiate serv and repositories
        ITouristRepository touristRepository = new TouristRepositoryImpl();
        NotificationService notificationService = new NotificationService();
        ETOURService etourService = new ETOURService();
        AuthenticationService authenticationService = new AuthenticationService();
        TouristSearchService touristSearchService = new TouristSearchService();

        // Create UI and Controller, handling their circular dependency:
        // 1. Instantiate TouristManagementUI first, passing null for the controller initially.
        TouristManagementUI touristManagementUI = new TouristManagementUI(null, touristSearchService);

        // 2. Instantiate TouristDeletionController, passing the now-existing TouristManagementUI instance.
        TouristDeletionController touristDeletionController = new TouristDeletionController(
            touristRepository,
            notificationService,
            etourService,
            touristManagementUI, // Inject the UI into the controller
            authenticationService
        );

        // 3. Now, set the actual TouristDeletionController instance into the TouristManagementUI.
        // This resolves the circular dependency.
        touristManagementUI.setTouristDeletionController(touristDeletionController);


        System.out.println("--- Tourist Management System Simulation ---");
        System.out.println("Entry Condition: Agency Operator is logged in and has viewed the list of tourists.");

        // Simulate Agency Operator viewing the list of tourists
        touristManagementUI.displayAllTourists();

        Scanner mainScanner = new Scanner(System.in);
        String touristIdToDelete;

        while (true) {
            System.out.print("\nEnter Tourist ID to delete (e.g., T001, T002, T003) or 'exit' to quit: ");
            touristIdToDelete = mainScanner.nextLine().trim();

            if (touristIdToDelete.equalsIgnoreCase("exit")) {
                System.out.println("Exiting simulation.");
                break;
            }

            // AO -> UI : selectTourist(touristId : String)
            // UI -> UI : handleTouristSelectionForDeletion(touristId : String)
            touristManagementUI.handleTouristSelectionForDeletion(touristIdToDelete);

            // Display current state of tourists after operation to see changes
            touristManagementUI.displayAllTourists();
        }

        mainScanner.close();
    }
}