package com.example.gps;

/**
 * Represents a geographic position with latitude and longitude.
 */
public class GPSPosition {
    private final double latitude;
    private final double longitude;

    public GPSPosition(double latitude, double longitude) {
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
        return String.format("GPSPosition{latitude=%.6f, longitude=%.6f}", latitude, longitude);
    }
}