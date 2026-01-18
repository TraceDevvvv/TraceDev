package com.example.bookmarking.domain;

/**
 * Represents a geographical Site in the domain model.
 */
public class Site {
    /** Unique identifier for the site. */
    public String id;
    /** Name of the site. */
    public String name;
    /** Geographical location details of the site. */
    public String location;

    /**
     * Constructs a new Site instance.
     * @param id The unique identifier for the site.
     * @param name The name of the site.
     * @param location The geographical location of the site.
     */
    public Site(String id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    /**
     * Retrieves the site's unique identifier.
     * @return The site's ID.
     */
    public String getSiteId() {
        return id;
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