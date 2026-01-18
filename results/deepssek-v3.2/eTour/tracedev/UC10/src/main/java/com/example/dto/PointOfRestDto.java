package com.example.dto;

import java.util.List;

/**
 * Presentation Layer DTO for Point of Rest details.
 */
public class PointOfRestDto {
    private int id;
    private String name;
    private String location;
    private List<String> amenities;

    public PointOfRestDto(int id, String name, String location, List<String> amenities) {
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