package com.example;

/**
 * Represents a geographic location with latitude and longitude.
 */
public class Location {
    private double latitude;
    private double longitude;

    /**
     * Constructor for Location.
     * @param latitude the latitude.
     * @param longitude the longitude.
     */
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Calculates the distance to another location using the Haversine formula.
     * @param to the target location.
     * @return distance in kilometers.
     */
    public double calculateDistance(Location to) {
        final int R = 6371; // Earth's radius in km
        double latDistance = Math.toRadians(to.latitude - this.latitude);
        double lonDistance = Math.toRadians(to.longitude - this.longitude);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(this.latitude)) * Math.cos(Math.toRadians(to.latitude))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

    /**
     * Gets the latitude.
     * @return latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Gets the longitude.
     * @return longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the latitude.
     * @param latitude the latitude.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Sets the longitude.
     * @param longitude the longitude.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}