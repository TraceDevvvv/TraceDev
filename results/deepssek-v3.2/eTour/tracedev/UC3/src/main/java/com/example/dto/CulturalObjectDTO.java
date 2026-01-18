package com.example.dto;

/**
 * DTO for CulturalObject data transfer between layers.
 */
public class CulturalObjectDTO {
    private int id;
    private String name;
    private String description;
    private String location;
    private String historicalPeriod;

    public CulturalObjectDTO() {}

    public CulturalObjectDTO(int id, String name, String description, String location, String historicalPeriod) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.historicalPeriod = historicalPeriod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}