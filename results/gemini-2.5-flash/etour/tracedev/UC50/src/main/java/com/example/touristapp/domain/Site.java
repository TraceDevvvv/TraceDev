package com.example.touristapp.domain;

/**
 * Represents a tourist site in the domain layer.
 */
public class Site {
    private String id;
    private String name;
    private String location;

    /**
     * Constructs a new Site instance.
     *
     * @param id The unique identifier of the site.
     * @param name The name of the site.
     * @param location The geographical location of the site.
     */
    public Site(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    // Setters (if mutable, typically not for domain objects unless business logic dictates)
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Site{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", location='" + location + '\'' +
               '}';
    }
}