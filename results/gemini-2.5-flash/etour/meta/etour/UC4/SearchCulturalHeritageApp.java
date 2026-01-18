package com.example.culturalheritage;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class for the SearchCulturalHeritage use case.
 * This class simulates user interaction to activate search functionality,
 * fill in a search form, submit it, and display the results.
 * It demonstrates the integration of CulturalObject, SearchCriteria, and CulturalObjectService.
 */
public class SearchCulturalHeritageApp {

    private final CulturalObjectService culturalObjectService;
    private final Scanner scanner;

    /**
     * Constructor to initialize the application with a CulturalObjectService and Scanner.
     */
    public SearchCulturalHeritageApp() {
        this.culturalObjectService = new CulturalObjectService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Activates the search functionality and guides the user through the search process.
     */
    public void activateSearchFunctionality() {
        System.out.println("--- Activating Cultural Object Search Functionality ---");
        showSearchForm();
    }

    /**
     * Displays the search form to the user and collects input for search criteria.
     */
    private void showSearchForm() {
        System.out.println("\n--- Cultural Object Search Form ---");
        System.out.println("Please enter your search criteria. Leave blank and press Enter for no filter.");

        System.out.print("Keyword (e.g., 'Mona', 'sculpture'): ");
        String keyword = readInput();

        System.out.print("Category (e.g., 'Painting', 'Sculpture', 'Building'): ");
        String category = readInput();

        System.out.print("Location (e.g., 'Paris', 'Rome'): ");
        String location = readInput();

        Integer yearFrom = null;
        while (true) {
            System.out.print("Year From (e.g., 1500, leave blank for no minimum): ");
            String yearFromString = readInput();
            if (yearFromString.isEmpty()) {
                break;
            }
            try {
                yearFrom = Integer.parseInt(yearFromString);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid year. Please enter a number or leave blank.");
            }
        }

        Integer yearTo = null;
        while (true) {
            System.out.print("Year To (e.g., 1900, leave blank for no maximum): ");
            String yearToString = readInput();
            if (yearToString.isEmpty()) {
                break;
            }
            try {
                yearTo = Integer.parseInt(yearToString);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid year. Please enter a number or leave blank.");
            }
        }

        // Create SearchCriteria object from user input
        SearchCriteria criteria = new SearchCriteria(
                keyword.isEmpty() ? null : keyword,
                category.isEmpty() ? null : category,
                location.isEmpty() ? null : location,
                yearFrom,
                yearTo
        );

        System.out.println("\n--- Submitting Search Request ---");
        System.out.println("Your search criteria: " + criteria);
        processRequest(criteria);
    }

    /**
     * Reads a line of input from the console.
     * @return The trimmed input string.
     */
    private String readInput() {
        return scanner.nextLine().trim();
    }

    /**
     * Processes the search request using the CulturalObjectService and displays the results.
     * Handles potential exceptions during the search.
     *
     * @param criteria The SearchCriteria object containing the user's search parameters.
     */
    private void processRequest(SearchCriteria criteria) {
        System.out.println("Processing your request...");
        try {
            // Simulate potential server interruption (ETOUR) by adding a delay or random failure
            // For this example, we'll just proceed with the search.
            // In a real system, this would involve network calls and error handling.

            List<CulturalObject> results = culturalObjectService.searchCulturalObjects(criteria);
            displayResults(results);
        } catch (IllegalArgumentException e) {
            System.err.println("Search Error: " + e.getMessage());
            System.out.println("Please refine your search criteria.");
        } catch (Exception e) {
            // Catch any other unexpected errors, simulating a connection interruption or server issue
            System.err.println("An unexpected error occurred during search: " + e.getMessage());
            System.err.println("Interruption of the connection to the server ETOUR. Please try again later.");
        }
    }

    /**
     * Displays the list of cultural objects that match the search criteria.
     *
     * @param results The list of CulturalObject found.
     */
    private void displayResults(List<CulturalObject> results) {
        System.out.println("\n--- Search Results ---");
        if (results.isEmpty()) {
            System.out.println("No cultural objects found matching your criteria.");
        } else {
            System.out.println("Found " + results.size() + " cultural object(s):");
            for (CulturalObject obj : results) {
                System.out.println("- " + obj);
            }
        }
        System.out.println("--- End of Results ---");
    }

    /**
     * Main method to run the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SearchCulturalHeritageApp app = new SearchCulturalHeritageApp();
        app.activateSearchFunctionality();
        app.scanner.close(); // Close the scanner when the application finishes
    }
}