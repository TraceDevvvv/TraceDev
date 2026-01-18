package com.example.external;

/**
 * Simple coordinate pair (used by external dev).
 */
public class Coordinates {
    private double lat;
    private double lon;

    public Coordinates(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public double getLatitude() {
        return lat;
    }

    public double getLongitude() {
        return lon;
    }
}