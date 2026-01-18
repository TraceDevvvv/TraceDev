package com.example.model;

import java.io.Serializable;

/**
 * Data Transfer Object for CulturalHeritage.
 * Used to transfer data without exposing domain model details.
 */
public class CulturalHeritageDTO implements Serializable {
    private String id;
    private String name;
    private String description;
    private String location;
    private String historicalPeriod;
    
    public CulturalHeritageDTO() {
        // default constructor
    }
    
    public CulturalHeritageDTO(String id, String name, String description, 
                               String location, String historicalPeriod) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.historicalPeriod = historicalPeriod;
    }
    
    // Getters and setters
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
    
    public String getLocation() {
        return location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }
    
    public String getHistoricalPeriod() {
        return historicalPeriod;
    }
    
    public void setHistoricalPeriod(String historicalPeriod) {
        this.historicalPeriod = historicalPeriod;
    }
    
    @Override
    public String toString() {
        return "CulturalHeritageDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", historicalPeriod='" + historicalPeriod + '\'' +
                '}';
    }
}