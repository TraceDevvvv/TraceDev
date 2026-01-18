package com.etour.site;

/**
 * Represents a tourist site with its details.
 * This class acts as a data structure to hold information about a site.
 */
public class Site {
    private String siteId;
    private String name;
    private String description;
    private String location;
    private double latitude;
    private double longitude;
    private String imageUrl;
    private double rating;
    private int numberOfReviews;

    /**
     * Default constructor for Site.
     */
    public Site() {
    }

    /**
     * Constructs a new Site object with all details.
     *
     * @param siteId Unique identifier for the site.
     * @param name The name of the site.
     * @param description A detailed description of the site.
     * @param location The geographical location/address of the site.
     * @param latitude The latitude coordinate of the site.
     * @param longitude The longitude coordinate of the site.
     * @param imageUrl URL to an image of the site.
     * @param rating The average rating of the site.
     * @param numberOfReviews The total number of reviews for the site.
     */
    public Site(String siteId, String name, String description, String location,
                double latitude, double longitude, String imageUrl,
                double rating, int numberOfReviews) {
        this.siteId = siteId;
        this.name = name;
        this.description = description;
        this.location = location;
        this.latitude = latitude;
        this.longitude = longitude;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.numberOfReviews = numberOfReviews;
    }

    // Getters for all properties

    public String getSiteId() {
        return siteId;
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

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public double getRating() {
        return rating;
    }

    public int getNumberOfReviews() {
        return numberOfReviews;
    }

    // Setters for all properties (optional, but good for data manipulation if needed)

    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setNumberOfReviews(int numberOfReviews) {
        this.numberOfReviews = numberOfReviews;
    }

    /**
     * Provides a string representation of the Site object, useful for logging and debugging.
     *
     * @return A formatted string containing all site details.
     */
    @Override
    public String toString() {
        return "Site Details:\n" +
               "  ID: " + siteId + "\n" +
               "  Name: " + name + "\n" +
               "  Description: " + description + "\n" +
               "  Location: " + location + "\n" +
               "  Coordinates: (" + latitude + ", " + longitude + ")\n" +
               "  Image URL: " + imageUrl + "\n" +
               "  Rating: " + rating + " (" + numberOfReviews + " reviews)";
    }
}