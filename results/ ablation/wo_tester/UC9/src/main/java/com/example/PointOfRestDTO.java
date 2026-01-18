package com.example;

import java.util.List;

/**
 * Data Transfer Object for Point of Rest details.
 */
public class PointOfRestDTO {
    private String id;
    private String name;
    private String location;
    private List<String> amenities;

    public PointOfRestDTO(String id, String name, String location, List<String> amenities) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.amenities = amenities;
    }

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

    public List<String> getAmenities() {
        return amenities;
    }

    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    @Override
    public String toString() {
        return "PointOfRestDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", amenities=" + amenities +
                '}';
    }
}