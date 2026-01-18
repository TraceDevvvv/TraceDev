package com.example.dto;

/**
 * Data Transfer Object for search criteria.
 * Includes user location for position/citing (REQ-009).
 */
public class SearchCriteriaDTO {
    private String keywords;
    private String location;
    private String heritageType;
    private String dateRange;
    private String userLocation; // Added for REQ-009

    // Constructors
    public SearchCriteriaDTO() {
    }

    public SearchCriteriaDTO(String keywords, String location, String heritageType, String dateRange, String userLocation) {
        this.keywords = keywords;
        this.location = location;
        this.heritageType = heritageType;
        this.dateRange = dateRange;
        this.userLocation = userLocation;
    }

    // Getters and Setters
    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHeritageType() {
        return heritageType;
    }

    public void setHeritageType(String heritageType) {
        this.heritageType = heritageType;
    }

    public String getDateRange() {
        return dateRange;
    }

    public void setDateRange(String dateRange) {
        this.dateRange = dateRange;
    }

    public String getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(String userLocation) {
        this.userLocation = userLocation;
    }

    /**
     * Returns the position (user location) as per REQ-009.
     */
    public String getPosition() {
        return userLocation;
    }

    /**
     * Validates the search criteria (REQ-008).
     * Assumes at least one field must be non-empty.
     */
    public boolean validate() {
        return (keywords != null && !keywords.trim().isEmpty()) ||
               (location != null && !location.trim().isEmpty()) ||
               (heritageType != null && !heritageType.trim().isEmpty()) ||
               (dateRange != null && !dateRange.trim().isEmpty()) ||
               (userLocation != null && !userLocation.trim().isEmpty());
    }
}