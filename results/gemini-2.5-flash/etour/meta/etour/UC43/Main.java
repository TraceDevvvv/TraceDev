package com.restaurant.app;

import com.restaurant.manager.RefreshmentPointManager;
import com.restaurant.operator.RestaurantOperator;
import com.restaurant.refreshment.RefreshmentPoint;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Main class to simulate the "ModifyRefreshmentPoint" use case.
 * This class orchestrates the flow of events, handles user interaction,
 * and demonstrates error handling and exit conditions.
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static RefreshmentPointManager manager = new RefreshmentPointManager();

    public static void main(String[] args) {
        System.out.println("--- Welcome to the Refreshment Point Management System ---");

        // Entry condition: The Point Restaurant Operator has successfully authenticated to the system.
        RestaurantOperator operator = new RestaurantOperator("admin");
        if (!authenticateOperator(operator)) {
            System.out.println("System: Operator authentication failed. Exiting application.");
            return;
        }

        // Flow of events:
        // 1. Enable the functionality of the information required by the point of rest.
        // (This is implicitly enabled by the menu options presented to the authenticated operator)
        System.out.println("\nSystem: Functionality for modifying refreshment points is enabled.");

        while (true) {
            System.out.println("\n--- Modify Refreshment Point Menu ---");
            System.out.println("1. Modify an existing Refreshment Point");
            System.out.println("2. Simulate Server Connection Interruption (ETOUR)");
            System.out.println("3. Logout and Exit");
            System.out.print("Please choose an option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        modifyRefreshmentPointFlow();
                        break;
                    case 2:
                        // Simulate Interruption of the connection to the server ETOUR.
                        try {
                            manager.simulateConnectionInterruption();
                        } catch (RuntimeException e) {
                            System.err.println("System: " + e.getMessage());
                            System.out.println("System: Application exiting due to server connection interruption.");
                            return; // Exit application on ETOUR
                        }
                        break;
                    case 3:
                        operator.logout();
                        System.out.println("System: Exiting application. Goodbye!");
                        return; // Exit application
                    default:
                        System.out.println("System: Invalid option. Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("System: Invalid input. Please enter a number.");
                scanner.nextLine(); // Consume the invalid input
            } catch (Exception e) {
                System.err.println("System: An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    /**
     * Simulates the operator authentication process.
     * @param operator The RestaurantOperator attempting to authenticate.
     * @return true if authentication is successful, false otherwise.
     */
    private static boolean authenticateOperator(RestaurantOperator operator) {
        System.out.print("Operator " + operator.getUsername() + ", please enter your password: ");
        String password = scanner.nextLine();
        return operator.authenticate(password);
    }

    /**
     * Implements the core flow for modifying a refreshment point.
     */
    private static void modifyRefreshmentPointFlow() {
        System.out.print("System: Enter the ID of the Refreshment Point to modify: ");
        String refreshmentPointId = scanner.nextLine();

        // 2. Upload data point Refreshments and displays them in a form.
        RefreshmentPoint originalPoint = manager.getRefreshmentPoint(refreshmentPointId);

        if (originalPoint == null) {
            System.out.println("System: Cannot modify. Refreshment Point with ID " + refreshmentPointId + " not found.");
            return;
        }

        // Create a working copy for modification
        RefreshmentPoint modifiedPoint = originalPoint.deepCopy();
        System.out.println("\nSystem: Current data for Refreshment Point ID " + refreshmentPointId + ":");
        System.out.println(modifiedPoint);
        System.out.println("System: Please enter new values (leave blank to keep current value).");

        // 3. Change data in the form and submit.
        System.out.print("Enter new Name [" + modifiedPoint.getName() + "]: ");
        String newName = scanner.nextLine();
        if (!newName.trim().isEmpty()) {
            modifiedPoint.setName(newName);
        }

        System.out.print("Enter new Location [" + modifiedPoint.getLocation() + "]: ");
        String newLocation = scanner.nextLine();
        if (!newLocation.trim().isEmpty()) {
            modifiedPoint.setLocation(newLocation);
        }

        System.out.print("Enter new Type [" + modifiedPoint.getType() + "]: ");
        String newType = scanner.nextLine();
        if (!newType.trim().isEmpty()) {
            modifiedPoint.setType(newType);
        }

        System.out.print("Enter new Contact Info [" + modifiedPoint.getContactInfo() + "]: ");
        String newContactInfo = scanner.nextLine();
        if (!newContactInfo.trim().isEmpty()) {
            modifiedPoint.setContactInfo(newContactInfo);
        }

        System.out.println("\nSystem: Proposed changes:");
        System.out.println(modifiedPoint);

        // 4. Verify the data entered and asks for confirmation of the change.
        // Where the data is invalid or insufficient, the system activates the use case Errored.
        String validationError = manager.validateRefreshmentPointData(modifiedPoint);
        if (validationError != null) {
            System.err.println("System: Data validation failed. Activating 'Errored' use case.");
            System.err.println("Error: " + validationError);
            System.out.println("System: Modification cancelled due to invalid data.");
            return; // Exit this modification flow
        }

        System.out.print("System: Data is valid. Confirm changes? (yes/no): ");
        String confirmation = scanner.nextLine();

        if ("yes".equalsIgnoreCase(confirmation.trim())) {
            // 5. Confirm the operation.
            System.out.println("System: Operation confirmed by operator.");
            // 6. Stores the modified data of the point of rest.
            if (manager.storeModifiedRefreshmentPoint(modifiedPoint)) {
                System.out.println("System: Refreshment Point ID " + modifiedPoint.getId() + " successfully updated.");
                // Exit conditions: The notification system has been changing the data point selected restaurants.
                System.out.println("System: Notification: Refreshment Point data changed for ID: " + modifiedPoint.getId());
            } else {
                System.err.println("System: Failed to store modified data. Please check logs.");
            }
        } else {
            // Exit conditions: Restaurant Point Of Operator cancels the operation.
            System.out.println("System: Operator cancelled the modification operation.");
        }
    }
}