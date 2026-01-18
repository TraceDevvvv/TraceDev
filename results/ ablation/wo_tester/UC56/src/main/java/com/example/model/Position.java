package com.example.model;

import java.time.LocalDateTime;

/**
 * Represents a geographical position with accuracy and timestamp.
 */
public class Position {
    private double latitude;
    private double longitude;
    private double accuracy;
    private LocalDateTime timestamp;

    public Position(double latitude, double longitude, double accuracy) {
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

    public double getAccuracy() {
        return accuracy;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * A position is valid if coordinates are within valid ranges and accuracy positive.
     */
    public boolean isValid() {
        return latitude >= -90.0 && latitude <= 90.0 &&
               longitude >= -180.0 && longitude <= 180.0 &&
               accuracy > 0.0;
    }
}