package com.example;

import java.util.List;

/**
 * Represents a cultural heritage item with its attributes.
 */
public class CulturalHeritage {
    private String id;
    private String name;
    private String description;
    private String location;
    private String historicalPeriod;
    private List<String> images;

    public CulturalHeritage() {
    }

    public CulturalHeritage(String id, String name, String description, String location, String historicalPeriod, List<String> images) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.historicalPeriod = historicalPeriod;
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getHistoricalPeriod() {
        return historicalPeriod;
    }

    public List<String> getImages() {
        return images;
    }

    // Setters for completeness (though not used in diagrams)
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHistoricalPeriod(String historicalPeriod) {
        this.historicalPeriod = historicalPeriod;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}