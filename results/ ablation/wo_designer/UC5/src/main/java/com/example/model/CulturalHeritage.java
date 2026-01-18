package com.example.model;

import java.util.Objects;

/**
 * Represents a cultural heritage item with all its details.
 */
public class CulturalHeritage {
    private int id;
    private String title;
    private String description;
    private String location;
    private String historicalPeriod;
    private String type; // e.g., monument, artifact, painting
    private String imageUrl;
    private boolean isProtected;
    
    public CulturalHeritage() {
    }
    
    public CulturalHeritage(int id, String title, String description, String location, 
                           String historicalPeriod, String type, String imageUrl, boolean isProtected) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.historicalPeriod = historicalPeriod;
        this.type = type;
        this.imageUrl = imageUrl;
        this.isProtected = isProtected;
    }
    
    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    
    public String getHistoricalPeriod() { return historicalPeriod; }
    public void setHistoricalPeriod(String historicalPeriod) { this.historicalPeriod = historicalPeriod; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public boolean isProtected() { return isProtected; }
    public void setProtected(boolean isProtected) { this.isProtected = isProtected; }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CulturalHeritage that = (CulturalHeritage) o;
        return id == that.id;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    @Override
    public String toString() {
        return "CulturalHeritage{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}