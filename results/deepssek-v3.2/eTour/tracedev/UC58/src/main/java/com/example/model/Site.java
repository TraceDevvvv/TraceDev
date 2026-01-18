package com.example.model;

import com.example.dto.SiteDTO;
import java.util.UUID;

/**
 * Domain class representing an archaeological site.
 * Maps to the Site class in the UML class diagram.
 */
public class Site {
    private final String id;
    private final String name;
    private final String description;
    private final String location;
    private final double rating;

    // Constructor for creating a Site from raw data
    public Site(String id, String name, String description, String location, double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.rating = rating;
    }

    // Getters as per UML
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

    public double getRating() {
        return rating;
    }

    // Creates a DTO from this domain object as per UML relationship
    public SiteDTO toDTO() {
        return new SiteDTO(id, name, description, location, rating);
    }
}