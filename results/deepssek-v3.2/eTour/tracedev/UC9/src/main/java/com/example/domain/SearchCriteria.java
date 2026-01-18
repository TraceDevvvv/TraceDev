package com.example.domain;

/**
 * Domain object representing search criteria.
 */
public class SearchCriteria {
    private String locationFilter;
    private RefreshmentPointType typeFilter;
    private int maxDistance;

    // Constructors
    public SearchCriteria() {}

    public SearchCriteria(String locationFilter, RefreshmentPointType typeFilter, int maxDistance) {
        this.locationFilter = locationFilter;
        this.typeFilter = typeFilter;
        this.maxDistance = maxDistance;
    }

    // Getters and Setters
    public String getLocationFilter() {
        return locationFilter;
    }

    public void setLocationFilter(String locationFilter) {
        this.locationFilter = locationFilter;
    }

    public RefreshmentPointType getTypeFilter() {
        return typeFilter;
    }

    public void setTypeFilter(RefreshmentPointType typeFilter) {
        this.typeFilter = typeFilter;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(int maxDistance) {
        this.maxDistance = maxDistance;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "locationFilter='" + locationFilter + '\'' +
                ", typeFilter=" + typeFilter +
                ", maxDistance=" + maxDistance +
                '}';
    }
}