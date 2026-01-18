package com.example.domain;

/**
 * Domain object representing search criteria.
 */
public class SearchCriteria {
    private Coordinates location;
    private String siteName;
    private String culturalPeriod;
    private double maxDistance;

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

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