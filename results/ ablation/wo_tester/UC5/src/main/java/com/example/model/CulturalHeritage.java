package com.example.model;

/**
 * Domain model representing Cultural Heritage entity.
 */
public class CulturalHeritage {
    private String id;
    private String name;
    private String description;
    private String location;
    private String historicalPeriod;
    // other domain attributes could be added here
    
    public CulturalHeritage() {
        // default constructor
    }
    
    public CulturalHeritage(String id, String name, String description, 
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
    
    /**
     * Converts this domain object to a DTO.
     * Added to satisfy requirement for Flow of Events #4.
     * @return CulturalHeritageDTO representation
     */
    public CulturalHeritageDTO toDTO() {
        return new CulturalHeritageDTO(
            this.id,
            this.name,
            this.description,
            this.location,
            this.historicalPeriod
        );
    }
    
    @Override
    public String toString() {
        return "CulturalHeritage{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", historicalPeriod='" + historicalPeriod + '\'' +
                '}';
    }
}