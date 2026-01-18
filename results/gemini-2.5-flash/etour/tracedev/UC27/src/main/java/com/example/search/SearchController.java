package com.example.search;

import java.util.List;
import java.util.Map;

/**
 * Controller responsible for mediating between the Search View and Search Service.
 * This class directly maps to the 'SearchController' class in the UML diagram.
 * It's designated as a 'Controller'.
 */
public class SearchController {
    // Private fields as specified in the UML diagram (relationships 'uses' and 'manages')
    private SearchService searchService;
    private SearchView searchView;

    /**
     * Constructor for SearchController.
     * @param searchService The search service to use for business logic.
     * @param searchView The view to manage and interact with.
     */
    public SearchController(SearchService searchService, SearchView searchView) {
        this.searchService = searchService;
        this.searchView = searchView;
        // The view needs to know its controller to call back (e.g., submitSearch)
        this.searchView.setSearchController(this);
    }

    /**
     * Handles an incoming search request from the view.
     * This method implements a core part of the sequence diagram,
     * including error handling for connection issues and timeouts.
     * @param criteria A map of key-value pairs representing search criteria.
     */
    public void handleSearchRequest(Map<String, String> criteria) {
        System.out.println("  [SearchController] handleSearchRequest(" + criteria + ")");
        try {
            // Sequence Diagram: SearchController -> SearchService : searchSites(criteria)
            List<Site> results = searchService.searchSites(criteria);
            // Sequence Diagram: SearchService --> SearchController : return List<Site> (on success)
            System.out.println("  [SearchController] Received " + results.size() + " results from SearchService.");

            // Sequence Diagram: SearchController -> SearchView : displaySearchResults(List<Site>)
            searchView.displaySearchResults(results);
        } catch (ConnectionError e) {
            // Sequence Diagram: SearchService --x SearchController : throws ConnectionError("ETOUR")
            System.out.println("  [SearchController] Caught ConnectionError: " + e.message);
            // Sequence Diagram: SearchController -> SearchView : displayErrorMessage("Connection interrupted (ETOUR)")
            searchView.displayErrorMessage("Connection error: Cannot retrieve site information. Details: " + e.message);
        } catch (TimeLimitExceededException e) {
            // Sequence Diagram: SearchService --x SearchController : throws TimeLimitExceededException("Search took too long")
            System.out.println("  [SearchController] Caught TimeLimitExceededException: " + e.message);
            // Sequence Diagram: SearchController -> SearchView : displayErrorMessage("Search timed out")
            searchView.displayErrorMessage("Search took too long. Please refine criteria. Details: " + e.message);
        }
        System.out.println("  [SearchController] Deactivating.");
    }

    /**
     * Activates the search functionality by instructing the view to display the search form.
     * Implements part of the sequence diagram's activation flow.
     */
    public void activateSearchFunctionality() {
        System.out.println("  [SearchController] activateSearchFunctionality()");
        // Sequence Diagram: SearchController -> SearchView : displaySearchForm()
        searchView.displaySearchForm();
        System.out.println("  [SearchController] Deactivating.");
    }
}