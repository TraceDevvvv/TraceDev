package com.example.domain;

import java.util.List;

/**
 * Domain entity representing a refreshment point.
 */
public class RefreshmentPoint {
    private String id;
    private String name;
    private String location;
    private RefreshmentPointType type;
    private List<String> amenities;

    // Constructors
    public RefreshmentPoint() {}

    public RefreshmentPoint(String id, String name, String location, RefreshmentPointType type, List<String> amenities) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.type = type;
        this.amenities = amenities;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public RefreshmentPointType getType() {
        return type;
    }

    public void setType(RefreshmentPointType type) {
        this.type = type;
    }

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    @Override
    public String toString() {
        return "RefreshmentPoint{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", type=" + type +
                ", amenities=" + amenities +
                '}';
    }
}