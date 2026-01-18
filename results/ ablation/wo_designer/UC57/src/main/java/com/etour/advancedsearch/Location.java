package com.etour.advancedsearch;

/**
 * Represents a geographic location with latitude and longitude.
 */
public class Location {
    private double latitude;
    private double longitude;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    @Override
    public String toString() {
        return String.format("Location{latitude=%.6f, longitude=%.6f}", latitude, longitude);
    }
}