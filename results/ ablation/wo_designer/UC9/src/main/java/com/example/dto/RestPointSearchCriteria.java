package com.example.dto;

/**
 * DTO representing the search criteria for points of rest.
 */
public class RestPointSearchCriteria {
    private String name;
    private String location;
    private Integer maxDistance; // in kilometers
    private Boolean hasShelter;
    private Boolean hasWater;
    private Integer minRating;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(Integer maxDistance) {
        this.maxDistance = maxDistance;
    }

    public Boolean getHasShelter() {
        return hasShelter;
    }

    public void setHasShelter(Boolean hasShelter) {
        this.hasShelter = hasShelter;
    }

    public Boolean getHasWater() {
        return hasWater;
    }

    public void setHasWater(Boolean hasWater) {
        this.hasWater = hasWater;
    }

    public Integer getMinRating() {
        return minRating;
    }

    public void setMinRating(Integer minRating) {
        this.minRating = minRating;
    }
}