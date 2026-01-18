package com.example.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * LocationRepository class
 * Implements ILocationRepository and provides data access for Location objects.
 * Uses in-memory data for demonstration purposes.
 */
public class LocationRepository implements ILocationRepository {
    private Map<String, Location> locations = new HashMap<>();

    /**
     * Constructor for LocationRepository.
     * Initializes with some dummy data.
     */
    public LocationRepository() {
        // Dummy data for demonstration
        locations.put("loc1", new Location("loc1", "Central Park", "A large park in Manhattan."));
        locations.put("loc2", new Location("loc2", "Eiffel Tower", "An iconic landmark in Paris."));
        locations.put("loc3", new Location("loc3", "Tokyo Skytree", "A broadcasting and observation tower in Tokyo."));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Location> findAll() {
        // Simulate database access delay
        try {
            Thread.sleep(50); // Simulate network/DB latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("[LR] Fetching all locations from DB (simulated).");
        // SD: m4, m5
        return new ArrayList<>(locations.values());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location findById(String id) {
        // Simulate database access delay
        try {
            Thread.sleep(50); // Simulate network/DB latency
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("[LR] Fetching location by ID: " + id + " from DB (simulated).");
        // SD: m14, m15
        return locations.get(id);
    }
}