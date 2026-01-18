package com.etour.culturalheritage;

import java.time.LocalDate;
import java.util.Objects;

/**
 * CulturalHeritage entity class representing a cultural object in the system.
 * This class contains the core data fields for a cultural heritage item.
 */
public class CulturalHeritage {
    private String id;              // Unique identifier for the cultural heritage
    private String name;            // Name of the cultural heritage
    private String description;     // Description of the cultural heritage
    private String location;        // Location where the cultural heritage is situated
    private LocalDate creationDate; // Date when the cultural heritage was created/established
    private LocalDate lastModified; // Date when the cultural heritage was last modified

    /**
     * Default constructor.
     */
    public CulturalHeritage() {
        this.lastModified = LocalDate.now();
    }

    /**
     * Constructor with all fields except lastModified.
     * @param id Unique identifier
     * @param name Name of cultural heritage
     * @param description Description
     * @param location Location
     * @param creationDate Creation date
     */
    public CulturalHeritage(String id, String name, String description, String location, LocalDate creationDate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.creationDate = creationDate;
        this.lastModified = LocalDate.now();
    }

    /**
     * Full constructor with all fields.
     * @param id Unique identifier
     * @param name Name of cultural heritage
     * @param description Description
     * @param location Location
     * @param creationDate Creation date
     * @param lastModified Last modified date
     */
    public CulturalHeritage(String id, String name, String description, String location, 
                           LocalDate creationDate, LocalDate lastModified) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.creationDate = creationDate;
        this.lastModified = lastModified;
    }

    // Getters and Setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
        this.lastModified = LocalDate.now();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.lastModified = LocalDate.now();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
        this.lastModified = LocalDate.now();
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
        this.lastModified = LocalDate.now();
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
        this.lastModified = LocalDate.now();
    }

    public LocalDate getLastModified() {
        return lastModified;
    }

    public void setLastModified(LocalDate lastModified) {
        this.lastModified = lastModified;
    }

    /**
     * Updates the last modified date to current date.
     */
    public void updateLastModified() {
        this.lastModified = LocalDate.now();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CulturalHeritage that = (CulturalHeritage) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CulturalHeritage{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", creationDate=" + creationDate +
                ", lastModified=" + lastModified +
                '}';
    }

    /**
     * Creates a copy of this CulturalHeritage object.
     * @return A new CulturalHeritage object with the same field values
     */
    public CulturalHeritage copy() {
        return new CulturalHeritage(id, name, description, location, creationDate, lastModified);
    }
}