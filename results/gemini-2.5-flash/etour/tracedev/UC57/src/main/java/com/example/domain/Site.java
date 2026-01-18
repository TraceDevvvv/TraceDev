package com.example.domain;

/**
 * Entity representing a site of interest.
 * Includes methods for geographical calculations.
 */
public class Site {
    private String id;
    private String name;
    private String description;
    private Location location;

    // Constants for Haversine formula (Earth radius in km)
    private static final double EARTH_RADIUS = 6371; // kilometers

    /**
     * Constructor for Site.
     * @param id Unique identifier for the site.
     * @param name Name of the site.
     * @param description Description of the site.
     * @param location Geographical location of the site.
     */
    public Site(String id, String name, String description, Location location) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.location = location;
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

    public Location getLocation() {
        return location;
    }

    // Setters (if needed, but for immutable entities, often not provided)
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Calculates the distance between this site's location and another given location
     * using the Haversine formula.
     * @param from The starting location (e.g., user's current location).
     * @return The distance in kilometers.
     */
    public double getDistance(Location from) {
        if (from == null || this.location == null) {
            // Handle null locations gracefully, perhaps throw an exception or return a special value
            return -1.0; // Indicate error or unknown distance
        }

        double lat1 = Math.toRadians(from.getLatitude());
        double lon1 = Math.toRadians(from.getLongitude());
        double lat2 = Math.toRadians(this.location.getLatitude());
        double lon2 = Math.toRadians(this.location.getLongitude());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                   Math.cos(lat1) * Math.cos(lat2) *
                   Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return EARTH_RADIUS * c; // Distance in kilometers
    }

    @Override
    public String toString() {
        return "Site{" +
               "id='" + id + '\'' +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", location=" + location +
               '}';
    }
}