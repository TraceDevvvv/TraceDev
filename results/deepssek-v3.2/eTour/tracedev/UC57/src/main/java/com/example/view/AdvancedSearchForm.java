package com.example.view;

import com.example.model.SearchRequest;
import com.example.model.Site;
import com.example.controller.SiteSearchController;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * The advanced search form used by the tourist to input filters and submit search.
 */
public class AdvancedSearchForm {
    private Map<String, Object> filters;

    public AdvancedSearchForm() {
        this.filters = new HashMap<>();
    }

    /**
     * Displays the form to the tourist.
     */
    public void display() {
        System.out.println("Advanced search form displayed.");
    }

    /**
     * Called when tourist fills the form with filters.
     */
    public void fillForm(Map<String, Object> filters) {
        this.filters = filters;
        System.out.println("Form filled with filters: " + filters);
    }

    /**
     * Creates a SearchRequest from the current filters.
     */
    public SearchRequest createSearchRequest() {
        return new SearchRequest(filters);
    }

    /**
     * Submits the form and returns the created SearchRequest.
     */
    public SearchRequest submit() {
        System.out.println("Form submitted.");
        return createSearchRequest();
    }

    /**
     * Displays the list of sites returned from the search.
     */
    public void displaySiteList(List<Site> sites) {
        System.out.println("Displaying site list:");
        for (Site site : sites) {
            System.out.println(" - " + site.getName() + " (ID: " + site.getId() + ")");
        }
    }

    /**
     * Enables advanced search (triggered by tourist).
     * This method corresponds to the sequence diagram step.
     */
    public void enableAdvancedSearch() {
        display();
    }

    /**
     * Submits the form and passes request to controller.
     * This method corresponds to the sequence diagram step.
     */
    public void submitForm(SiteSearchController controller) {
        SearchRequest request = submit();
        List<Site> sites = controller.handleAdvancedSearch(request);
        displaySiteList(sites);
    }

    public void displayForm() {
        display();
    }

    public void showSiteList(List<Site> sites) {
        displaySiteList(sites);
    }
}