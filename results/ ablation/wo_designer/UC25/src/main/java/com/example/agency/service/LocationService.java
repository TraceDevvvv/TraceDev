package com.example.agency.service;

import com.example.agency.model.Location;
import java.util.ArrayList;
import java.util.List;

/**
 * Service to manage locations.
 * Simulates database operations for locations.
 * Handles potential connection interruptions.
 */
public class LocationService {
    /**
     * Fetches all available locations.
     * In a real scenario, this would query a database or external service.
     * Simulates connection interruption handling.
     *
     * @return List of locations.
     */
    public List<Location> getAllLocations() {
        // Simulate potential connection interruption (Quality Requirement).
        // In real application, implement retry logic or proper exception handling.
        try {
            // Simulate network delay.
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Connection interrupted while fetching locations.");
            return new ArrayList<>();
        }

        List<Location> locations = new ArrayList<>();
        locations.add(new Location("L001", "Downtown Plaza", "Central shopping area"));
        locations.add(new Location("L002", "Riverside Park", "Public park by the river"));
        locations.add(new Location("L003", "Tech Hub Office", "Corporate office building"));
        locations.add(new Location("L004", "Northside Mall", "Large shopping mall"));
        locations.add(new Location("L005", "Beachfront Resort", "Luxury resort by the beach"));
        return locations;
    }
}