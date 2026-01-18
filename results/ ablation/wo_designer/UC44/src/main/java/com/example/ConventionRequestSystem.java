package com.example;

import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * System to handle the convention request flow.
 * Implements the steps described in the use case.
 */
public class ConventionRequestSystem {
    private Scanner scanner;
    private boolean authenticated;
    private Convention convention;
    private boolean serverConnected;

    public ConventionRequestSystem() {
        scanner = new Scanner(System.in);
        authenticated = false;
        serverConnected = true; // Assume connection is initially OK
    }

    /**
     * Starts the convention request process.
     * Simulates the entire flow from authentication to submission.
     */
    public void start() {
        System.out.println("=== Convention Request System ===");

        // Entry Condition: Authentication
        if (!authenticate()) {
            System.out.println("Authentication failed. Exiting.");
            return;
        }

        // Step 1: Enable functionality
        enableFunctionality();

        // Step 2: Display form
        displayForm();

        // Step 3: Insert data
        insertData();

        // Step 4: Submit form
        submitForm();

        // Step 5: Verify data
        if (!verifyData()) {
            errored("Data verification failed.");
            return;
        }

        // Step 6: Ask for confirmation
        askConfirmation();

        // Step 7: Confirm operation
        if (!confirmOperation()) {
            System.out.println("Operation cancelled by operator.");
            return; // Exit condition: cancellation
        }

        // Step 8: Send request
        sendRequest();

        // Check server connection
        if (!serverConnected) {
            errored("Connection to the server ETOUR is interrupted.");
            return; // Exit condition: server interruption
        }

        // Success: Notification sent
        System.out.println("\n*** Notification about the call for the Convention to the Agency has been sent successfully. ***");
        scanner.close();
    }

    private boolean authenticate() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Simple authentication simulation
        authenticated = "operator".equals(username) && "pass123".equals(password);
        if (authenticated) {
            System.out.println("Authentication successful.");
        }
        return authenticated;
    }

    private void enableFunctionality() {
        System.out.println("\n--- Functionality to request the Convention is enabled. ---");
    }

    private void displayForm() {
        System.out.println("\n--- Convention Request Form ---");
        System.out.println("Please enter the following details:");
    }

    private void insertData() {
        System.out.print("Restaurant Name: ");
        String restaurantName = scanner.nextLine();

        System.out.print("Operator Name: ");
        String operatorName = scanner.nextLine();

        System.out.print("Agency Name: ");
        String agencyName = scanner.nextLine();

        Date startDate = readDate("Start Date (yyyy-MM-dd): ");
        Date endDate = readDate("End Date (yyyy-MM-dd): ");

        int numberOfGuests = readInt("Number of Guests: ");

        System.out.print("Special Requirements: ");
        String specialRequirements = scanner.nextLine();

        convention = new Convention(restaurantName, operatorName, agencyName,
                                    startDate, endDate, numberOfGuests, specialRequirements);
    }

    private Date readDate(String prompt) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        while (true) {
            System.out.print(prompt);
            try {
                return sdf.parse(scanner.nextLine());
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
            }
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter an integer.");
            }
        }
    }

    private void submitForm() {
        System.out.println("\nForm submitted successfully.");
    }

    private boolean verifyData() {
        if (convention == null) {
            return false;
        }
        // Check required fields are not empty
        if (convention.getRestaurantName() == null || convention.getRestaurantName().trim().isEmpty() ||
            convention.getOperatorName() == null || convention.getOperatorName().trim().isEmpty() ||
            convention.getAgencyName() == null || convention.getAgencyName().trim().isEmpty() ||
            convention.getStartDate() == null || convention.getEndDate() == null) {
            return false;
        }
        // Check dates are logical
        if (convention.getStartDate().after(convention.getEndDate())) {
            System.out.println("Error: Start date must be before end date.");
            return false;
        }
        // Check guests positive
        if (convention.getNumberOfGuests() <= 0) {
            System.out.println("Error: Number of guests must be positive.");
            return false;
        }
        return true;
    }

    private void askConfirmation() {
        System.out.println("\n--- Please confirm the following details ---");
        System.out.println(convention);
    }

    private boolean confirmOperation() {
        System.out.print("\nConfirm the request? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

    private void sendRequest() {
        // Simulate sending request to agency
        System.out.println("\nSending request to the Convention...");
        // In a real system, this would involve network calls.
        // Simulate a random server connection failure (10% chance) for demonstration.
        if (Math.random() < 0.1) {
            serverConnected = false;
        }
    }

    private void errored(String message) {
        System.out.println("\n*** ERRO ***");
        System.out.println("Error: " + message);
        System.out.println("Use case 'Errored' activated.");
    }
}