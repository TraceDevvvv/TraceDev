package com.example.domain;

import java.util.List;

/**
 * Business/Domain Layer entity representing a point of rest.
 */
public class PointOfRest {
    private int id;
    private String name;
    private String location;
    private List<String> amenities;

    public PointOfRest(int id, String name, String location, List<String> amenities) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.amenities = amenities;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public List<String> getAmenities() {
        return amenities;
    }
}