package com.example.domain;

/**
 * Represents a tourist.
 * This is a core domain entity.
 */
public class Tourist {
    public String touristId;
    public String name;

    // Constructor
    public Tourist(String touristId, String name) {
        this.touristId = touristId;
        this.name = name;
    }

    // Getters for all attributes
    public String getTouristId() {
        return touristId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Tourist{" +
               "touristId='" + touristId + '\'' +
               ", name='" + name + '\'' +
               '}';
    }
}