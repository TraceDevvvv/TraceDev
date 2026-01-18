package com.example;

import com.example.exception.ETOURException;
import com.example.model.RefreshmentPoint;
import com.example.service.RefreshmentPointService;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Main application class to simulate the deletion use case.
 */
public class Application {
    private RefreshmentPointService service;
    private Scanner scanner;
    private boolean loggedIn;

    public Application() {
        service = new RefreshmentPointService();
        scanner = new Scanner(System.in);
        loggedIn = false;
    }

    /**
     * Simulates the login process. In a real system, this would involve authentication.
     */
    private void login() {
        System.out.println("Simulating login...");
        System.out.print("Enter username (any input will simulate login): ");
        scanner.nextLine();
        loggedIn = true;
        System.out.println("Agency Operator logged in successfully.");
    }

    /**
     * Displays the list of refreshment points.
     */
    private void displayRefreshmentPoints() {
        List<RefreshmentPoint> points = service.getAllRefreshmentPoints();
        if (points.isEmpty()) {
            System.out.println("No refreshment points available.");
        } else {
            System.out.println("\n=== List of Refreshment Points ===");
            for (RefreshmentPoint rp : points) {
                System.out.println(rp);
            }
        }
    }

    /**
     * Asks the user to select a refreshment point by ID.
     */
    private Optional<RefreshmentPoint> selectRefreshmentPoint() {
        System.out.print("\nEnter the ID of the refreshment point to delete: ");
        String id = scanner.nextLine().trim();
        return service.findById(id);
    }

    /**
     * Asks for confirmation before deletion.
     */
    private boolean confirmDeletion(RefreshmentPoint rp) {
        System.out.println("You have selected: " + rp);
        System.out.print("Are you sure you want to delete this refreshment point? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();
        return confirmation.equals("yes") || confirmation.equals("y");
    }

    /**
     * Simulates a possible connection interruption.
     */
    private void checkConnection() throws ETOURException {
        // Simulating random connection interruption (10% chance)
        if (Math.random() < 0.1) {
            throw new ETOURException("ETOUR connection to the server is interrupted.");
        }
    }

    /**
     * Main flow to delete a refreshment point.
     */
    public void deleteRefreshmentPointFlow() {
        if (!loggedIn) {
            System.out.println("Error: Agency Operator must be logged in.");
            return;
        }

        try {
            // Step 1: Display refreshment points
            displayRefreshmentPoints();

            // Step 2: Select a refreshment point
            Optional<RefreshmentPoint> selected = selectRefreshmentPoint();
            if (selected.isEmpty()) {
                System.out.println("No refreshment point found with the given ID.");
                System.out.println("Operation cancelled.");
                return;
            }

            // Step 3: Activation of deletion (implicitly done by selecting)

            // Step 4: Ask for confirmation
            if (!confirmDeletion(selected.get())) {
                System.out.println("Deletion cancelled by Agency Operator.");
                return;
            }

            // Simulate connection check before deletion
            checkConnection();

            // Step 5: Confirm (already done) and Step 6: Delete
            boolean deleted = service.deleteRefreshmentPoint(selected.get().getId());
            if (deleted) {
                System.out.println("The system notifies the successful elimination of the refreshment point.");
            } else {
                System.out.println("Failed to delete refreshment point (it may have been already removed).");
            }

        } catch (ETOURException e) {
            System.out.println("Exit Condition: " + e.getMessage());
            System.out.println("Operation aborted due to connection interruption.");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Main menu for the application.
     */
    public void run() {
        login();
        while (loggedIn) {
            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Delete a refreshment point");
            System.out.println("2. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    deleteRefreshmentPointFlow();
                    break;
                case "2":
                    loggedIn = false;
                    System.out.println("Logged out. Exiting application.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        Application app = new Application();
        app.run();
    }
}