package com.example.dto;

/**
 * Data Transfer Object for a cultural heritage site.
 * Used to transfer site data to the presentation layer.
 */
public class SiteDTO {
    private String id;
    private String name;
    private String description;
    private double latitude;
    private double longitude;
    private String culturalPeriod;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getCulturalPeriod() {
        return culturalPeriod;
    }

    public void setCulturalPeriod(String culturalPeriod) {
        this.culturalPeriod = culturalPeriod;
    }
}