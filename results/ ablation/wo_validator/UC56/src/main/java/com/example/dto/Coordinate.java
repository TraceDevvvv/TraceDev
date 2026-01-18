package com.example.dto;

/**
 * Helper DTO for raw coordinate data (used internally by adapters).
 * Not explicitly in the UML but implied by GPSAdapter's return type.
 */
public class Coordinate {
    private double latitude;
    private double longitude;
    private double accuracy;

    public Coordinate(double latitude, double longitude, double accuracy) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.accuracy = accuracy;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }
}