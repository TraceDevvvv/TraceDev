package com.etour.search;

import java.util.List;

/**
 * Boundary class representing the search form interface.
 * Implements availability check as per quality requirement.
 */
public class SearchForm {
    private SearchCriteria searchCriteria;
    private SearchController searchController;

    public SearchForm(SearchController searchController) {
        this.searchController = searchController;
        this.searchCriteria = new SearchCriteria();
    }

    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    /**
     * Displays the form to the user.
     */
    public void displayForm() {
        System.out.println("Displaying search form...");
    }

    /**
     * Returns the current form data.
     * @return SearchCriteria object containing user input.
     */
    public SearchCriteria getFormData() {
        return searchCriteria;
    }

    /**
     * Shows search results to the user.
     * @param sites List of sites matching the search criteria.
     */
    public void showResults(List<Site> sites) {
        System.out.println("Displaying " + sites.size() + " search results:");
        for (Site site : sites) {
            System.out.println(" - " + site.getName() + " (" + site.getCategory() + ")");
        }
    }

    /**
     * Activates the search process.
     * Called by the guest user to start searching.
     */
    public void activateSearch() {
        System.out.println("Search activated.");
        displayForm();
    }

    /**
     * Accepts filled form data from the user and submits to controller.
     * @param criteria Search criteria filled by the user.
     */
    public void fillForm(SearchCriteria criteria) {
        this.searchCriteria = criteria;
        System.out.println("Form filled with criteria: " + criteria.getSearchString());
        submitForm(criteria);
    }

    /**
     * Submits the form data to the search controller.
     * @param criteria Search criteria to process.
     */
    public void submitForm(SearchCriteria criteria) {
        System.out.println("Submitting form to controller...");
        searchController.processFormSubmission(criteria);
    }

    /**
     * Displays search results to the guest user.
     */
    public void displaySearchResults() {
        System.out.println("Search results displayed to user.");
    }

    /**
     * Displays an error message to the guest user.
     */
    public void displayError() {
        System.out.println("An error occurred. Please try again later.");
    }

    /**
     * Shows an error message on the form.
     * @param message Error message to display.
     */
    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
        displayError();
    }

    /**
     * Performs an availability check of the system.
     * Quality Requirement: availability
     * @return true if system is available, false otherwise.
     */
    public boolean availabilityCheck() {
        System.out.println("Performing availability check...");
        boolean available = searchController.healthCheck();
        System.out.println("System available: " + available);
        return available;
    }

    /**
     * Gets system status from search controller.
     * @return systemStatus:boolean
     */
    public boolean getSystemStatus() {
        System.out.println("Getting system status from controller...");
        boolean systemStatus = searchController.healthCheck();
        System.out.println("System status: " + systemStatus);
        return systemStatus;
    }
}