package com.example.dto;

import java.util.Map;
import java.util.HashMap;

/**
 * DTO class representing a convention request data transfer object.
 * Corresponds to the ConventionRequestDTO class in the UML diagram.
 */
public class ConventionRequestDTO {
    private Map<String, Object> conventionData;

    /**
     * Default constructor.
     */
    public ConventionRequestDTO() {
        this.conventionData = new HashMap<>();
    }

    /**
     * Gets the convention data map.
     * @return Map containing convention data.
     */
    public Map<String, Object> getConventionData() {
        return conventionData;
    }

    /**
     * Sets the convention data map.
     * @param data The map containing convention data.
     */
    public void setConventionData(Map<String, Object> data) {
        this.conventionData = data;
    }
}