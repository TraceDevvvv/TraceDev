package com.example.dto;

import java.util.Map;
import java.util.HashMap;

/**
 * Data Transfer Object for Preference.
 */
public class PreferenceDTO {
    private String id;
    private String touristId;
    private Map<String, String> attributes;

    public PreferenceDTO() {
        this.attributes = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTouristId() {
        return touristId;
    }

    public void setTouristId(String touristId) {
        this.touristId = touristId;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }
}