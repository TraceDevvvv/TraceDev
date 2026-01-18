package com.etop.agency;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Main class to demonstrate the DeleteTouristAccount use case.
 * This class simulates the agency operator's interaction with the system
 * to search for a tourist and then delete their account.
 */
public class DeleteTouristAccount {

    private static List<Tourist> touristDatabase = new ArrayList<>();
    private static ETOURServer etourServer = new ETOURServer();
    private static Scanner scanner = new Scanner(System.in);

    // Static block to initialize some dummy tourist data
    static {
        touristDatabase.add(new Tourist("T001", "Alice Smith"));
        touristDatabase.add(new Tourist("T002", "Bob Johnson"));
        touristDatabase.add(new Tourist("T003", "Charlie Brown"));
        touristDatabase.add(new Tourist("T004", "Diana Prince"));
    }

    /**
     * Simulates the SearchTourist use case to find tourists based on a query.
     * In a real system, this would involve more sophisticated search logic
     * and potentially a database query.
     *
     * @param query The search string (e.g., part of a name or ID).
     * @return A list of tourists matching the query.
     */
    public static List<Tourist> searchTourist(String query) {
        System.out.println("\nSystem: Searching for tourists with query: '" + query + "'...");
        List<Tourist> results = new ArrayList<>();
        for (Tourist tourist : touristDatabase) {
            if (tourist.getName().toLowerCase().contains(query.toLowerCase()) ||
                tourist.getId().toLowerCase().contains(query.toLowerCase())) {
                results.add(tourist);
            }
        }
        return results;
    }

    /**
     * Simulates the agency operator logging in.
     * For this use case, we assume the login is always successful.
     *
     * @return true if the agency operator is logged in, false otherwise.
     */
    public static boolean agencyLogin() {
        System.out.println("Agency Operator: Attempting to log in...");
        // In a real application, this would involve authentication.
        // For this simulation, we assume successful login.
        System.out.println("System: Agency Operator logged in successfully.");
        return true;
    }

    /**
     * Main method to run the DeleteTouristAccount use case simulation.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        System.out.println("--- Starting DeleteTouristAccount Use Case ---");

        // Entry Condition: The agency has logged in.
        if (!agencyLogin()) {
            System.out.println("System: Agency Operator failed to log in. Exiting use case.");
            return;
        }

        // Flow of events:
        // 1. Tourists from the list obtained by activating the use case SearchTourist
        //    will select and activate a feature for disposal.
        System.out.println("\nAgency Operator: Please enter a search query to find the tourist to delete:");
        String searchQuery = scanner.nextLine();
        List<Tourist> foundTourists = searchTourist(searchQuery);

        if (foundTourists.isEmpty()) {
            System.out.println("System: No tourists found matching your query.");
            System.out.println("--- DeleteTouristAccount Use Case Finished ---");
            return;
        }

        System.out.println("\nSystem: Found the following tourists:");
        for (int i = 0; i < foundTourists.size(); i++) {
            System.out.println((i + 1) + ". " + foundTourists.get(i));
        }

        Tourist selectedTourist = null;
        while (selectedTourist == null) {
            System.out.println("\nAgency Operator: Enter the number of the tourist to delete (or 'q' to quit):");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("q")) {
                System.out.println("System: Deletion cancelled by operator. Exiting use case.");
                System.out.println("--- DeleteTouristAccount Use Case Finished ---");
                return;
            }
            try {
                int index = Integer.parseInt(choice) - 1;
                if (index >= 0 && index < foundTourists.size()) {
                    selectedTourist = foundTourists.get(index);
                } else {
                    System.out.println("System: Invalid selection. Please enter a valid number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("System: Invalid input. Please enter a number or 'q'.");
            }
        }

        System.out.println("System: You have selected to delete: " + selectedTourist);

        // 2. Asks for confirmation of the transaction.
        System.out.println("\nSystem: Are you sure you want to delete the account for " + selectedTourist.getName() + " (ID: " + selectedTourist.getId() + ")? (yes/no)");
        String confirmation = scanner.nextLine();

        // 3. Confirm the operation.
        if (!confirmation.equalsIgnoreCase("yes")) {
            System.out.println("System: Deletion cancelled by operator. Exiting use case.");
            System.out.println("--- DeleteTouristAccount Use Case Finished ---");
            return;
        }

        // Check ETOUR server connection before attempting deletion
        if (!etourServer.isConnected()) {
            System.err.println("System: Cannot connect to ETOUR server. Deletion aborted.");
            System.out.println("--- DeleteTouristAccount Use Case Finished ---");
            return;
        }

        // 4. Delete the selected data.
        System.out.println("\nSystem: Attempting to delete tourist account for " + selectedTourist.getName() + "...");
        boolean deletionSuccessful = etourServer.deleteTouristAccount(selectedTourist.getId());

        if (deletionSuccessful) {
            // Remove from local database if server deletion is successful
            touristDatabase.remove(selectedTourist);
            System.out.println("System: Tourist account for " + selectedTourist.getName() + " (ID: " + selectedTourist.getId() + ") has been successfully deleted.");
            // Exit condition: The notification system has been elimination of selected accounts Tourist.
            System.out.println("System: Notification sent: Tourist account eliminated.");
        } else {
            System.err.println("System: Failed to delete tourist account for " + selectedTourist.getName() + ".");
            // Exit condition: Interruption of the connection to the server ETOUR.
            // This is handled within ETOURServer.deleteTouristAccount or by the isConnected check.
            if (!etourServer.isConnected()) {
                System.err.println("System: Interruption of the connection to the server ETOUR detected during deletion attempt.");
            }
        }

        System.out.println("\n--- DeleteTouristAccount Use Case Finished ---");
        scanner.close();
    }
}