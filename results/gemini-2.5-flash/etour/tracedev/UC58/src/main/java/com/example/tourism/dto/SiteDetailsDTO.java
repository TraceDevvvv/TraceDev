package com.example.tourism.dto;

/**
 * Data Transfer Object (DTO): Used to transfer site details between layers,
 * particularly from the service layer to the presentation layer.
 * It may contain a subset or aggregated data from domain entities.
 */
public class SiteDetailsDTO {
    // Public fields for simplicity as per UML, typically these would be private with getters/setters.
    public String id;
    public String name;
    public String description;
    public String location;
    public String imageUrl;

    // Default constructor
    public SiteDetailsDTO() {}

    // Constructor with all fields
    public SiteDetailsDTO(String id, String name, String description, String location, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.imageUrl = imageUrl;
    }
}