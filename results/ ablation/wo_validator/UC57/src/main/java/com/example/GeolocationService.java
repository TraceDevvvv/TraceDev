package com.example;

/**
 * Concrete implementation of LocationService using geolocation.
 */
public class GeolocationService implements LocationService {
    /**
     * Simulates getting the current position.
     * In a real scenario, this would use GPS or network location.
     * @return a fixed location for demonstration.
     */
    @Override
    public Location getCurrentPosition() {
        // Return a default location (e.g., New York City).
        return new Location(40.7128, -74.0060);
    }
}