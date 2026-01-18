package com.example.dto;

import java.util.List;

/**
 * DTO representing a refreshment point for data transfer.
 * Maps from domain entity RefreshmentPoint.
 */
public class RefreshmentPointDTO {
    private String id;
    private String name;
    private String category;
    private double latitude;
    private double longitude;
    private List<String> amenities;

    public RefreshmentPointDTO(String id, String name, String category, double latitude, double longitude, List<String> amenities) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public List<String> getAmenities() {
        return amenities;
    }
}