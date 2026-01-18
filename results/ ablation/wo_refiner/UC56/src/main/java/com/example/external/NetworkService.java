package com.example.external;

/**
 * External network‑based location service.
 */
public class NetworkService {
    private String provider;

    public NetworkService(String provider) {
        this.provider = provider;
    }

    public Coordinates getLocationFromNetwork() {
        // Simulate network‑based location (e.g., cell tower triangulation)
        return new Coordinates(40.7128, -74.0060); // New York coordinates
    }

    public boolean hasNetworkCoverage() {
        // Assume network coverage is present
        return true;
    }
}