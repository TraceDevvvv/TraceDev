package com.example.banner.infrastructure;

import com.example.banner.domain.PointOfRest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of {@link IPointOfRestRepository}.\n * This class simulates database interactions using a {@link ConcurrentHashMap}.\n * It can also simulate {@link ETOURConnectionException} for testing purposes.
 */
public class PointOfRestRepositoryImpl implements IPointOfRestRepository {
    // In-memory storage for points of rest
    private final Map<String, PointOfRest> pointsOfRest = new ConcurrentHashMap<>();
    // Counter for simulating ETOUR connection errors
    private int etourErrorCounter = 0;
    private static final int ERROR_THRESHOLD = 3; // Simulate error every 3 operations for example

    /**
     * Finds a point of rest by its ID.
     *
     * @param id The ID of the point of rest.
     * @return The point of rest if found, otherwise null.
     */
    @Override
    public PointOfRest findById(String id) { // SD: m32, m33, m34
        System.out.println("[PointOfRestRepositoryImpl] Simulating DB: Finding PointOfRest by ID: " + id);
        return pointsOfRest.get(id);
    }

    /**
     * Finds all available points of rest.
     *
     * @return A list of all points of rest.
     */
    @Override
    public List<PointOfRest> findAllPointsOfRest() { // SD: m6
        System.out.println("[PointOfRestRepositoryImpl] Simulating DB: Finding all PointsOfRest.");
        return new ArrayList<>(pointsOfRest.values());
    }

    /**
     * Updates an existing point of rest in the storage.
     *
     * @param pointOfRest The point of rest to update.
     * @return The updated point of rest.
     * @throws ETOURConnectionException if a simulated connection error occurs.
     */
    @Override
    public PointOfRest update(PointOfRest pointOfRest) throws ETOURConnectionException { // SD: m52, m53, m54
        // Simulate ETOUR connection error based on a counter
        etourErrorCounter++;
        if (etourErrorCounter % ERROR_THRESHOLD == 0) {
            System.out.println("[PointOfRestRepositoryImpl] Simulating ETOUR connection error during update.");
            throw new ETOURConnectionException("ETOUR connection interrupted during PointOfRest update operation.");
        }

        System.out.println("[PointOfRestRepositoryImpl] Simulating DB: Updating PointOfRest: " + pointOfRest.getId());
        if (!pointsOfRest.containsKey(pointOfRest.getId())) {
            System.err.println("[PointOfRestRepositoryImpl] Warning: PointOfRest with ID " + pointOfRest.getId() + " not found for update.");
            // For this in-memory implementation, we'll add it if it doesn't exist
            // A real DB would likely throw an exception or handle it differently
        }
        pointsOfRest.put(pointOfRest.getId(), pointOfRest);
        System.out.println("[PointOfRestRepositoryImpl] DB: PointOfRest updated: " + pointOfRest);
        return pointOfRest;
    }

    /**
     * Initializes some dummy data for testing purposes.
     */
    public void initDummyData() {
        PointOfRest por1 = new PointOfRest("Coffee Shop A", 2);
        por1.setCurrentBannerCount(1); // One banner already exists
        PointOfRest por2 = new PointOfRest("Museum B", 1);
        PointOfRest por3 = new PointOfRest("Park C", 5);

        pointsOfRest.put(por1.getId(), por1);
        pointsOfRest.put(por2.getId(), por2);
        pointsOfRest.put(por3.getId(), por3);

        System.out.println("[PointOfRestRepositoryImpl] Initialized dummy data.");
        System.out.println(" - " + por1);
        System.out.println(" - " + por2);
        System.out.println(" - " + por3);
    }
}