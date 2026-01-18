package com.example.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Data Transfer Object for refreshment point (Presentation <-> Application layer).
 * Maps to RefreshmentPoint domain object.
 */
public class RefreshmentPointDTO implements Serializable {
    public String id;
    public String name;
    public String location;
    public String type;
    public List<String> amenities;

    // Constructors
    public RefreshmentPointDTO() {}

    public RefreshmentPointDTO(String id, String name, String location, String type, List<String> amenities) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
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
        return "RefreshmentPointDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", type='" + type + '\'' +
                ", amenities=" + amenities +
                '}';
    }
}