package com.etour.entities;

import java.util.Objects;

/**
 * Represents a cultural object in the system.
 * Part of the Entities layer in Clean Architecture.
 */
public class CulturalObject {
    private String id;
    private String title;
    private String description;
    private String type;
    private String location;

    // Constructors
    public CulturalObject() {
        // default constructor
    }

    public CulturalObject(String id, String title, String description, String type, String location) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.type = type;
        this.location = location;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getLocation() {
        return location;
    }

    // Setters (if needed for flexibility, though UML only specifies getters)
    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CulturalObject that = (CulturalObject) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "CulturalObject{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}