package com.example.ui;

import com.example.model.SearchCriteria;
import com.example.model.Site;
import com.example.model.SiteSearchResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UI component for site search (simulated UI).
 */
public class SearchFormUI {
    /**
     * Activates site search functionality (as per sequence diagram).
     */
    public void activateSiteSearch() {
        displaySearchForm();
    }

    /**
     * Displays the search form (simulated).
     */
    public void displaySearchForm() {
        System.out.println("Search form displayed.");
    }

    /**
     * Submits a search request (triggered by user).
     *
     * @param formData map of form data
     */
    public void submitSearchRequest(Map<String, String> formData) {
        // In a real application, this would trigger controller via action listener.
        // For simulation, we just collect data and print.
        Map<String, String> data = collectFormData();
        System.out.println("Search request submitted with data: " + data);
    }

    /**
     * Collects form data (simulated).
     *
     * @return map of form data
     */
    protected Map<String, String> collectFormData() {
        // Simulated data collection.
        Map<String, String> data = new HashMap<>();
        data.put("siteName", "TestSite");
        data.put("location", "New York");
        return data;
    }

    /**
     * Displays search results.
     *
     * @param result the search result
     */
    public void displaySearchResults(SiteSearchResult result) {
        if (result.hasError()) {
            System.out.println("Error: " + result.getErrorMessage());
        } else {
            renderResults();
            List<Site> sites = result.getSites();
            System.out.println("Displaying " + (sites != null ? sites.size() : 0) + " sites.");
            if (sites != null) {
                for (Site site : sites) {
                    System.out.println("Site: " + site.getSiteName() + " (" + site.getSiteId() + ")");
                }
            }
            if (!result.isWithinTimeLimit(5000)) {
                System.out.println("Warning: Search took longer than expected (" + result.getExecutionTime() + " ms).");
            }
        }
    }

    /**
     * Renders the results (as per class diagram).
     */
    public void renderResults() {
        System.out.println("Rendering search results...");
    }

    public void ShowSearchForm() {
        displaySearchForm();
    }

    public void DisplaySearchResults(SiteSearchResult result) {
        displaySearchResults(result);
    }

    public void DisplayConnectionError() {
        System.out.println("Connection error displayed.");
    }

    public void DisplayValidationError() {
        System.out.println("Validation error displayed.");
    }
}