package com.example.view;

import com.example.dto.SearchCriteriaDTO;
import com.example.dto.SiteDTO;
import java.util.List;

/**
 * View component in MVC, representing the search form and results display.
 * Supports GuestUser (REQ-003) and handles connection errors (REQ-011).
 */
public class SearchFormView {
    /**
     * Activates search functionality (REQ-006).
     */
    public void activateSearch() {
        System.out.println("Search functionality activated.");
    }

    /**
     * Displays the search form (REQ-007).
     */
    public void displayForm() {
        System.out.println("Displaying search form...");
    }

    /**
     * Gets search criteria from the user (simulated).
     */
    public SearchCriteriaDTO getSearchCriteria() {
        // In a real app, this would collect input from UI.
        return new SearchCriteriaDTO("castle", "France", "Medieval", "1200-1500", "Paris");
    }

    /**
     * Displays search results.
     */
    public void displayResults(List<SiteDTO> results) {
        System.out.println("=== Search Results ===");
        for (SiteDTO site : results) {
            System.out.println("ID: " + site.getId() + ", Name: " + site.getName() + ", Location: " + site.getLocation());
        }
        System.out.println("Total sites found: " + results.size());
    }

    /**
     * Shows an error message.
     */
    public void showErrorMessage(String message) {
        System.err.println("Error: " + message);
    }

    /**
     * Handles connection errors (REQ-011).
     */
    public void handleConnectionError() {
        System.err.println("View handled connection error: informing user.");
    }
}