package com.etour.controllers;

import com.etour.adapters.SearchForm;
import com.etour.adapters.SearchQuery;
import com.etour.adapters.SearchResult;
import com.etour.usecases.SearchUseCase;

/**
 * Controller that handles search requests from the UI.
 * Receives a SearchForm, converts it to SearchQuery, and delegates to the use case.
 */
public class SearchController {
    private final SearchUseCase searchUseCase;

    public SearchController(SearchUseCase searchUseCase) {
        this.searchUseCase = searchUseCase;
    }

    /**
     * Handles a search request (step 5 in sequence diagram).
     * @param form the search form data
     * @return the search result
     */
    public SearchResult handleSearch(SearchForm form) {
        // Convert form to query
        SearchQuery query = form.submit();
        // Delegate to use case (step 6)
        return searchUseCase.execute(query);
    }

    /**
     * Alternative method that directly accepts a SearchQuery (as per sequence diagram step 5).
     * This matches the arrow label in the diagram.
     */
    public SearchResult handleSearch(SearchQuery query) {
        return searchUseCase.execute(query);
    }

    /**
     * Displays search results to the user (step 9).
     * In a real application, this might update a view.
     */
    public void displayResults(SearchResult result) {
        System.out.println("Displaying search results:");
        System.out.println("Total objects found: " + result.getTotalCount());
        result.getCulturalObjects().forEach(obj -> 
            System.out.println(" - " + obj.getTitle() + " (" + obj.getType() + ") at " + obj.getLocation())
        );
    }

    /**
     * Displays an error message to the user (step 10).
     */
    public void displayErrorMessage(String message) {
        System.err.println("Error: " + message);
    }
}