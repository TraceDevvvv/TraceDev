package com.example.dto;

import java.util.Map;
import java.util.HashMap;

/**
 * Data Transfer Object for updating a RefreshmentPoint.
 */
public class RefreshmentPointUpdateDTO {
    private String id;
    private String updatedName;
    private String updatedDescription;
    private String updatedLocation;
    private Map<String, String> updatedAttributes;

    public RefreshmentPointUpdateDTO(String id) {
        this.id = id;
        this.updatedAttributes = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getUpdatedName() {
        return updatedName;
    }

    public String getUpdatedDescription() {
        return updatedDescription;
    }

    public String getUpdatedLocation() {
        return updatedLocation;
    }

    public Map<String, String> getUpdatedAttributes() {
        return updatedAttributes;
    }

    public void setUpdatedName(String name) {
        this.updatedName = name;
    }

    public void setUpdatedDescription(String description) {
        this.updatedDescription = description;
    }

    public void setUpdatedLocation(String location) {
        this.updatedLocation = location;
    }

    public void addUpdatedAttribute(String key, String value) {
        this.updatedAttributes.put(key, value);
    }

    /**
     * Simple validation: at least one field must be updated.
     */
    public boolean isValid() {
        return (updatedName != null && !updatedName.isEmpty()) ||
               (updatedDescription != null) ||
               (updatedLocation != null && !updatedLocation.isEmpty()) ||
               !updatedAttributes.isEmpty();
    }
}