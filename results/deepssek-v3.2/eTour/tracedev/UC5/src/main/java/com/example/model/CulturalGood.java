package com.example.model;

/**
 * Entity representing a cultural good.
 * Contains attributes as per class diagram.
 */
public class CulturalGood {
    private String id;
    private String title;
    private String description;
    private String location;
    private String period;

    public CulturalGood(String id, String title, String description, String location, String period) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.period = period;
    }

    // Getters as per class diagram
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getPeriod() {
        return period;
    }

    // Setters for missing attributes to ensure full traceability
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setPeriod(String period) {
        this.period = period;
    }
}