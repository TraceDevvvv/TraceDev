package com.example;

/**
 * Request object containing search data for site search.
 */
public class SiteSearchRequest {
    private String searchTerm;
    private SearchCriteria searchCriteria;

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public void setSearchCriteria(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }
}