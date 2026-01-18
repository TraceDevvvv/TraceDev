package com.example.ui;

import com.example.controller.SearchTouristAccountUseCaseController;
import com.example.dto.SearchTouristDTO;
import com.example.dto.TouristAccountDTO;
import java.util.List;
import java.util.Scanner;

/**
 * UI class for searching tourist accounts.
 * Simulates interaction with an Agency Operator (actor).
 */
public class SearchTouristAccountUI {
    private SearchTouristAccountUseCaseController controller;
    private Scanner scanner;

    public SearchTouristAccountUI(SearchTouristAccountUseCaseController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Shows the search form (Flow of Events 2).
     */
    public void showForm() {
        System.out.println("=== Tourist Account Search ===");
        System.out.println("Enter search criteria (press Enter to skip any field):");
    }

    /**
     * Submits the form (Flow of Events 3 & 4).
     */
    public void submitForm(SearchTouristDTO searchData) {
        // In a real UI, this would be triggered by a button click.
        // Here we simulate by calling the controller.
        List<TouristAccountDTO> results = controller.searchTouristAccounts(searchData);
        displayResults(results);
    }

    /**
     * Displays search results (Flow of Events 6 normal flow).
     */
    public void displayResults(List<TouristAccountDTO> results) {
        if (results.isEmpty()) {
            System.out.println("No tourist accounts found.");
        } else {
            System.out.println("Found " + results.size() + " tourist account(s):");
            for (TouristAccountDTO dto : results) {
                System.out.println(dto);
            }
        }
    }

    /**
     * Displays an error message (alternative path).
     */
    public void displayError(String message) {
        System.err.println("Error: " + message);
    }

    /**
     * Simulates the UI interaction flow (as per sequence diagram).
     * This method mimics the Agency Operator interacting with the UI.
     */
    public void simulateUIFlow() {
        // Precondition: Agency Operator is logged in (simulated by this method being called)
        System.out.println("Agency Operator is logged in.");

        showForm();

        // Simulate user input
        System.out.print("First Name: ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Last Name: ");
        String lastName = scanner.nextLine().trim();

        System.out.print("Email: ");
        String email = scanner.nextLine().trim();

        System.out.print("Agency Reference: ");
        String agencyRef = scanner.nextLine().trim();

        SearchTouristDTO searchData = new SearchTouristDTO(firstName, lastName, email, agencyRef);
        submitForm(searchData);
    }

    // Main method for standalone run
    public static void main(String[] args) {
        // Setup dependencies (as per class diagram)
        com.example.connection.ETOURConnection connection = new com.example.connection.ETOURConnection();
        com.example.repository.TouristAccountRepository repo = new com.example.repository.TouristAccountRepositoryImpl(connection);
        SearchTouristAccountUseCaseController controller = new SearchTouristAccountUseCaseController(repo);
        SearchTouristAccountUI ui = new SearchTouristAccountUI(controller);

        // Simulate the UI flow
        ui.simulateUIFlow();
    }

    // New method to correspond to sequence diagram message "accesses search functionality"
    public void accessSearch() {
        // This method is called when the Agency Operator accesses the search functionality.
        System.out.println("Agency Operator accesses search functionality.");
        simulateUIFlow();
    }
}