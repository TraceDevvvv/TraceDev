package com.example.application.model;

/**
 * Entity class representing a tourist site.
 * Contains site details such as id, name, description, location, and image URL.
 */
public class Site {
    private String id;
    private String name;
    private String description;
    private String location;
    private String imageUrl;

    /**
     * Constructor to create a Site object.
     *
     * @param id the unique identifier of the site
     * @param name the name of the site
     * @param description a description of the site
     * @param location the location of the site
     * @param imageUrl the URL of the site's image
     */
    public Site(String id, String name, String description, String location, String imageUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.imageUrl = imageUrl;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }
}