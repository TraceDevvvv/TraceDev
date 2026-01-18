package com.example.domain;

import java.util.Objects;

/**
 * Value Object representing a geographical location with latitude and longitude.
 */
public class Location {
    private final double latitude;
    private final double longitude;

    /**
     * Constructor for Location.
     * @param latitude The latitude coordinate.
     * @param longitude The longitude coordinate.
     */
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

    /**
     * Compares this Location object to the specified object.
     * The result is true if and only if the argument is not null and is a Location object
     * that represents the same sequence of coordinates as this object.
     * @param other The object to compare this Location against.
     * @return true if the given object represents a Location equivalent to this location, false otherwise.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Location location = (Location) other;
        return Double.compare(location.latitude, latitude) == 0 &&
               Double.compare(location.longitude, longitude) == 0;
    }

    /**
     * Returns a hash code for this Location.
     * @return a hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(latitude, longitude);
    }

    @Override
    public String toString() {
        return "Location{" +
               "latitude=" + latitude +
               ", longitude=" + longitude +
               '}';
    }
}