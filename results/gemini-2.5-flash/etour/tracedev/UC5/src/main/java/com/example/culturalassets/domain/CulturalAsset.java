package com.example.culturalassets.domain;

/**
 * Represents a cultural asset in the domain layer.
 * Contains core information about a cultural item.
 */
public class CulturalAsset {
    private String id;
    private String name;
    private String description;
    private String location;
    private String type;

    /**
     * Constructs a new CulturalAsset.
     * @param id Unique identifier of the asset.
     * @param name Name of the asset.
     * @param description Detailed description of the asset.
     * @param location Physical or conceptual location of the asset.
     * @param type Category or type of the asset (e.g., "Painting", "Sculpture", "Historic Site").
     */
    public CulturalAsset(String id, String name, String description, String location, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = type;
    }

    // Getters for all attributes
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public String getType() {
        return type;
    }

    /**
     * Provides a summary of the cultural asset's details.
     * @return A string containing key details of the asset.
     */
    public String getDetails() {
        return "ID: " + id + ", Name: " + name + ", Type: " + type + ", Location: " + location;
    }
}