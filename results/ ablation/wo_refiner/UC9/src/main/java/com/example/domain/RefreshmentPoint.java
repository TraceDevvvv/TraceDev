package com.example.domain;

import java.util.List;

/**
 * Domain entity representing a refreshment point with its attributes and coordinates.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private String category;
    private Coordinates coordinates;
    private List<String> amenities;

    public RefreshmentPoint(String id, String name, String category, Coordinates coordinates, List<String> amenities) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.coordinates = coordinates;
        this.amenities = amenities;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public List<String> getAmenities() {
        return amenities;
    }
}