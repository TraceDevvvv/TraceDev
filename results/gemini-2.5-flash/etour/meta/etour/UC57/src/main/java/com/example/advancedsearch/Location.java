package com.example.advancedsearch;

/**
 * Represents a geographical location with latitude and longitude.
 * This class is used to define the location of a tourist or a site.
 */
public class Location {
    private double latitude;
    private double longitude;

    /**
     * Constructs a new Location object.
     *
     * @param latitude The latitude of the location.
     * @param longitude The longitude of the location.
     */
    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Gets the latitude of the location.
     *
     * @return The latitude.
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude of the location.
     *
     * @param latitude The new latitude.
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the longitude of the location.
     *
     * @return The longitude.
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude of the location.
     *
     * @param longitude The new longitude.
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Calculates the distance between this location and another location.
     * This is a simplified calculation (Euclidean distance) for demonstration purposes.
     * For real-world applications, Haversine formula or a geospatial library would be used.
     *
     * @param otherLocation The other location to calculate the distance to.
     * @return The distance between the two locations.
     */
    public double calculateDistance(Location otherLocation) {
        // Using Euclidean distance for simplicity.
        // In a real application, consider Haversine formula for geographical distances.
        double latDiff = this.latitude - otherLocation.getLatitude();
        double lonDiff = this.longitude - otherLocation.getLongitude();
        return Math.sqrt(latDiff * latDiff + lonDiff * lonDiff);
    }

    @Override
    public String toString() {
        return "Location{" +
               "latitude=" + latitude +
               ", longitude=" + longitude +
               '}';
    }
}