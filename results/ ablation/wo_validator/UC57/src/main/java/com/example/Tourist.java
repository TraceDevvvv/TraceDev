package com.example;

/**
 * Represents a tourist user who can perform searches.
 */
public class Tourist {
    private SearchCriteria searchCriteria;
    private Location location;
    private boolean authenticated;

    /**
     * Constructor for Tourist.
     */
    public Tourist() {
        this.searchCriteria = null;
        this.location = null;
        this.authenticated = false;
    }

    /**
     * Enables advanced search feature.
     * This method triggers the display of the advanced search form.
     */
    public void enableAdvancedSearch() {
        AdvancedSearchController controller = new AdvancedSearchController();
        controller.displayAdvancedSearchForm();
    }

    /**
     * Fills the search form with given criteria.
     * @param criteria the search criteria to be used.
     */
    public void fillSearchForm(SearchCriteria criteria) {
        this.searchCriteria = criteria;
    }

    /**
     * Submits the search request.
     * This method passes the criteria and location to the controller.
     */
    public void submitSearch() {
        if (searchCriteria == null) {
            System.out.println("Error: No search criteria provided.");
            return;
        }
        AdvancedSearchController controller = new AdvancedSearchController();
        controller.handleSearchSubmission(searchCriteria, location);
    }

    /**
     * Sets the current location of the tourist.
     * @param location the current location.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Authenticates the tourist.
     * @param authenticated authentication status.
     */
    public void setAuthenticated(boolean authenticated) {
        this.authenticated = authenticated;
    }

    /**
     * Gets the current search criteria.
     * @return the search criteria.
     */
    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    /**
     * Gets the current location.
     * @return the location.
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Checks if the tourist is authenticated.
     * @return true if authenticated.
     */
    public boolean isAuthenticated() {
        return authenticated;
    }
}