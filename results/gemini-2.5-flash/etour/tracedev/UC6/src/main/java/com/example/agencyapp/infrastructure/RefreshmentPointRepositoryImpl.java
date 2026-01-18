package com.example.agencyapp.infrastructure;

import com.example.agencyapp.domain.ETOURConnectionException;
import com.example.agencyapp.domain.RefreshmentPoint;

import java.util.HashMap;
import java.util.Map;

/**
 * Concrete implementation of IRefreshmentPointRepository.
 * This class simulates a persistence layer for RefreshmentPoint entities.
 * It includes mock logic for deletion outcomes, including throwing ETOURConnectionException.
 */
public class RefreshmentPointRepositoryImpl implements IRefreshmentPointRepository {

    // A simple in-memory store to simulate a database.
    private final Map<String, RefreshmentPoint> database = new HashMap<>();

    public RefreshmentPointRepositoryImpl() {
        // Populate with some dummy data for testing
        database.put("RP001", new RefreshmentPoint("RP001", "Coffee Corner", "Lobby A"));
        database.put("RP002", new RefreshmentPoint("RP002", "Snack Bar", "Level 3"));
        database.put("RP003", new RefreshmentPoint("RP003", "Water Station", "Main Hall"));
    }

    /**
     * Simulates the deletion of a refreshment point.
     *
     * @param id The ID of the refreshment point to delete.
     * @return true if deleted, false if not found or a generic error occurred.
     * @throws ETOURConnectionException for specific IDs to simulate ETOUR connection issues.
     */
    @Override
    public boolean delete(String id) throws ETOURConnectionException {
        System.out.println("  Repo: Attempting to delete refreshment point with ID: " + id);

        // Simulate different outcomes based on ID for testing purposes
        if ("etour_error_id".equalsIgnoreCase(id)) {
            // Simulate an ETOUR connection error (REQ-012)
            System.err.println("  Repo: Simulating ETOUR connection error for ID: " + id);
            throw new ETOURConnectionException("ETOUR system connection interrupted during deletion for ID: " + id);
        } else if ("fail_id".equalsIgnoreCase(id)) {
            // Simulate a generic database error or integrity constraint violation
            System.out.println("  Repo: Simulating generic deletion failure for ID: " + id);
            return false;
        }

        // Standard deletion logic
        if (database.containsKey(id)) {
            database.remove(id);
            System.out.println("  Repo: Successfully deleted refreshment point ID: " + id);
            return true;
        } else {
            System.out.println("  Repo: Refreshment point with ID " + id + " not found.");
            return false; // Refreshment point not found
        }
    }

    /**
     * Retrieves all refreshment points currently in the "database".
     * This is an auxiliary method not explicitly in the diagram but useful for UI display.
     * @return A map of all refreshment points.
     */
    public Map<String, RefreshmentPoint> getAllRefreshmentPoints() {
        return new HashMap<>(database); // Return a copy to prevent external modification
    }
}