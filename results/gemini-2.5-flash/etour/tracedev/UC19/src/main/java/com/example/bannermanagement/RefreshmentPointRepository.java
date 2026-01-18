package com.example.bannermanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * In-memory implementation of IRefreshmentPointRepository.
 * This class simulates data storage and retrieval for RefreshmentPoint entities.
 * It can also simulate ETOURConnectionException for testing purposes.
 */
public class RefreshmentPointRepository implements IRefreshmentPointRepository {
    private final Map<String, RefreshmentPoint> refreshmentPoints = new HashMap<>();
    // Static flag to simulate connection errors for demonstration purposes.
    public static boolean simulateConnectionError = false;

    /**
     * Constructor to initialize with some dummy data.
     */
    public RefreshmentPointRepository() {
        // Add some dummy data
        refreshmentPoints.put("RP001", new RefreshmentPoint("RP001", "Coffee Corner", "Cozy place for a quick coffee."));
        refreshmentPoints.put("RP002", new RefreshmentPoint("RP002", "Snack Stop", "Variety of snacks and drinks."));
        refreshmentPoints.put("RP003", new RefreshmentPoint("RP003", "Juice Bar", "Freshly squeezed ju."));
    }

    /**
     * Retrieves all available RefreshmentPoint entities.
     * @return A list of all RefreshmentPoint objects.
     * @throws ETOURConnectionException if simulateConnectionError is true.
     */
    @Override
    public List<RefreshmentPoint> findAll() throws ETOURConnectionException {
        if (simulateConnectionError) {
            throw new ETOURConnectionException("Simulated ETOUR connection error during findAll RefreshmentPoints.");
        }
        return new ArrayList<>(refreshmentPoints.values());
    }

    /**
     * Finds a RefreshmentPoint by its unique identifier.
     * @param id The unique ID of the refreshment point to find.
     * @return The RefreshmentPoint object if found, null otherwise.
     * @throws ETOURConnectionException if simulateConnectionError is true.
     */
    @Override
    public RefreshmentPoint findById(String id) throws ETOURConnectionException {
        if (simulateConnectionError) {
            throw new ETOURConnectionException("Simulated ETOUR connection error during findById RefreshmentPoint.");
        }
        return refreshmentPoints.get(id);
    }
}