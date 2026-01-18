package com.example.model;

import com.example.ui.SearchFormUI;

import java.util.Map;

/**
 * Represents a user who performs search.
 */
public class User {
    public String userId;
    public Map<String, Object> searchCriteria;

    public User(String userId) {
        this.userId = userId;
    }

    /**
     * Fills the search form with criteria (simulated).
     * @param criteria the search criteria
     */
    public void fillSearchForm(Map<String, Object> criteria) {
        this.searchCriteria = criteria;
        // In real scenario, would pass to UI
    }

    /**
     * Submits the search form (simulated).
     */
    public void submitSearchForm() {
        // In real scenario, would trigger UI submission
        System.out.println("User " + userId + " submitted search form.");
    }

    // Simulate the sequence diagram interactions
    public void performSearch(SearchFormUI ui) {
        ui.activateSearch();
        ui.fillSearchForm(this.searchCriteria);
        ui.submitSearchForm();
    }
}