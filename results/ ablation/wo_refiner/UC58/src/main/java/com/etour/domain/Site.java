package com.etour.domain;

/**
 * Represents a tour site entity.
 * Contains data about a specific tourist site.
 */
public class Site {
    private String id;
    private String name;
    private String description;
    private String location;
    private double rating;

    public Site(String id, String name, String description, String location, double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
        this.rating = rating;
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

    public double getRating() {
        return rating;
    }
}