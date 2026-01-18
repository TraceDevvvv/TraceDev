package com.example.dto;

/**
 * Data Transfer Object for Site details.
 * Used to transfer data between layers.
 */
public class SiteDTO {
    private final String id;
    private final String name;
    private final String description;
    private final String location;
    private final double rating;

    public SiteDTO(String id, String name, String description, String location, double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.rating = rating;
    }

    // Getters as per UML
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

    public double getRating() {
        return rating;
    }
}