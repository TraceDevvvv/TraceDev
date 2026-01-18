package com.example.external;

import com.example.model.Location;

/**
 * External database for storing location data.
 */
public class Database {
    private Object connection; // Simplified connection object

    public void saveLocationData(Location data) {
        // Simulate saving to database
        System.out.println("Database: Saved location for tourist");
    }

    public Location queryLocationData(String touristId) {
        // Simulate querying the latest location from database
        // Return null to simulate no cached data (fallback will handle)
        return null;
    }
}