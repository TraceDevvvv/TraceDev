package com.example.dto;

import java.util.List;

/**
 * Request DTO carrying search criteria from the presentation layer.
 */
public class SearchRefreshmentPointRequestDTO {
    private String name;
    private String category;
    private Double maxDistance;
    private List<String> amenities;

    // Default constructor and getters/setters for flexibility in binding.
    public SearchRefreshmentPointRequestDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(Double maxDistance) {
        this.maxDistance = maxDistance;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }
}