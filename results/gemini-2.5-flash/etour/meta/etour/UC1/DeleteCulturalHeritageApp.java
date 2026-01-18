package com.example.culturalheritage;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class for the DeleteCulturalHeritage use case.
 * This class simulates the interaction of an Agency Operator with the system
 * to delete a cultural heritage object.
 */
public class DeleteCulturalHeritageApp {

    private final CulturalHeritageService service;
    private final Scanner scanner;
    private boolean loggedIn = false; // Simulates agency operator login status
    private boolean inputBlocked = false; // Quality requirement: blocks input controls

    /**
     * Constructs a new DeleteCulturalHeritageApp.
     * Initializes the CulturalHeritageService and a Scanner for user input.
     */
    public DeleteCulturalHeritageApp() {
        this.service = new CulturalHeritageService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Simulates the login process for an Agency Operator.
     * For simplicity, this method always "logs in" successfully.
     */
    public void login() {
        System.out.println("System: Agency Operator attempting to log in...");
        // In a real system, this would involve authentication.
        // For this simulation, we assume successful login.
        this.loggedIn = true;
        System.out.println("System: Agency Operator logged in successfully.");
    }

    /**
     * Runs the main flow of the DeleteCulturalHeritage use case.
     */
    public void run() {
        if (!loggedIn) {
            System.out.println("System: Please log in first to perform this operation.");
            return;
        }

        System.out.println("\n--- Delete Cultural Heritage Use Case ---");

        try {
            // Step 1: View the list of CulturalHeritage and select one for elimination.
            // This simulates the result of the use case SearchCulturalHeritage.
            List<CulturalHeritage> culturalHeritages = service.searchCulturalHeritage();
            if (culturalHeritages.isEmpty()) {
                System.out.println("System: No cultural heritage objects found to delete.");
                return;
            }

            System.out.println("\nSystem: Available Cultural Heritage Objects:");
            culturalHeritages.forEach(System.out::println);

            String selectedId = null;
            boolean idFound = false;
            while (!idFound) {
                System.out.print("Operator: Enter the ID of the cultural heritage to delete (or 'cancel' to abort): ");
                selectedId = scanner.nextLine().trim();

                if (selectedId.equalsIgnoreCase("cancel")) {
                    System.out.println("System: Operation cancelled by Operator Agency.");
                    return; // Exit condition: Operator Agency cancels the operation.
                }

                String finalSelectedId = selectedId;
                idFound = culturalHeritages.stream().anyMatch(ch -> ch.getId().equals(finalSelectedId));
                if (!idFound) {
                    System.out.println("System: Invalid ID. Please enter a valid ID from the list.");
                }
            }

            // Step 2: Asks confirmation.
            // Step 3: Confirm the operation.
            System.out.print("System: Are you sure you want to delete Cultural Heritage with ID '" + selectedId + "'? (yes/no): ");
            String confirmation = "";
            while (inputBlocked) {
                // Block input controls if already processing a confirmation
                // This loop ensures that if inputBlocked is true, it waits.
                // In a real GUI, this would disable buttons/fields.
                try {
                    Thread.sleep(100); // Small delay to prevent busy-waiting
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    System.err.println("System: Interrupted while waiting for input unblock.");
                }
            }
            inputBlocked = true; // Block input controls to avoid multiple submissions
            confirmation = scanner.nextLine().trim();
            inputBlocked = false; // Unblock input controls

            if (!confirmation.equalsIgnoreCase("yes")) {
                System.out.println("System: Operation cancelled by Operator Agency.");
                return; // Exit condition: Operator Agency cancels the operation.
            }

            // Step 4: Delete the cultural choice.
            boolean deleted = service.deleteCulturalHeritage(selectedId);

            // Exit condition: The system shall notify the successful elimination of the cultural.
            if (deleted) {
                System.out.println("System: Cultural Heritage successfully eliminated.");
            } else {
                System.out.println("System: Failed to delete cultural heritage. It might have been removed already or an error occurred.");
            }

        } catch (ETOURConnectionException e) {
            // Exit condition: Interruption of connection to the server ETOUR.
            System.err.println("System Error: " + e.getMessage());
            System.err.println("System: Operation failed due to ETOUR server connection interruption.");
        } catch (Exception e) {
            System.err.println("System Error: An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Ensure scanner is closed
            // scanner.close(); // Closing scanner here would prevent further use in main method if called multiple times
        }
    }

    /**
     * Main method to run the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        DeleteCulturalHeritageApp app = new DeleteCulturalHeritageApp();
        app.login(); // Agency Operator logs in
        app.run();   // Execute the delete use case

        // Example of simulating ETOUR connection interruption for testing
        System.out.println("\n--- Simulating ETOUR Connection Interruption ---");
        app.service.interruptETOURConnection();
        app.run(); // This run should fail due to connection error

        System.out.println("\n--- Simulating ETOUR Connection Restoration ---");
        app.service.restoreETOURConnection();
        app.run(); // This run should work again (if there are items left)

        app.scanner.close(); // Close scanner when application exits
    }
}