package com.example;

import java.util.List;
import java.util.ArrayList;

/**
 * Repository for accessing Location data.
 * Simulates a database connection.
 */
public class LocationRepository {
    /**
     * Retrieves all locations.
     * @return List of all locations.
     * @throws ConnectionException if connection to server fails.
     */
    public List<Location> findAll() throws ConnectionException {
        List<Location> locations = new ArrayList<>();
        locations.add(new Location("loc1", "Downtown Office"));
        locations.add(new Location("loc2", "Airport Branch"));
        if (Math.random() < 0.1) {
            throw new ConnectionException("Connection lost while fetching locations.");
        }
        return locations;
    }
}