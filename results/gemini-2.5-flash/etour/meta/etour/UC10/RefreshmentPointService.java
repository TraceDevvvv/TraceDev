package com.example.refreshmentpoint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Service class responsible for managing and retrieving RefreshmentPoint data.
 * This class simulates interaction with a data source (e.g., a database or external API)
 * to fetch refreshment point details.
 */
public class RefreshmentPointService {

    // A simulated database or data store for refreshment points.
    // In a real application, this would interact with a persistent storage.
    private final Map<String, RefreshmentPoint> refreshmentPointsStore;

    public RefreshmentPointService() {
        this.refreshmentPointsStore = new HashMap<>();
        // Initialize with some dummy data for demonstration purposes
        initializeDummyData();
    }

    /**
     * Initializes the service with some predefined refreshment points.
     * This simulates data being loaded from a persistent store.
     */
    private void initializeDummyData() {
        refreshmentPointsStore.put("RP001", new RefreshmentPoint("RP001", "The Grand Cafe", "123 Main St, Cityville", "A cozy cafe known for its artisanal coffee and pastries.", "Cafe", 4.7));
        refreshmentPointsStore.put("RP002", new RefreshmentPoint("RP002", "Pasta Palace", "456 Oak Ave, Townsville", "Authentic Italian cuisine in a family-friendly atmosphere.", "Restaurant", 4.2));
        refreshmentPointsStore.put("RP003", new RefreshmentPoint("RP003", "Burger Joint", "789 Pine Ln, Villageton", "Classic American burgers and shakes.", "Restaurant", 3.9));
        refreshmentPointsStore.put("RP004", new RefreshmentPoint("RP004", "Smoothie Oasis", "101 Beach Blvd, Coast City", "Healthy and refreshing smoothies and acai bowls.", "Cafe", 4.9));
        refreshmentPointsStore.put("RP005", new RefreshmentPoint("RP005", "The Thirsty Mug", "202 Market St, Metropolis", "A lively pub with a wide selection of craft beers.", "Bar", 4.1));
    }

    /**
     * Simulates searching for refreshment points.
     * In a real scenario, this might take search criteria. For this use case,
     * it simply returns all available refreshment points.
     *
     * @return A list of all available refreshment points.
     */
    public List<RefreshmentPoint> searchRefreshmentPoints() {
        // Simulate network delay or database query time
        try {
            Thread.sleep(200); // 200 milliseconds delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Search operation interrupted: " + e.getMessage());
        }
        return new ArrayList<>(refreshmentPointsStore.values());
    }

    /**
     * Retrieves the detailed information for a specific refreshment point by its ID.
     * This method simulates fetching data from a server (e.g., ETOUR server).
     *
     * @param refreshmentPointId The unique identifier of the refreshment point to retrieve.
     * @return An Optional containing the RefreshmentPoint if found, or an empty Optional if not found.
     * @throws ConnectionInterruptionException If there's a simulated interruption in connection to the ETOUR server.
     */
    public Optional<RefreshmentPoint> getRefreshmentPointDetails(String refreshmentPointId) throws ConnectionInterruptionException {
        // Simulate potential connection interruption to the ETOUR server
        if (Math.random() < 0.1) { // 10% chance of connection interruption
            throw new ConnectionInterruptionException("Connection to ETOUR server interrupted while fetching details for " + refreshmentPointId);
        }

        // Simulate network delay or database query time
        try {
            Thread.sleep(500); // 500 milliseconds delay for detail retrieval
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Detail retrieval operation interrupted: " + e.getMessage());
        }

        return Optional.ofNullable(refreshmentPointsStore.get(refreshmentPointId));
    }

    /**
     * Custom exception for simulating connection interruptions.
     */
    public static class ConnectionInterruptionException extends Exception {
        public ConnectionInterruptionException(String message) {
            super(message);
        }
    }
}