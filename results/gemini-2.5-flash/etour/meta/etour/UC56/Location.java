package com.example.localization;

/**
 * Represents a geographical location with latitude and longitude coordinates.
 */
public class Location {
    private final double latitude;
    private final double longitude;

    /**
     * Constructs a new Location object with the specified latitude and longitude.
     *
     * @param latitude  The latitude of the location.
     * @param longitude The longitude of the location.
     */
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Returns the latitude of this location.
     *
     * @return The latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Returns the longitude of this location.
     *
     * @return The longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Returns a string representation of this Location object.
     *
     * @return A string in the format "Latitude: [latitude], Longitude: [longitude]".
     */
    @Override
    public String toString() {
        return "Latitude: " + latitude + ", Longitude: " + longitude;
    }

    /**
     * Compares this Location object to the specified object. The result is true if and only if
     * the argument is not null and is a Location object that represents the same latitude and longitude
     * as this object.
     *
     * @param o The object to compare this Location against.
     * @return true if the given object represents a Location equivalent to this location, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.latitude, latitude) == 0 &&
               Double.compare(location.longitude, longitude) == 0;
    }

    /**
     * Returns a hash code for this Location object.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return java.util.Objects.hash(latitude, longitude);
    }
}