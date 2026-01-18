package com.example.localization.model;

/**
 * Represents geographical coordinates and a timestamp.
 * This class is a value object for location data.
 */
public class Location {
    public double latitude;
    public double longitude;
    public long timestamp;

    /**
     * Constructs a new Location object.
     *
     * @param lat The latitude coordinate.
     * @param lon The longitude coordinate.
     * @param ts  The timestamp when the location was recorded, in milliseconds since epoch.
     */
    public Location(double lat, double lon, long ts) {
        this.latitude = lat;
        this.longitude = lon;
        this.timestamp = ts;
    }

    @Override
    public String toString() {
        return "Location{" +
               "latitude=" + latitude +
               ", longitude=" + longitude +
               ", timestamp=" + timestamp +
               '}';
    }
}