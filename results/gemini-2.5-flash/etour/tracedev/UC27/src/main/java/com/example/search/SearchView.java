package com.example.search;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Represents the user interface for search functionality.
 * This class directly maps to the 'SearchView' class in the UML diagram.
 * It's designated as a 'View'.
 */
public class SearchView {
    // Public fields as specified in the UML diagram
    public String searchInput;
    public List<Site> searchResults;

    // Reference to the controller to trigger actions, populated via setController method or constructor
    private SearchController searchController;

    /**
     * Default constructor for SearchView.
     */
    public SearchView() {
        this.searchInput = ""; // Initialize search input
    }

    /**
     * Sets the SearchController for this view. This is a form of dependency injection.\
     * @param controller The SearchController instance.
     */
    public void setSearchController(SearchController controller) {
        this.searchController = controller;
    }

    /**
     * Displays the search form to the user.
     * Implements part of the sequence diagram flow (Step 2).
     */
    public void displaySearchForm() {
        System.out.println("  [SearchView] displaySearchForm()");
        System.out.println("-------------------------------------");
        System.out.println("           SITE SEARCH FORM          ");
        System.out.println("-------------------------------------");
        System.out.println("Enter search criteria (e.g., name=ruins, location=greece):");
        System.out.println("Type 'exit' to quit.");
        System.out.print("> ");
    }

    /**
     * Simulates getting user search criteria.
     * This method is marked as 'Removed to satisfy requirement R6 (semantic correction)'
     * from direct user interaction in the sequence diagram.
     * It can be used internally by the view to parse user input before calling submitSearchForm.
     * For this implementation, we will simulate user input via Main class.
     * @return A map of criteria based on user input.
     */
    private Map<String, String> getUserSearchCriteria() {
        // This method is conceptually "internal" to how the view gathers input
        // before invoking the controller's submitSearchForm.
        // For actual runnable code, we'll simulate input being directly passed to submitSearchForm.
        // Or, a real UI would populate this from form fields.
        return new HashMap<>(); // Placeholder, actual criteria will come from submitSearchForm(criteria)
    }

    /**
     * Displays the search results to the user.
     * Implements part of the sequence diagram flow (Successful Search -> displaySearchResults).
     * @param results The list of Site objects to display.
     */
    public void displaySearchResults(List<Site> results) {
        System.out.println("  [SearchView] displaySearchResults(List<Site>)");
        System.out.println("-------------------------------------");
        System.out.println("          SEARCH RESULTS             ");
        System.out.println("-------------------------------------");
        if (results == null || results.isEmpty()) {
            System.out.println("No sites found matching your criteria.");
        } else {
            results.forEach(site -> System.out.println("- " + site.toString()));
        }
        System.out.println("-------------------------------------");
    }

    /**
     * Displays an error message to the user.
     * Implements part of the sequence diagram flow (Error handling -> displayErrorMessage).\
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.out.println("  [SearchView] displayErrorMessage(\"" + message + "\")");
        System.err.println("-------------------------------------");
        System.err.println("           ERROR MESSAGE             ");
        System.err.println("-------------------------------------");
        System.err.println("Error: " + message);
        System.err.println("-------------------------------------");
    }

    /**
     * Activates the search functionality.
     * This method is called by the User actor (R4).
     * It delegates to the SearchController to initiate the search process.
     */
    public void activateSearch() {
        System.out.println("[SearchView] activateSearch()");
        if (searchController != null) {
            // Sequence Diagram: SearchView -> SearchController : activateSearchFunctionality()
            searchController.activateSearchFunctionality();
        } else {
            System.err.println("Error: SearchController not set for SearchView.");
        }
    }

    /**
     * Submits the search criteria to the controller.
     * This method is called by the User actor (R7).
     * Corresponds to message 'submitSearchForm(criteria)' in the sequence diagram.
     * @param criteria The search criteria provided by the user.
     */
    public void submitSearchForm(Map<String, String> criteria) {
        System.out.println("[SearchView] submitSearchForm(" + criteria + ")");
        this.searchInput = criteria.toString(); // Store criteria as input string for view
        if (searchController != null) {
            // Sequence Diagram: SearchView -> SearchController : handleSearchRequest(criteria)
            searchController.handleSearchRequest(criteria);
        } else {
            System.err.println("Error: SearchController not set for SearchView.");
        }
    }
}