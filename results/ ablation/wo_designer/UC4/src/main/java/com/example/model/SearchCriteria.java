package com.example.model;

/**
 * Represents the search criteria for cultural objects.
 * All fields are optional - if a field is null or empty, it is ignored in the search.
 */
public class SearchCriteria {
    private String name;
    private String type;
    private String location;
    private String era;

    public SearchCriteria() {
    }

    public SearchCriteria(String name, String type, String location, String era) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.era = era;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEra() {
        return era;
    }

    public void setEra(String era) {
        this.era = era;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", era='" + era + '\'' +
                '}';
    }
}