package com.example.culturalobjects;

/**
 * Data Class: Represents a cultural object.
 * This is a core domain entity containing information about an object.
 */
public class CulturalObject {
    public String id;
    public String name;
    public String description;
    public String type;

    /**
     * Constructor for CulturalObject.
     * @param id Unique identifier for the object.
     * @param name Name of the object.
     * @param description A brief description.
     * @param type The type or category of the object.
     */
    public CulturalObject(String id, String name, String description, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "CulturalObject{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", type='" + type + '\'' +
               '}';
    }
}