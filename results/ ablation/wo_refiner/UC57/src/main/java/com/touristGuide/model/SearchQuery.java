package com.touristGuide.model;

public class SearchQuery {
    private SearchCriteria criteria;
    private Location userLocation;

    public SearchQuery(SearchCriteria criteria, Location userLocation) {
        this.criteria = criteria;
        this.userLocation = userLocation;
    }

    public SearchCriteria getCriteria() {
        return criteria;
    }

    public Location getUserLocation() {
        return userLocation;
    }
}