package com.example.application;

import java.util.List;

/**
 * Query object representing the search criteria for refreshment points.
 */
public class SearchRefreshmentPointQuery {
    private String name;
    private String category;
    private Double maxDistance;
    private List<String> amenities;

    public SearchRefreshmentPointQuery(String name, String category, Double maxDistance, List<String> amenities) {
        this.name = name;
        this.category = category;
        this.maxDistance = maxDistance;
        this.amenities = amenities;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Double getMaxDistance() {
        return maxDistance;
    }

    public List<String> getAmenities() {
        return amenities;
    }
}