package com.example.touristapp;

import com.example.touristapp.application.TouristAccountCommandService;
import com.example.touristapp.application.TouristAccountQueryService;
import com.example.touristapp.domain.AccountStatus;
import com.example.touristapp.domain.TouristAccount;
import com.example.touristapp.infrastructure.AuditLogService;
import com.example.touristapp.infrastructure.TouristAccountRepositoryAdapter;
import com.example.touristapp.ui.TouristAccountManagementUI;

import java.util.Scanner;

/**
 * Main class to demonstrate the Tourist Account Management application.
 * This sets up the dependency graph and simulates user interaction as per the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        // 1. Setup Infrastructure Layer (Persistence, Logging)
        TouristAccountRepositoryAdapter touristAccountRepositoryAdapter = new TouristAccountRepositoryAdapter();
        AuditLogService auditLogService = new AuditLogService();

        // Simulate some initial data
        touristAccountRepositoryAdapter.addAccountForTesting(new TouristAccount("TA001", "Alice Wonderland", AccountStatus.ENABLED));
        touristAccountRepositoryAdapter.addAccountForTesting(new TouristAccount("TA002", "Bob The Builder", AccountStatus.DISABLED));
        touristAccountRepositoryAdapter.addAccountForTesting(new TouristAccount("TA003", "Charlie Chaplin", AccountStatus.ENABLED));

        // 2. Setup Application Layer (Serv)
        TouristAccountCommandService touristAccountCommandService =
                new TouristAccountCommandService(touristAccountRepositoryAdapter, auditLogService);
        TouristAccountQueryService touristAccountQueryService =
                new TouristAccountQueryService(touristAccountRepositoryAdapter);


        // 3. Setup UI Layer (User Interface)
        TouristAccountManagementUI ui =
                new TouristAccountManagementUI(touristAccountCommandService, touristAccountQueryService);

        Scanner mainScanner = new Scanner(System.in);
        String operatorId = "OP123";

        try {
            System.out.println("Welcome to Tourist Account Management System!");

            // Scenario 1: Enable an account
            System.out.println("\n--- Scenario 1: Enable a DISABLED account ---");
            ui.displayAccountDetails("TA002");
            ui.initiateStatusChange("TA002", true, operatorId);
            System.out.print("Simulate operator confirmation (yes/no): ");
            boolean confirmed = ui.getConfirmationInput();
            ui.confirmOperation("TA002", confirmed);
            ui.displayAccountDetails("TA002"); // Check status after operation

            // Scenario 2: Disable an account
            System.out.println("\n--- Scenario 2: Disable an ENABLED account ---");
            ui.displayAccountDetails("TA001");
            ui.initiateStatusChange("TA001", false, operatorId);
            System.out.print("Simulate operator confirmation (yes/no): ");
            confirmed = ui.getConfirmationInput();
            ui.confirmOperation("TA001", confirmed);
            ui.displayAccountDetails("TA001"); // Check status after operation

            // Scenario 3: Operation cancelled by operator
            System.out.println("\n--- Scenario 3: Operation cancelled by operator ---");
            ui.displayAccountDetails("TA003");
            ui.initiateStatusChange("TA003", false, operatorId); // Try to disable TA003
            System.out.print("Simulate operator confirmation (yes/no): ");
            confirmed = ui.getConfirmationInput(); // Enter 'no' or 'n'
            ui.confirmOperation("TA003", confirmed);
            ui.displayAccountDetails("TA003"); // Status should remain ENABLED

            // Scenario 4: Attempt to enable an already ENABLED account
            System.out.println("\n--- Scenario 4: Enable an already ENABLED account ---");
            ui.displayAccountDetails("TA003");
            ui.initiateStatusChange("TA003", true, operatorId);
            System.out.print("Simulate operator confirmation (yes/no): ");
            confirmed = ui.getConfirmationInput();
            ui.confirmOperation("TA003", confirmed);
            ui.displayAccountDetails("TA003");

            // Scenario 5: Error scenario - ETOUR server connection interruption
            System.out.println("\n--- Scenario 5: Error - ETOUR server connection interrupted ---");
            touristAccountRepositoryAdapter.setSimulateConnectionFailure(true); // Activate simulation
            ui.displayAccountDetails("TA001"); // This might fail too, depending on where checkConnection() is.
            ui.initiateStatusChange("TA001", true, operatorId); // Try to enable TA001
            System.out.print("Simulate operator confirmation (yes/no): ");
            confirmed = ui.getConfirmationInput();
            ui.confirmOperation("TA001", confirmed); // This call should trigger the RepositoryException
            touristAccountRepositoryAdapter.setSimulateConnectionFailure(false); // Deactivate simulation
            ui.displayAccountDetails("TA001"); // Check status, should still be DISABLED or whatever it was before

            // Scenario 6: Non-existent account
            System.out.println("\n--- Scenario 6: Non-existent account ---");
            ui.displayAccountDetails("TA999");
            ui.initiateStatusChange("TA999", true, operatorId);
            System.out.print("Simulate operator confirmation (yes/no): ");
            confirmed = ui.getConfirmationInput();
            ui.confirmOperation("TA999", confirmed);

        } finally {
            ui.closeScanner();
            mainScanner.close();
            System.out.println("\nApplication demonstration finished.");
        }
    }
}