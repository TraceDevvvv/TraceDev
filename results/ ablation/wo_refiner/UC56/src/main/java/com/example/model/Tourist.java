package com.example.model;

/**
 * Represents a tourist with a unique ID, name, and current location.
 */
public class Tourist {
    private String touristId;
    private String name;
    private Location location;

    public Tourist(String touristId, String name) {
        this.touristId = touristId;
        this.name = name;
    }

    public String getTouristId() {
        return touristId;
    }

    public String getName() {
        return name;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}