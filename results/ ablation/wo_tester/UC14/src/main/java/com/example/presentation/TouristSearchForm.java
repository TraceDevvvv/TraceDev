package com.example.presentation;

import com.example.application.SearchCriteria;
import com.example.domain.Tourist;
import java.util.List;

/**
 * Presentation layer form for tourist search.
 */
public class TouristSearchForm {
    private SearchCriteria criteria;

    public TouristSearchForm() {
        this.criteria = new SearchCriteria();
    }

    public void display() {
        System.out.println("Displaying Tourist Search Form...");
    }

    public SearchCriteria getFormInput() {
        // In a real UI, this would collect input from fields.
        // For simulation, we return the criteria already set.
        return criteria;
    }

    public void showResults(List<Tourist> tourists) {
        if (tourists == null || tourists.isEmpty()) {
            System.out.println("No tourists found.");
        } else {
            System.out.println("Search Results:");
            for (Tourist t : tourists) {
                System.out.println("  - " + t);
            }
        }
    }

    // Helper to set criteria for simulation
    public void setCriteria(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }
}