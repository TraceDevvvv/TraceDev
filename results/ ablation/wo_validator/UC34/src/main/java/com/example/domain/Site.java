package com.example.domain;

/**
 * Domain entity representing a cultural heritage site.
 */
public class Site {
    private String id;
    private String name;
    private String description;
    private Coordinates location;
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

    public Coordinates getLocation() {
        return location;
    }

    public void setLocation(Coordinates location) {
        this.location = location;
    }

    public String getCulturalPeriod() {
        return culturalPeriod;
    }

    public void setCulturalPeriod(String culturalPeriod) {
        this.culturalPeriod = culturalPeriod;
    }
}