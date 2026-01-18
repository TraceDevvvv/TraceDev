package com.example.dto;

import java.io.Serializable;

/**
 * Data Transfer Object for Cultural Heritage
 */
public class HeritageDTO implements Serializable {
    private String id;
    private String name;
    private String description;
    private String location;
    private String historicalPeriod;

    public HeritageDTO(String id, String name, String description, String location, String period) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.historicalPeriod = period;
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

    @Override
    public String toString() {
        return "HeritageDTO [id=" + id + ", name=" + name + ", location=" + location + "]";
    }
}