package com.example.culturalheritage.model;

/**
 * An Entity representing a cultural heritage item.
 * Has a unique identifier and other descriptive attributes.
 */
public class CulturalHeritage {
    private String id;
    private String name;
    private String description;
    private String type; // e.g., "museum", "historical site", "park"
    private Location location; // The geographical location of the heritage item

    /**
     * Constructor for CulturalHeritage.
     * @param id A unique identifier for the cultural heritage item.
     * @param name The name of the item.
     * @param description A brief description of the item.
     * @param type The type or category of the item.
     * @param location The geographical coordinates of the item.
     */
    public CulturalHeritage(String id, String name, String description, String type, Location location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.location = location;
    }

    // --- Getters ---
    // No setters for an entity once created, typically, unless specific update operations are defined.

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public Location getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "CulturalHeritage{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                ", location=" + location +
                '}';
    }
}