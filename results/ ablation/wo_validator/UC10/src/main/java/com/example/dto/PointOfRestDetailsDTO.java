package com.example.dto;

import java.util.List;

/**
 * Data Transfer Object for Point of Rest details.
 * Used to transfer data between layers without exposing domain entities.
 */
public class PointOfRestDetailsDTO {
    public String id;
    public String name;
    public String fullAddress;
    public List<String> facilities;

    public PointOfRestDetailsDTO() {}

    public PointOfRestDetailsDTO(String id, String name, String fullAddress, List<String> facilities) {
        this.id = id;
        this.name = name;
        this.fullAddress = fullAddress;
        this.facilities = facilities;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getFullAddress() { return fullAddress; }
    public void setFullAddress(String fullAddress) { this.fullAddress = fullAddress; }

    public List<String> getFacilities() { return facilities; }
    public void setFacilities(List<String> facilities) { this.facilities = facilities; }
}