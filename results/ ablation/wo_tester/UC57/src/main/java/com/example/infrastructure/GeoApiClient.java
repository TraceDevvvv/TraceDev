package com.example.infrastructure;

/**
 * Simulates a client for a geolocation API.
 */
public class GeoApiClient {
    public String getCurrentLocation(String userId) {
        // In a real implementation, this would call an external API.
        // Return a simulated location string "lat,lon".
        return "45.4642,9.1900"; // Milan coordinates
    }
}