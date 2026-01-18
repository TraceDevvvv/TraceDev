package com.example.menu;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Main class to demonstrate the daily menu deletion use case.
 * Sets up the application context, wires dependencies, and simulates user interaction.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("--- Starting Daily Menu Deletion Application Demo ---");

        // 1. Infrastructure Layer Setup (Mocks/Implementations)
        // Mock Authentication Service (REQ-R3)
        AuthenticationService mockAuthService = new AuthenticationService() {
            private boolean isAuthenticated = true; // Start authenticated for demo
            private Scanner authScanner = new Scanner(System.in);

            @Override
            public boolean isAuthenticated() {
                System.out.println("\n--- Authentication Check ---");
                System.out.print("Simulate authentication (type 'true' or 'false'): ");
                String input = authScanner.nextLine();
                this.isAuthenticated = Boolean.parseBoolean(input);
                System.out.println("Authentication status set to: " + isAuthenticated);
                return isAuthenticated;
            }
        };

        // Concrete DailyMenuRepository implementation
        DailyMenuDatabaseRepository dailyMenuRepository = new DailyMenuDatabaseRepository();

        // Audit Log Service
        AuditLogService auditLogService = new AuditLogService();

        // 2. Application Layer Setup
        MenuDeletionService menuDeletionService = new MenuDeletionService(dailyMenuRepository, auditLogService);

        // 3. Presentation Layer Setup
        // MenuView needs MenuController, MenuController needs MenuView.
        // To avoid circular dependency during constructor calls,
        // we can instantiate one, then pass its reference to the other via a setter.
        // Let's create MenuView first, then pass it to MenuController, then set controller back to view.
        // Alternatively, pass 'null' for the controller in MenuView and set it later.
        MenuView menuView = new MenuView(null); // Pass null initially, will set controller later
        MenuController menuController = new MenuController(menuDeletionService, menuView, mockAuthService);
        menuView.setController(menuController); // Now set the controller in the view

        // --- Simulate User Interaction ---

        Scanner mainScanner = new Scanner(System.in);

        // First scenario: Successful deletion
        System.out.println("\n--- Scenario 1: Successful Deletion ---");
        DailyMenuDatabaseRepository.SIMULATE_NETWORK_FAILURE = false; // Ensure no network failure
        menuController.handleDeleteMenuInitiation();
        // User interaction will be handled by MenuView's displayDeleteMenuForm method and subsequent callbacks

        System.out.println("\n--- End of Scenario 1 ---");
        System.out.println("Press Enter to continue to Scenario 2 (Network Failure)...");
        mainScanner.nextLine();

        // Second scenario: Deletion with network connection exception (REQ-R13)
        System.out.println("\n--- Scenario 2: Deletion with Simulated Network Failure ---");
        DailyMenuDatabaseRepository.SIMULATE_NETWORK_FAILURE = true; // Simulate network failure
        menuController.handleDeleteMenuInitiation();
        // User interaction will be handled by MenuView
        
        System.out.println("\n--- End of Scenario 2 ---");
        System.out.println("Press Enter to continue to Scenario 3 (Authentication Failure)...");
        mainScanner.nextLine();

        // Third scenario: Authentication Failure (REQ-R3)
        System.out.println("\n--- Scenario 3: Authentication Failure ---");
        // The mockAuthService will prompt for input, enter 'false'
        menuController.handleDeleteMenuInitiation();

        System.out.println("\n--- End of Scenario 3 ---");
        System.out.println("Press Enter to exit.");
        mainScanner.nextLine();


        // Clean up resources
        menuView.closeScanner();
        mainScanner.close();
        System.out.println("--- Application Demo Ended ---");
    }
}