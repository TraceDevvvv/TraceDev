package com.example.tourism.domain;

/**
 * Domain Entity: Represents a tourist site with its core attributes.
 * This is the rich domain model that holds the core business data.
 */
public class Site {
    // Public fields for simplicity as per UML, typically these would be private with getters/setters.
    public String id;
    public String name;
    public String description;
    public String location;
    public String imageUrl;

    // Default constructor
    public Site() {}

    // Constructor with all fields
    public Site(String id, String name, String description, String location, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.imageUrl = imageUrl;
    }
}