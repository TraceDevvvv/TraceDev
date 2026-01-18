package com.example.dto;

import java.util.Date;

/**
 * Data Transfer Object for Cultural Good.
 * Represents the data needed to create or display a CulturalGood.
 */
public class CulturalGoodDTO {
    private String id;
    private String name;
    private String description;
    private String location;
    private String type;
    private Date dateAdded;

    /**
     * Default constructor.
     */
    public CulturalGoodDTO() {
    }

    /**
     * Constructor with all fields.
     * @param id the unique identifier
     * @param name the name of the cultural good
     * @param description the description
     * @param location the location
     * @param type the type
     * @param dateAdded the date when the good was added
     */
    public CulturalGoodDTO(String id, String name, String description, String location, String type, Date dateAdded) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.type = type;
        this.dateAdded = dateAdded;
    }

    // Getters and setters

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}