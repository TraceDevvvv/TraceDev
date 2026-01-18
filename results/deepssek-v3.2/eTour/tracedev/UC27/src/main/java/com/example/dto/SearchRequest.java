package com.example.dto;

/**
 * Data transfer object for search request.
 * Contains the search criteria entered by the user.
 */
public class SearchRequest {
    private String searchCriteria;

    /**
     * Default constructor.
     */
    public SearchRequest() {}

    /**
     * Constructs a SearchRequest with the specified criteria.
     * @param searchCriteria the search criteria
     */
    public SearchRequest(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    /**
     * Gets the search criteria.
     * @return the search criteria
     */
    public String getSearchCriteria() {
        return searchCriteria;
    }

    /**
     * Sets the search criteria.
     * @param searchCriteria the search criteria
     */
    public void setSearchCriteria(String searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}