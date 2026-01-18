package com.example.culturalheritage.service;

import com.example.culturalheritage.model.Location;

/**
 * A Service responsible for providing location-related functionalities,
 * such as getting the current user's position.
 */
public class LocationService {

    /**
     * Retrieves the current geographical position of the specified user.
     * In a real application, this would involve GPS, IP lookup, or user settings.
     * For this simulation, it returns a fixed dummy location.
     * @param userId The ID of the user whose position is to be retrieved.
     * @return A Location object representing the user's current position.
     */
    public Location getCurrentUserPosition(String userId) {
        System.out.println("LocationService: getCurrentUserPosition() for user: " + userId);
        // Simulate retrieving current user's position.
        // In a real application, this would query a location provider.
        return new Location(40.7128, -74.0060); // Example: New York City coordinates
    }
}