package com.example;

/**
 * Represents a tourist site with details and location.
 */
public class Site {
    private int id;
    private String name;
    private String description;
    private Location coordinates;
    private String category;
    private double rating;

    /**
     * Constructor for Site.
     * @param id the site ID.
     * @param name the site name.
     * @param description the site description.
     * @param coordinates the site coordinates.
     * @param category the site category.
     * @param rating the site rating.
     */
    public Site(int id, String name, String description, Location coordinates, String category, double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.coordinates = coordinates;
        this.category = category;
        this.rating = rating;
    }

    /**
     * Calculates distance from a given location.
     * @param from the starting location.
     * @return distance in kilometers.
     */
    public double calculateDistance(Location from) {
        return coordinates.calculateDistance(from);
    }

    /**
     * Gets the site ID.
     * @return the ID.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the site ID.
     * @param id the ID.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the site name.
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the site name.
     * @param name the name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the site description.
     * @return the description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the site description.
     * @param description the description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the site coordinates.
     * @return the coordinates.
     */
    public Location getCoordinates() {
        return coordinates;
    }

    /**
     * Sets the site coordinates.
     * @param coordinates the coordinates.
     */
    public void setCoordinates(Location coordinates) {
        this.coordinates = coordinates;
    }

    /**
     * Gets the site category.
     * @return the category.
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the site category.
     * @param category the category.
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the site rating.
     * @return the rating.
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets the site rating.
     * @param rating the rating.
     */
    public void setRating(double rating) {
        this.rating = rating;
    }
}