package com.example;

import java.util.List;

/**
 * Boundary class for the Search User Interface.
 */
public class SearchUI {
    private SearchForm currentForm;
    private Results results;

    public SearchUI() {
        this.results = new Results();
    }

    public void showForm() {
        System.out.println("Displaying search form...");
        currentForm = new SearchForm();
    }

    /**
     * Shows the research form to user (sequence diagram message 2)
     */
    public void showResearchForm() {
        System.out.println("2. Showing research form to user");
        showForm();
    }

    /**
     * Shows found sites list to user (sequence diagram message 4)
     */
    public void showFoundSitesList(List<Site> sites) {
        System.out.println("4. Showing found sites list to user");
        displayResults(sites);
    }

    /**
     * Shows validation error to user (sequence diagram message)
     */
    public void showValidationError() {
        System.out.println("Showing validation error to user");
        showValidationError("Validation error occurred");
    }

    public void submitSearchForm(SearchForm form) {
        this.currentForm = form;
        SearchSiteController controller = new SearchSiteController();
        try {
            List<Site> sites = controller.submitSearchForm(form);
            if (sites != null && !sites.isEmpty()) {
                displayResults(sites);
                showFoundSitesList(sites);
            } else {
                showValidationError("No results found.");
            }
        } catch (Exception e) {
            showValidationError("Search failed: " + e.getMessage());
        }
    }

    public void displayResults(List<Site> sites) {
        System.out.println("Displaying search results...");
        results.update(sites);
        results.render();
    }

    public void showValidationError(String message) {
        System.out.println("Validation Error: " + message);
    }

    public SearchForm getCurrentForm() {
        return currentForm;
    }

    public void setCurrentForm(SearchForm currentForm) {
        this.currentForm = currentForm;
    }
}