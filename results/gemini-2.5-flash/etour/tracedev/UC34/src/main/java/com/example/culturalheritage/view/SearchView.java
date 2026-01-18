package com.example.culturalheritage.view;

import com.example.culturalheritage.controller.SearchController;
import com.example.culturalheritage.model.SearchCriteria;
import com.example.culturalheritage.model.SearchResult;
import com.example.culturalheritage.model.CulturalHeritage;

import java.util.Scanner;

/**
 * Represents the user interface for searching cultural heritage.
 * This class is responsible for displaying forms, results, and error messages,
 * and for capturing user input.
 * Implements the 'View' role in an MVC pattern.
 */
public class SearchView {
    private SearchController searchController;
    // Attributes from Class Diagram, not strictly needed for a simple console view but good for completeness.
    private SearchCriteria searchForm; // This will hold the current criteria, potentially set by user or for testing.
    private SearchResult searchResults;

    /**
     * Constructor for SearchView.
     * Injects the SearchController dependency.
     * @param searchController The controller responsible for handling search actions.
     */
    public SearchView(SearchController searchController) {
        this.searchController = searchController;
    }

    /**
     * Simulates the user activating the search functionality.
     * This method is called to initiate the search process, fulfilling REQ-004.
     * Corresponds to sequence diagram message m1: activateSearchFunctionality().
     */
    public void activateSearchFunctionality() { // Renamed from activateSearch() to match diagram
        System.out.println("--- User activates search functionality ---"); // m1
        // Delegate to the controller to handle the activation
        searchController.handleActivateSearch();
    }

    /**
     * Displays the search input form to the user.
     * In a real UI, this would render a graphical form. Here, it's a simple console message.
     */
    public void displayForm() {
        System.out.println("--- Displaying search form ---"); // m5
        System.out.println("Please enter your search criteria:");
        // In a real application, this would present an interactive form for user input.
        // For this console example, the 'searchForm' attribute can be pre-filled or will be created upon submission.
    }

    /**
     * Simulates the user filling out a search form and then submitting it.
     * This method corresponds to the `submitForm()` message from the GUI in the sequence diagram (m8).
     * It uses the currently held `searchForm` criteria or generates default ones if not set.
     */
    public void submitForm() { // m8
        System.out.println("--- User submits form (via SearchView.submitForm()) ---");
        // Use pre-set criteria for testing, or generate default ones if none are set.
        SearchCriteria criteriaToSubmit = (this.searchForm != null) ? this.searchForm : createDefaultSearchCriteria();

        System.out.println("Submitted Criteria: Keyword='" + criteriaToSubmit.getKeyword() +
                           "', Type='" + criteriaToSubmit.getTypeFilter() +
                           "', Radius='" + criteriaToSubmit.getRadius() + "'");

        if (searchController != null) {
            searchController.handleSubmitSearch(criteriaToSubmit);
        } else {
            System.err.println("Error: SearchController not initialized in SearchView, cannot process form submission.");
        }
        // Clear the searchForm after submission if it was a temporary test criteria
        this.searchForm = null;
    }

    /**
     * Helper method to create default search criteria.
     * This is what a user would 'fill out' if not explicitly setting criteria for testing.
     */
    private SearchCriteria createDefaultSearchCriteria() {
        SearchCriteria defaultCriteria = new SearchCriteria();
        defaultCriteria.setKeyword("museum");
        defaultCriteria.setTypeFilter("historical site");
        defaultCriteria.setRadius(5000);
        return defaultCriteria;
    }

    /**
     * Simulates getting search criteria from a form for the main success path.
     * For this example, it directly creates and returns a dummy criteria object.
     * This method is added to satisfy REQ-006 and REQ-007.
     * (Retained for existing logic compatibility, but its role changes slightly with submitForm())
     * @return A SearchCriteria object populated with user input (simulated).
     */
    public SearchCriteria getSubmittedSearchCriteria() {
        // This method's name is a bit misleading now if it doesn't represent the submission action itself.
        // It's effectively generating a "filled out" form.
        // The print statement "--- User fills out and submits form ---" is moved to submitForm().
        SearchCriteria criteria = createDefaultSearchCriteria();
        // The original method also had a print of the criteria here, which is now done in submitForm().
        return criteria;
    }

    /**
     * Allows setting specific search criteria for testing purposes, which `submitForm()` will then use.
     * This effectively simulates the user "filling out" the form with specific values.
     * @param criteria The SearchCriteria object to be used for the next submission.
     */
    public void setTestSearchCriteria(SearchCriteria criteria) {
        System.out.println("--- SearchView: Setting test search criteria ---");
        this.searchForm = criteria;
    }

    /**
     * Displays the search results to the user.
     * @param results The SearchResult object containing the found cultural heritage items.
     */
    public void displayResults(SearchResult results) { // m28 return (GU receives showSearchResults)
        System.out.println("--- Displaying Search Results (REQ-012: Results accurate & displayed in 2s) ---");
        if (results == null || results.getItems().isEmpty()) {
            System.out.println("No cultural heritage items found for your criteria.");
        } else {
            System.out.println("Found " + results.getItems().size() + " cultural heritage items:");
            for (CulturalHeritage item : results.getItems()) {
                System.out.println("  - ID: " + item.getId() + ", Name: " + item.getName() +
                                   ", Type: " + item.getType() + ", Location: " + item.getLocation().getLatitude() +
                                   ", " + item.getLocation().getLongitude());
            }
        }
        this.searchResults = results; // Store for completeness if needed later
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to be displayed.
     */
    public void displayErrorMessage(String message) {
        System.err.println("--- Displaying Error Message ---");
        System.err.println("Error: " + message);
    }

    /**
     * For demonstration purposes, provides a simple way to get search criteria.
     * In a real application, this would involve UI elements.
     * @return A dummy SearchCriteria object.
     */
    public SearchCriteria getSearchCriteria() {
        // This method is less relevant after getSubmittedSearchCriteria was added,
        // but kept for completeness as per class diagram.
        // It could represent initial criteria setup.
        return new SearchCriteria("defaultKeyword", "defaultType", 1000);
    }
}