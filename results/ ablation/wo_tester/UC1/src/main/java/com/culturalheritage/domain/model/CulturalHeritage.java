package com.culturalheritage.domain.model;

import java.util.Map;
import java.util.HashMap;

/**
 * CulturalHeritage entity representing a cultural heritage object.
 * Mapped from UML class diagram.
 */
public class CulturalHeritage {
    private String id;
    private String name;
    private String description;
    private Map<String, Object> otherAttributes;

    public CulturalHeritage() {
        this.otherAttributes = new HashMap<>();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Object> getOtherAttributes() {
        return otherAttributes;
    }

    public void setOtherAttributes(Map<String, Object> otherAttributes) {
        this.otherAttributes = otherAttributes;
    }
}