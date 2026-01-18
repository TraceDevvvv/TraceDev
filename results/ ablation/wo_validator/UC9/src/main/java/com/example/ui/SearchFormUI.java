package com.example.ui;

import com.example.controller.SearchControllerInterface;
import com.example.dto.PointOfRestDTO;

import java.util.List;
import java.util.Map;

/**
 * UI component for search form.
 */
public class SearchFormUI {
    private Object searchForm; // In real implementation, would be a UI Form object
    private SearchControllerInterface searchController;

    // Assumption: UI gets controller via dependency injection or setter
    public SearchFormUI() {
        // Default constructor
    }

    public void setSearchController(SearchControllerInterface controller) {
        this.searchController = controller;
    }

    /**
     * Displays the search form (simulated).
     */
    public void displayForm() {
        // In a real UI, this would render the form
        System.out.println("Search form displayed.");
    }

    /**
     * Collects form data (simulated).
     * @return map of criteria
     */
    public Map<String, Object> collectFormData() {
        // Simulate collecting data from UI fields
        // For simplicity, return an empty map
        return Map.of("location", "Highway", "facility", "Parking");
    }

    /**
     * Submits the form and triggers search.
     */
    public void submitForm() {
        if (searchController == null) {
            throw new IllegalStateException("SearchController not set");
        }
        Map<String, Object> criteria = collectFormData();
        try {
            List<PointOfRestDTO> results = searchController.handleSearchRequest(criteria);
            formatResults(results);
        } catch (Exception e) {
            System.err.println("Error during search: " + e.getMessage());
            // In real UI, would show error message to user
        }
    }

    /**
     * Formats and displays search results.
     * @param results list of DTOs
     */
    public void formatResults(List<PointOfRestDTO> results) {
        System.out.println("=== Search Results ===");
        for (PointOfRestDTO dto : results) {
            System.out.println("- " + dto.name + " at " + dto.location + " (ID: " + dto.id + ")");
            System.out.println("  Facilities: " + String.join(", ", dto.facilities));
        }
        System.out.println("======================");
    }

    // Additional method to simulate the sequence diagram flow
    public void activateSearch() {
        displayForm();
    }

    public void fillSearchForm(Map<String, Object> criteria) {
        // Simulate updating form with criteria
        System.out.println("Form filled with criteria: " + criteria);
    }

    public void submitSearchForm() {
        submitForm();
    }
}