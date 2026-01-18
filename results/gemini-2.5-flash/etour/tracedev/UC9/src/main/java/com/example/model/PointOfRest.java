package com.example.model;

/**
 * Represents a Point of Rest entity in the domain layer.
 */
public class PointOfRest {
    public String id;
    public String name;
    public String location;
    public String description;
    public String type;

    public PointOfRest(String id, String name, String location, String description, String type) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
        this.type = type;
    }

    @Override
    public String toString() {
        return "PointOfRest{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}