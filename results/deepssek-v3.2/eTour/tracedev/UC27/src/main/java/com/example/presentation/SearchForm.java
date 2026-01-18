package com.example.presentation;

import com.example.controller.SearchSiteController;
import com.example.dto.SearchRequest;
import com.example.dto.SearchResponse;
import com.example.dto.SiteDTO;
import java.util.List;

/**
 * Presentation layer boundary for the search form.
 */
public class SearchForm {
    private String criteriaField;
    private Object submitButton; // In a real application, this would be a UI button.
    private SearchSiteController controller;

    /**
     * Constructs a SearchForm with a controller.
     * @param controller the search site controller
     */
    public SearchForm(SearchSiteController controller) {
        this.controller = controller;
    }

    /**
     * Activates the search form (Flow 1).
     */
    public void activateSearch() {
        // Typically, this would initialize the form.
        System.out.println("Search form activated.");
    }

    /**
     * Shows the form (Flow 2).
     */
    public void show() {
        // Display the search form. In a real application, this would render the UI.
        System.out.println("Displaying search form.");
    }

    /**
     * Fills the form with criteria (Flow 3).
     * @param criteria the search criteria
     */
    public void fillForm(String criteria) {
        this.criteriaField = criteria;
        System.out.println("Form filled with criteria: " + criteria);
    }

    /**
     * Submits the form (Flow 4).
     */
    public void submit() {
        // Create a search request from the form data
        SearchRequest request = createSearchRequest();
        // Call the controller to perform the search
        SearchResponse response = controller.searchSite(request);

        // Handle the response
        if (response.isSuccessful()) {
            displayResults(response.getSites());
        } else {
            displayError(response.getErrorMessage());
        }
    }

    /**
     * Gets the search criteria from the form.
     * @return the search criteria
     */
    public String getSearchCriteria() {
        return criteriaField;
    }

    /**
     * Creates a SearchRequest from the form data.
     * @return the search request
     */
    public SearchRequest createSearchRequest() {
        return new SearchRequest(criteriaField);
    }

    /**
     * Displays the search results.
     * @param sites the list of site DTOs
     */
    public void displayResults(List<SiteDTO> sites) {
        System.out.println("Displaying search results:");
        for (SiteDTO site : sites) {
            System.out.println("  " + site);
        }
    }

    /**
     * Displays an error message.
     * @param message the error message
     */
    public void displayError(String message) {
        System.out.println("Error: " + message);
    }
}