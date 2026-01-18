package com.etour.touristsearch;

import java.util.List;
import java.util.Scanner;

/**
 * Main class to demonstrate the Tourist Search functionality.
 * This class simulates the Agency Operator's interaction with the system
 * to search for tourist accounts.
 */
public class Main {

    public static void main(String[] args) {
        // Simulate agency login condition
        boolean agencyLogged = true;

        if (!agencyLogged) {
            System.out.println("Agency not logged in. Please log in to access tourist search functionality.");
            return;
        }

        // Initialize the TouristSearchService
        TouristSearchService searchService = new TouristSearchService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the ETOUR Tourist Search System!");

        // Step 1: Access the search functionality of a tourist.
        // Step 2: Show the form.
        System.out.println("\n--- Tourist Search Form ---");
        System.out.println("Please enter search criteria. Leave blank and press Enter for any field to ignore it.");

        String touristId = null;
        String firstName = null;
        String lastName = null;
        String email = null;
        String country = null;
        String phoneNumber = null;

        try {
            System.out.print("Tourist ID: ");
            touristId = scanner.nextLine();

            System.out.print("First Name: ");
            firstName = scanner.nextLine();

            System.out.print("Last Name: ");
            lastName = scanner.nextLine();

            System.out.print("Email: ");
            email = scanner.nextLine();

            System.out.print("Country: ");
            country = scanner.nextLine();

            System.out.print("Phone Number: ");
            phoneNumber = scanner.nextLine();

            // Step 3: Fill out the form and submit.
            System.out.println("\nProcessing your request...");

            // Simulate potential connection interruption
            // For demonstration, we can add a condition to simulate this
            // For example, if the user types "interrupt" in any field, we simulate it.
            if (touristId.equalsIgnoreCase("interrupt") || firstName.equalsIgnoreCase("interrupt") ||
                lastName.equalsIgnoreCase("interrupt") || email.equalsIgnoreCase("interrupt") ||
                country.equalsIgnoreCase("interrupt") || phoneNumber.equalsIgnoreCase("interrupt")) {
                searchService.simulateConnectionInterruption();
                System.out.println("Search aborted due to connection interruption.");
                return;
            }

            // Step 4: Processing the request.
            List<Tourist> results = searchService.searchTourists(
                touristId.isEmpty() ? null : touristId,
                firstName.isEmpty() ? null : firstName,
                lastName.isEmpty() ? null : lastName,
                email.isEmpty() ? null : email,
                country.isEmpty() ? null : country,
                phoneNumber.isEmpty() ? null : phoneNumber
            );

            // Exit conditions: The system returns a list of accounts that meet Tourist your search criteria.
            System.out.println("\n--- Search Results ---");
            if (results.isEmpty()) {
                System.out.println("No tourist accounts found matching your criteria.");
            } else {
                System.out.println("Found " + results.size() + " tourist account(s):");
                for (Tourist tourist : results) {
                    System.out.println(tourist);
                }
            }

        } catch (Exception e) {
            System.err.println("An unexpected error occurred during search: " + e.getMessage());
            // In a real application, more robust error handling and logging would be present.
        } finally {
            scanner.close(); // Close the scanner to prevent resource leaks
            System.out.println("\nThank you for using the ETOUR Tourist Search System.");
        }
    }
}