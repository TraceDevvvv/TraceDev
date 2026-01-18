package com.example.search.presentation;

import com.example.search.application.SearchUseCaseController;
import com.example.search.application.SearchUseCaseException;
import com.example.search.domain.SearchCriteria;
import com.example.search.domain.SearchResultDTO;

import java.util.Map;

/**
 * Represents the entry point for user interactions related to searching cultural objects.
 * This class handles display logic, user input, and delegates to the application layer.
 * Requirement R3: System is ready to receive requests when activateSearch() is called.
 */
public class PresentationController {

    private final SearchUseCaseController searchUseCaseController;

    /**
     * Constructs a PresentationController with a dependency on SearchUseCaseController.
     *
     * @param searchUseCaseController The controller handling the search use case logic.
     */
    public PresentationController(SearchUseCaseController searchUseCaseController) {
        this.searchUseCaseController = searchUseCaseController;
    }

    /**
     * Activates the search functionality.
     * Requirement R4: This method is added to explicitly activate search flow.
     * As per SD, this is the first user action.
     */
    public void activateSearch() {
        System.out.println("\nUI: User activates search functionality.");
        displaySearchForm();
    }

    /**
     * Displays the search form to the user.
     * (Placeholder for actual UI rendering logic.)
     * This corresponds to "System shows the form for research" in the SD.
     */
    public void displaySearchForm() {
        System.out.println("UI: Displaying search form to user.");
        System.out.println("------------------------------------");
        System.out.println("Please enter your search criteria:");
        System.out.println("  Keyword (e.g., 'painting', 'ancient'):");
        System.out.println("  Type Filter (e.g., 'Painting', 'Sculpture'):");
        System.out.println("  Year Range Start (e.g., 1800):");
        System.out.println("  Year Range End (e.g., 1900):");
        System.out.println("------------------------------------");
    }

    /**
     * Handles a search request submitted by the user.
     * It processes the form data, creates a SearchCriteria DTO, and delegates to the Use Case Controller.
     * This corresponds to "User fills in the search form and submits" in the SD.
     * As per sequence diagram, this method is named submitSearchForm.
     *
     * @param formData A map containing user input from the search form.
     */
    public void submitSearchForm(Map<String, String> formData) {
        System.out.println("UI: User submitted search form with data: " + formData);

        try {
            // Create SearchCriteria DTO from form data (as per sequence diagram note)
            String keyword = formData.getOrDefault("keyword", "");
            String typeFilter = formData.getOrDefault("typeFilter", "");
            int yearStart = parseInteger(formData.get("yearRangeStart"), 0);
            int yearEnd = parseInteger(formData.get("yearRangeEnd"), 9999); // Max year assumption

            SearchCriteria criteria = new SearchCriteria(keyword, typeFilter, yearStart, yearEnd);

            // Delegate to SearchUseCaseController to perform the search
            SearchResultDTO results = searchUseCaseController.performSearch(criteria);

            // Display the results to the user
            displaySearchResults(results);

        } catch (SearchUseCaseException e) {
            // Catch specific use case exceptions and display an appropriate error message
            displayErrorMessage("Search failed: " + e.getMessage());
        } catch (NumberFormatException e) {
            displayErrorMessage("Invalid year format. Please enter numeric values for year ranges.");
        } catch (Exception e) {
            // General error handling
            displayErrorMessage("An unexpected error occurred during search: " + e.getMessage());
        }
    }

    /**
     * Displays the search results to the user.
     * (Placeholder for actual UI rendering logic.)
     * This corresponds to "System returns a list of cultural goods" in the SD.
     *
     * @param results The DTO containing the search results.
     */
    public void displaySearchResults(SearchResultDTO results) {
        System.out.println("\nUI: Displaying Search Results:");
        if (results.getCulturalObjects().isEmpty()) {
            System.out.println("  No cultural objects found matching your criteria.");
        } else {
            System.out.println("  Found " + results.getCulturalObjects().size() + " cultural objects:");
            results.getCulturalObjects().forEach(obj ->
                    System.out.println("    - " + obj.getName() + " (" + obj.getType() + ", " + obj.getYear() + ") - ID: " + obj.getId())
            );
        }
        System.out.println("------------------------------------\n");
    }

    /**
     * Displays an error message to the user.
     * (Placeholder for actual UI error display logic.)
     * This corresponds to "Interruption of the connection to the server ETOUR" error path in the SD.
     *
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\nUI: ERROR - " + message);
        System.out.println("------------------------------------\n");
    }

    /**
     * Helper method to parse integer from string, with a default value.\n     */
    private int parseInteger(String value, int defaultValue) {
        try {
            return value != null && !value.trim().isEmpty() ? Integer.parseInt(value) : defaultValue;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid number format for year: '" + value + "'");
        }
    }
}