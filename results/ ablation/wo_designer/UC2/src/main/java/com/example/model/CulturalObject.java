package com.example.model;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a cultural object with its attributes.
 * Implements equals and hashCode to prevent duplicates based on ID and name.
 */
public class CulturalObject {
    private String id;
    private String name;
    private String description;
    private String location;
    private String era;
    private String category;
    private LocalDateTime creationDate;
    private LocalDateTime lastModified;

    public CulturalObject() {
        this.creationDate = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
    }

    public CulturalObject(String id, String name, String description, String location, String era, String category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.era = era;
        this.category = category;
        this.creationDate = LocalDateTime.now();
        this.lastModified = LocalDateTime.now();
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        this.lastModified = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.lastModified = LocalDateTime.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.lastModified = LocalDateTime.now();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        this.lastModified = LocalDateTime.now();
    }

    public String getEra() {
        return era;
    }

    public void setEra(String era) {
        this.era = era;
        this.lastModified = LocalDateTime.now();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        this.lastModified = LocalDateTime.now();
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CulturalObject that = (CulturalObject) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "CulturalObject{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", era='" + era + '\'' +
                ", category='" + category + '\'' +
                ", creationDate=" + creationDate +
                ", lastModified=" + lastModified +
                '}';
    }
}