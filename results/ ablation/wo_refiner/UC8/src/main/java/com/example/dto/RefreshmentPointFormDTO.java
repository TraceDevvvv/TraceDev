package com.example.dto;

import com.example.model.LocationDTO;
import com.example.model.ValidationResult;

import java.util.Map;

/**
 * Data Transfer Object for the modification form.
 */
public class RefreshmentPointFormDTO {
    private String name;
    private LocationDTO locationData;
    private Map<String, Object> attributes;
    private String operatorNotes;

    public RefreshmentPointFormDTO(String name, LocationDTO locationData, Map<String, Object> attributes, String operatorNotes) {
        this.name = name;
        this.locationData = locationData;
        this.attributes = attributes;
        this.operatorNotes = operatorNotes;
    }

    public String getName() {
        return name;
    }

    public LocationDTO getLocationData() {
        return locationData;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public String getOperatorNotes() {
        return operatorNotes;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocationData(LocationDTO locationData) {
        this.locationData = locationData;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public void setOperatorNotes(String operatorNotes) {
        this.operatorNotes = operatorNotes;
    }

    /**
     * Validates the DTO.
     */
    public ValidationResult validate() {
        ValidationResult result = new ValidationResult();
        if (name == null || name.trim().isEmpty()) {
            result.addError("Name is required");
        }
        if (locationData == null) {
            result.addError("Location data is required");
        }
        return result;
    }
}