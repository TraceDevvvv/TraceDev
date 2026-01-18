package com.etour.searchtourist;

import java.util.List;
import java.util.Scanner;

/**
 * Simulates an Agency Operator interacting with the ETOUR system to search for tourists.
 * This class handles user input for search criteria and displays results.
 */
public class AgencyOperator {

    private final TouristService touristService;
    private boolean loggedIn; // Simulates the logged-in state of the agency operator

    /**
     * Constructs an AgencyOperator with a reference to the TouristService.
     *
     * @param touristService The service responsible for tourist search operations.
     */
    public AgencyOperator(TouristService touristService) {
        this.touristService = touristService;
        this.loggedIn = false; // Operator is not logged in initially
    }

    /**
     * Simulates the login process for the agency operator.
     * In a real system, this would involve authentication.
     * For this use case, we simply set the loggedIn flag to true.
     */
    public void login() {
        System.out.println("Agency Operator attempting to log in...");
        // Simulate successful login
        this.loggedIn = true;
        System.out.println("Agency Operator logged in successfully.");
    }

    /**
     * Accesses the search functionality for tourists.
     * This method guides the operator through filling out the search form and submitting it.
     */
    public void accessSearchFunctionality() {
        if (!loggedIn) {
            System.out.println("Error: Agency Operator must be logged in to access search functionality.");
            return;
        }

        System.out.println("\n--- Accessing Tourist Search Functionality ---");
        showSearchForm();
    }

    /**
     * Displays the search form to the operator and collects input.
     */
    private void showSearchForm() {
        Scanner scanner = new Scanner(System.in);
        TouristSearchCriteria criteria = new TouristSearchCriteria();

        System.out.println("Please fill out the search form (leave blank for any):");

        System.out.print("Tourist ID: ");
        String touristId = scanner.nextLine();
        if (!touristId.trim().isEmpty()) {
            criteria.setTouristId(touristId.trim());
        }

        System.out.print("First Name: ");
        String firstName = scanner.nextLine();
        if (!firstName.trim().isEmpty()) {
            criteria.setFirstName(firstName.trim());
        }

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine();
        if (!lastName.trim().isEmpty()) {
            criteria.setLastName(lastName.trim());
        }

        System.out.print("Email: ");
        String email = scanner.nextLine();
        if (!email.trim().isEmpty()) {
            criteria.setEmail(email.trim());
        }

        System.out.print("Country: ");
        String country = scanner.nextLine();
        if (!country.trim().isEmpty()) {
            criteria.setCountry(country.trim());
        }

        System.out.println("\nSubmitting search request with criteria: " + criteria);
        processSearchRequest(criteria);
    }

    /**
     * Processes the search request using the TouristService and displays the results.
     *
     * @param criteria The search criteria provided by the operator.
     */
    private void processSearchRequest(TouristSearchCriteria criteria) {
        try {
            List<Tourist> results = touristService.searchTourists(criteria);
            displaySearchResults(results);
        } catch (TouristService.ConnectionInterruptionException e) {
            System.err.println("Search failed: " + e.getMessage());
            System.err.println("Please try again later or contact support.");
        } catch (Exception e) {
            System.err.println("An unexpected error occurred during search: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Displays the list of tourist accounts that match the search criteria.
     *
     * @param tourists The list of {@link Tourist} objects found.
     */
    private void displaySearchResults(List<Tourist> tourists) {
        System.out.println("\n--- Search Results ---");
        if (tourists.isEmpty()) {
            System.out.println("No tourist accounts found matching your criteria.");
        } else {
            System.out.println("Found " + tourists.size() + " tourist account(s):");
            for (Tourist tourist : tourists) {
                System.out.println("- " + tourist);
            }
        }
        System.out.println("----------------------");
    }

    /**
     * Simulates the logout process for the agency operator.
     */
    public void logout() {
        System.out.println("Agency Operator logging out...");
        this.loggedIn = false;
        System.out.println("Agency Operator logged out.");
    }
}