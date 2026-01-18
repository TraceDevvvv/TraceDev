package com.example.culturalobjects;

/**
 * VIEW: Manages the presentation layer for the search functionality.
 * Responsible for displaying search forms, results, and error messages to the user.
 */
public class SearchView {

    /**
     * Displays an empty search form to the user.
     */
    public void displaySearchForm() {
        System.out.println("\n--- Search Form ---");
        System.out.println("Please enter your search criteria:");
        System.out.println("Keywords: [e.g., 'art', 'history', 'museum']");
        System.out.println("Category: [e.g., 'Painting', 'Sculpture', 'Historical Site']");
        System.out.println("Location: [e.g., 'Paris', 'Rome', 'New York']");
        System.out.println("-------------------\n");
        System.out.println("User: Search form displayed.");
    }

    /**
     * Displays the search results to the user.
     * @param results The SearchResultDTO containing the cultural objects to display.
     */
    public void displaySearchResults(SearchResultDTO results) {
        System.out.println("\n--- Search Results ---");
        if (results.getCulturalObjects().isEmpty()) {
            System.out.println("No cultural objects found matching your criteria.");
        } else {
            System.out.println("Found " + results.getCulturalObjects().size() + " cultural objects:");
            for (CulturalObject obj : results.getCulturalObjects()) {
                System.out.println("- ID: " + obj.getId() + ", Name: " + obj.getName() + ", Type: " + obj.getType());
                System.out.println("  Description: " + obj.getDescription());
            }
        }
        System.out.println("----------------------\n");
        System.out.println("User: Search results displayed.");
    }

    /**
     * Displays an error message to the user.
     * Added to satisfy requirement REQ-009.
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\n--- ERROR ---");
        System.err.println("An error occurred: " + message);
        System.err.println("Please try again later.");
        System.err.println("-------------\n");
        System.out.println("User: Error message displayed.");
    }

    // This method is not directly part of the class diagram but helps simulate user interaction for the sequence diagram.
    public void fillAndSubmitForm(SearchFormDTO searchFormDTO, SearchController controller) {
        System.out.println("User: Filling and submitting form...");
        // In a real UI, this would be triggered by a button click with form data.
        // Here, we directly call the controller's submit method.
        controller.submitSearchForm(searchFormDTO);
    }
}