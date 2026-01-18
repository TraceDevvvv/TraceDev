package com.example.dto;

/**
 * Data Transfer Object for search criteria.
 * Used between boundary and controller.
 */
public class SearchCriteriaDTO {
    private String siteName;
    private String culturalPeriod;
    private double maxDistance;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getCulturalPeriod() {
        return culturalPeriod;
    }

    public void setCulturalPeriod(String culturalPeriod) {
        this.culturalPeriod = culturalPeriod;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }
}