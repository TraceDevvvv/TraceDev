package com.etour.accountmanager;

import com.etour.touristaccount.Tourist;
import com.etour.exception.ETOURServerException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

/**
 * Manages the activation and deactivation of tourist accounts.
 * This class simulates the interaction of an Agency Operator with the ETOUR system
 * to change the status of a tourist's account.
 */
public class TouristAccountManager {

    // A simulated database or repository for tourist accounts.
    // In a real application, this would interact with a persistent data store.
    private Map<String, Tourist> touristDatabase;
    private Scanner scanner;

    /**
     * Constructs a new TouristAccountManager.
     * Initializes the tourist database and a scanner for user input.
     */
    public TouristAccountManager() {
        this.touristDatabase = new HashMap<>();
        this.scanner = new Scanner(System.in);
        // Populate with some dummy data for demonstration
        touristDatabase.put("T001", new Tourist("T001", "Alice Smith", true));
        touristDatabase.put("T002", new Tourist("T002", "Bob Johnson", false));
        touristDatabase.put("T003", new Tourist("T003", "Charlie Brown", true));
    }

    /**
     * Simulates a connection to the ETOUR server.
     * This method can randomly throw an ETOURServerException to simulate connection issues.
     *
     * @throws ETOURServerException if there is an interruption in the connection to the ETOUR server.
     */
    private void connectToETOURServer() throws ETOURServerException {
        // Simulate network latency or connection issues
        if (Math.random() < 0.1) { // 10% chance of connection failure
            throw new ETOURServerException("Connection to ETOUR server interrupted.");
        }
        System.out.println("Successfully connected to ETOUR server.");
    }

    /**
     * Activates or deactivates a tourist account based on the provided tourist ID and desired status.
     * This method encapsulates the core business logic for changing account status.
     *
     * @param touristId The ID of the tourist whose account status is to be changed.
     * @param activate  True to activate the account, false to deactivate.
     * @return An Optional containing the updated Tourist object if successful, or empty if the tourist is not found.
     * @throws ETOURServerException if there is an interruption in the connection to the ETOUR server.
     */
    public Optional<Tourist> updateTouristAccountStatus(String touristId, boolean activate) throws ETOURServerException {
        connectToETOURServer(); // Step 1: Simulate server connection

        Tourist tourist = touristDatabase.get(touristId);
        if (tourist == null) {
            return Optional.empty(); // Tourist not found
        }

        // Check if the account is already in the desired state
        if (tourist.isActive() == activate) {
            System.out.println("Tourist account '" + tourist.getName() + "' (ID: " + touristId + ") is already " + (activate ? "active." : "inactive."));
            return Optional.of(tourist);
        }

        // Step 2: Ask for confirmation
        String action = activate ? "activate" : "deactivate";
        System.out.println("Are you sure you want to " + action + " the account for " + tourist.getName() + " (ID: " + touristId + ")? (yes/no)");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        // Step 3: Confirm the operation
        if (!"yes".equals(confirmation)) {
            System.out.println("Operation cancelled by user.");
            return Optional.of(tourist); // Return current status as operation was cancelled
        }

        // Step 4: Enable/disable the account
        tourist.setActive(activate);
        System.out.println("Account for " + tourist.getName() + " (ID: " + touristId + ") has been successfully " + (activate ? "activated." : "deactivated."));
        return Optional.of(tourist);
    }

    /**
     * Displays the current status of all tourist accounts in the database.
     */
    public void displayAllTouristAccounts() {
        System.out.println("\n--- Current Tourist Accounts ---");
        if (touristDatabase.isEmpty()) {
            System.out.println("No tourist accounts available.");
            return;
        }
        touristDatabase.values().forEach(System.out::println);
        System.out.println("--------------------------------\n");
    }

    /**
     * The main entry point for the Tourist Account Management application.
     * This method simulates the Agency Operator's interaction.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        TouristAccountManager manager = new TouristAccountManager();
        Scanner mainScanner = new Scanner(System.in);

        System.out.println("Welcome to ETOUR Tourist Account Management System.");

        while (true) {
            manager.displayAllTouristAccounts();
            System.out.println("Enter tourist ID to manage (e.g., T001), or 'exit' to quit:");
            String touristId = mainScanner.nextLine().trim();

            if ("exit".equalsIgnoreCase(touristId)) {
                System.out.println("Exiting Tourist Account Management System. Goodbye!");
                break;
            }

            if (!manager.touristDatabase.containsKey(touristId)) {
                System.out.println("Error: Tourist with ID '" + touristId + "' not found. Please try again.");
                continue;
            }

            System.out.println("Enter desired action for " + manager.touristDatabase.get(touristId).getName() + " (activate/deactivate):");
            String action = mainScanner.nextLine().trim().toLowerCase();

            boolean activate;
            if ("activate".equals(action)) {
                activate = true;
            } else if ("deactivate".equals(action)) {
                activate = false;
            } else {
                System.out.println("Invalid action. Please enter 'activate' or 'deactivate'.");
                continue;
            }

            try {
                Optional<Tourist> updatedTourist = manager.updateTouristAccountStatus(touristId, activate);
                // Exit conditions: The notification of the outcome.
                if (updatedTourist.isPresent()) {
                    System.out.println("Operation outcome: " + updatedTourist.get());
                } else {
                    System.out.println("Operation outcome: Tourist not found during update process.");
                }
            } catch (ETOURServerException e) {
                // Exit conditions: Interruption of the connection to the server ETOUR.
                System.err.println("Server Error: " + e.getMessage());
                System.err.println("Operation failed due to ETOUR server connection interruption. Please try again later.");
            } catch (Exception e) {
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }
            System.out.println("\n--------------------------------------------------\n");
        }
        mainScanner.close();
        manager.scanner.close(); // Close the scanner used within the manager
    }
}