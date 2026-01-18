package com.example.model;

import java.time.LocalDateTime;

/**
 * Represents a geographical location with latitude, longitude, timestamp, and accuracy.
 */
public class Location {
    private double latitude;
    private double longitude;
    private LocalDateTime timestamp;
    private double accuracy;

    public Location(double latitude, double longitude, double accuracy) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.accuracy = accuracy;
        this.timestamp = LocalDateTime.now();
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public double getAccuracy() {
        return accuracy;
    }

    /**
     * Validates whether the location data is acceptable.
     * Assumes valid if accuracy > 0 (reasonable assumption).
     */
    public boolean isValid() {
        return accuracy > 0;
    }
}