package com.example.domain;

import java.util.Date;

/**
 * Domain Entity representing Cultural Heritage
 */
public class CulturalHeritage {
    private String id;
    private String name;
    private String description;
    private String location;
    private String historicalPeriod;
    private Date creationDate;
    private Date lastModified;

    public CulturalHeritage(String id, String name, String description, String location, 
                           String historicalPeriod, Date creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.historicalPeriod = historicalPeriod;
        this.creationDate = creationDate;
        this.lastModified = creationDate;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastModified() {
        return lastModified;
    }

    /**
     * Updates the details of the cultural heritage item
     */
    public void updateDetails(String name, String description, String location, String period) {
        this.name = name;
        this.description = description;
        this.location = location;
        this.historicalPeriod = period;
        this.lastModified = new Date();
    }

    @Override
    public String toString() {
        return "CulturalHeritage [id=" + id + ", name=" + name + ", location=" + location + "]";
    }
}