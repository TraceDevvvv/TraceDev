package com.example.culturalheritage.model;

/**
 * A Value Object representing a geographical location with latitude and longitude.
 */
public class Location {
    private double latitude;
    private double longitude;

    /**
     * Constructor for Location.
     * @param latitude The latitude coordinate.
     * @param longitude The longitude coordinate.
     */
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // --- Getters ---
    // No setters as Location is a Value Object and should be immutable.

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}