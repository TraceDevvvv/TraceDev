package com.example.model;

import java.util.Map;

/**
 * Represents a search request containing criteria and tourist location.
 */
public class SearchRequest {
    private SearchCriteria searchCriteria;
    private Location touristLocation;

    public SearchRequest(Map<String, Object> filters) {
        this.searchCriteria = new SearchCriteria(filters);
        // Location will be set later by controller using tourist's location
        this.touristLocation = null;
    }

    public SearchCriteria getSearchCriteria() {
        return searchCriteria;
    }

    public Location getTouristLocation() {
        return touristLocation;
    }

    public void setTouristLocation(Location touristLocation) {
        this.touristLocation = touristLocation;
    }
}