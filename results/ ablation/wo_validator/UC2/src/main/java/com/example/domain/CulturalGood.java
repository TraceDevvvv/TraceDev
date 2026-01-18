package com.example.domain;

import com.example.dto.CulturalGoodDTO;
import java.util.Date;
import java.util.UUID;

/**
 * Domain entity representing a Cultural Good.
 * Contains business logic and validation.
 */
public class CulturalGood {
    private String id;
    private String name;
    private String description;
    private String location;
    private String type;
    private Date dateAdded;

    /**
     * Constructor to create a CulturalGood from provided data.
     * Calls validate() to ensure data integrity.
     * @param id the unique identifier
     * @param name the name of the cultural good
     * @param description the description
     * @param location the location
     * @param type the type
     */
    public CulturalGood(String id, String name, String description, String location, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = type;
        this.dateAdded = new Date(); // Set current date as dateAdded
        validate();
    }

    /**
     * Private validation method.
     * Ensures that required fields are not null or empty.
     * Throws IllegalArgumentException if validation fails.
     */
    private void validate() {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be null or empty");
        }
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type cannot be null or empty");
        }
    }

    /**
     * Static factory method to create a CulturalGood from a DTO.
     * Generates a new UUID if the DTO's id is null.
     * @param dto the data transfer object
     * @return a new CulturalGood instance
     */
    public static CulturalGood createFromDTO(CulturalGoodDTO dto) {
        String id = dto.getId();
        if (id == null || id.trim().isEmpty()) {
            id = UUID.randomUUID().toString(); // Generate ID if not provided
        }
        return new CulturalGood(id, dto.getName(), dto.getDescription(), dto.getLocation(), dto.getType());
    }

    // Getters (no setters to maintain immutability)

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

    public Date getDateAdded() {
        return dateAdded;
    }
}