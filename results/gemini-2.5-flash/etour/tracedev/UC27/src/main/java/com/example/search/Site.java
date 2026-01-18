package com.example.search;

/**
 * Represents a Site entity with its properties.
 * This class directly maps to the 'Site' class in the UML diagram.
 */
public class Site {
    // Public fields as specified in the UML diagram
    public String id;
    public String name;
    public String location;
    public String description;

    /**
     * Constructor for the Site class.
     * @param id Unique identifier for the site.
     * @param name Name of the site.
     * @param location Location details of the site.
     * @param description Brief description of the site.
     */
    public Site(String id, String name, String location, String description) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Site{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", location='" + location + '\'' +
               ", description='" + description + '\'' +
               '}';
    }
}