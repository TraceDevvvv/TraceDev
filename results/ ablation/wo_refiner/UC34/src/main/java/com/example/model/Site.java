package com.example.model;

/**
 * Entity class representing a heritage site.
 */
public class Site {
    private int id;
    private String name;
    private String description;
    private String location;
    private String heritageType;
    private String historicalPeriod;

    // Constructors
    public Site() {
    }

    public Site(int id, String name, String description, String location, String heritageType, String historicalPeriod) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.heritageType = heritageType;
        this.historicalPeriod = historicalPeriod;
    }

    // Getters
    public int getId() {
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

    public String getHeritageType() {
        return heritageType;
    }

    public String getHistoricalPeriod() {
        return historicalPeriod;
    }

    // Setters (if needed, not in diagram but included for completeness)
    public void setId(int id) {
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

    public void setHeritageType(String heritageType) {
        this.heritageType = heritageType;
    }

    public void setHistoricalPeriod(String historicalPeriod) {
        this.historicalPeriod = historicalPeriod;
    }
}