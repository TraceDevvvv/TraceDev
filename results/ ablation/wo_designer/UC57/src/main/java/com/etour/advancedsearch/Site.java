package com.etour.advancedsearch;

/**
 * Represents a tourist site that can be searched.
 */
public class Site {
    private String id;
    private String name;
    private String description;
    private String category;
    private Location location;
    private double rating;

    public Site(String id, String name, String description, String category, Location location, double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public Location getLocation() {
        return location;
    }

    public double getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return String.format("Site{id='%s', name='%s', category='%s', rating=%.1f}", id, name, category, rating);
    }
}