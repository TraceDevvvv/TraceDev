package com.example.dto;

import java.io.Serializable;

/**
 * Data Transfer Object for search criteria (Presentation <-> Application layer).
 * Maps to SearchCriteria domain object.
 */
public class SearchCriteriaDTO implements Serializable {
    public String locationFilter;
    public String typeFilter;
    public int maxDistance;

    // Constructors
    public SearchCriteriaDTO() {}

    public SearchCriteriaDTO(String locationFilter, String typeFilter, int maxDistance) {
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

    public String getTypeFilter() {
        return typeFilter;
    }

    public void setTypeFilter(String typeFilter) {
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
        return "SearchCriteriaDTO{" +
                "locationFilter='" + locationFilter + '\'' +
                ", typeFilter='" + typeFilter + '\'' +
                ", maxDistance=" + maxDistance +
                '}';
    }
}