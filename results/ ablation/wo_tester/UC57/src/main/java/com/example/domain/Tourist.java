package com.example.domain;

import com.example.application.SearchResultDTO;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a tourist user who can perform advanced searches for sites.
 */
public class Tourist {
    private String id;
    private String name;
    private String location;
    private List<String> searchHistory;

    public Tourist(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.searchHistory = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<String> getSearchHistory() {
        return searchHistory;
    }

    /**
     * Authenticates the tourist.
     * For simplicity, returns true always; in reality, would check credentials.
     */
    public boolean authenticate() {
        // Placeholder authentication logic
        return true;
    }

    /**
     * Enables advanced search functionality.
     * In this implementation, it simply logs the action.
     */
    public void enableAdvancedSearch() {
        System.out.println("Advanced search enabled for tourist " + name);
    }

    /**
     * Views the advanced search form, returning a new form instance.
     */
    public AdvancedSearchForm viewAdvancedSearchForm() {
        return new AdvancedSearchForm();
    }

    /**
     * Fills the search form with given criteria.
     * This is a helper method for the sequence diagram.
     */
    public void fillForm(SearchCriteria criteria) {
        // In the sequence diagram, this is an internal Tourist action.
        System.out.println("Tourist filled criteria: " + criteria.getName());
    }

    /**
     * Submits the form and triggers the search process.
     * This method simulates the interaction with the form and controller.
     */
    public SearchResultDTO submitForm(AdvancedSearchForm form, AdvancedSearchController controller) {
        // This simulates the Tourist submitting the form and receiving results.
        // In reality, the form would call controller.executeSearch.
        // For simplicity, we directly call executeSearch.
        SearchCriteria criteria = form.getCriteria();
        if (criteria == null) {
            System.out.println("No criteria set in form.");
            return null;
        }
        return controller.executeSearch(this.id, criteria);
    }
}