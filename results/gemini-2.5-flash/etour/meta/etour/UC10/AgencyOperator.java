package com.example.refreshmentpoint;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Simulates an Agency Operator interacting with the system to view refreshment point details.
 * This class orchestrates the user flow as described in the use case.
 */
public class AgencyOperator {

    private boolean loggedIn; // Simulates the logged-in state of the operator
    private RefreshmentPointService refreshmentPointService;
    private Scanner scanner;

    /**
     * Constructs an AgencyOperator.
     *
     * @param refreshmentPointService The service used to interact with refreshment point data.
     */
    public AgencyOperator(RefreshmentPointService refreshmentPointService) {
        this.refreshmentPointService = refreshmentPointService;
        this.loggedIn = false; // Operator is not logged in initially
        this.scanner = new Scanner(System.in);
    }

    /**
     * Simulates the login process for the agency operator.
     * In a real application, this would involve authentication.
     * For this use case, it simply sets the loggedIn flag to true.
     */
    public void login() {
        System.out.println("Agency Operator attempting to log in...");
        // Simulate a successful login
        this.loggedIn = true;
        System.out.println("Agency Operator logged in successfully.");
    }

    /**
     * Checks if the agency operator is currently logged in.
     *
     * @return true if the operator is logged in, false otherwise.
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }

    /**
     * Simulates the "SearchRefreshmentPoint" use case, displaying a list of available refreshment points.
     *
     * @return A list of RefreshmentPoint objects found.
     */
    public List<RefreshmentPoint> viewRefreshmentPointList() {
        System.out.println("\n--- Viewing List of Refreshment Points (SearchRefreshmentPoint) ---");
        List<RefreshmentPoint> points = refreshmentPointService.searchRefreshmentPoints();
        if (points.isEmpty()) {
            System.out.println("No refreshment points found.");
        } else {
            System.out.println("Available Refreshment Points:");
            for (int i = 0; i < points.size(); i++) {
                RefreshmentPoint rp = points.get(i);
                System.out.printf("%d. ID: %s, Name: %s, Location: %s%n", (i + 1), rp.getId(), rp.getName(), rp.getLocation());
            }
        }
        return points;
    }

    /**
     * Allows the operator to select a refreshment point from a list and view its detailed card.
     * This method implements the core "ViewRefreshmentPointCard" use case.
     *
     * @param availablePoints The list of refreshment points from which to select.
     */
    public void selectAndDisplayRefreshmentPointCard(List<RefreshmentPoint> availablePoints) {
        if (!isLoggedIn()) {
            System.out.println("Error: Operator must be logged in to view refreshment point cards.");
            return;
        }
        if (availablePoints == null || availablePoints.isEmpty()) {
            System.out.println("No refreshment points available to select from.");
            return;
        }

        System.out.println("\n--- Select a Refreshment Point to View Details ---");
        System.out.print("Enter the number corresponding to the refreshment point: ");

        int selection = -1;
        try {
            selection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        if (selection < 1 || selection > availablePoints.size()) {
            System.out.println("Invalid selection. Please choose a number from the list.");
            return;
        }

        // Get the selected refreshment point
        RefreshmentPoint selectedPoint = availablePoints.get(selection - 1);
        System.out.println("You selected: " + selectedPoint.getName() + " (ID: " + selectedPoint.getId() + ")");

        // Attempt to upload/display data for the selected restaurant (Step 2 of flow)
        System.out.println("Attempting to retrieve and display details for " + selectedPoint.getName() + "...");
        try {
            Optional<RefreshmentPoint> details = refreshmentPointService.getRefreshmentPointDetails(selectedPoint.getId());

            if (details.isPresent()) {
                System.out.println("\n--- Refreshment Point Card Details ---");
                System.out.println(details.get().toString());
                System.out.println("--------------------------------------");
            } else {
                System.out.println("Could not find details for the selected refreshment point (ID: " + selectedPoint.getId() + ").");
            }
        } catch (RefreshmentPointService.ConnectionInterruptionException e) {
            // Handle the specific interruption condition
            System.err.println("Operation Interrupted: " + e.getMessage());
            System.err.println("Please try again later.");
        } catch (Exception e) {
            // Catch any other unexpected errors
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Closes the scanner resource.
     */
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}