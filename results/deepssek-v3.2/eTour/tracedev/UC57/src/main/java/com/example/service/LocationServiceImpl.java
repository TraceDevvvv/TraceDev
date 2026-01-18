
package com.example.service;

import com.example.model.Location;

/**
 * Implementation of LocationService.
 * Simulates getting current device location.
 */
public class LocationServiceImpl implements LocationService {

    @Override
    public Location getCurrentLocation() {
        // Simulate location availability
        boolean locationAvailable = Math.random() > 0.1; // 90% availability for demo

        if (!locationAvailable) {
            throw new RuntimeException("Location service unavailable");
        }

        // Return a mock location (e.g., from GPS)
        return new Location(40.7128, -74.0060); // New York City
    }
}
