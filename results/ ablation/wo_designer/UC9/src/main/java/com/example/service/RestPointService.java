package com.example.service;

import com.example.dto.RestPoint;
import com.example.dto.RestPointSearchCriteria;
import com.example.exception.ServerConnectionException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service for handling rest point search operations.
 */
public class RestPointService {

    // Simulated in-memory database of rest points
    private static final List<RestPoint> REST_POINTS_DB = new ArrayList<>();

    static {
        // Initialize with some sample data
        REST_POINTS_DB.add(new RestPoint("1", "Mountain Hut", "Alps", true, true, 4));
        REST_POINTS_DB.add(new RestPoint("2", "Forest Shelter", "Black Forest", true, false, 3));
        REST_POINTS_DB.add(new RestPoint("3", "Lake Rest Area", "Lake District", false, true, 5));
        REST_POINTS_DB.add(new RestPoint("4", "Desert Oasis", "Sahara", false, true, 2));
        REST_POINTS_DB.add(new RestPoint("5", "Hilltop Cabin", "Highlands", true, true, 5));
    }

    /**
     * Searches for rest points based on the provided criteria.
     * Simulates a search that must complete within 15 seconds.
     * @param criteria the search criteria
     * @return list of matching rest points
     * @throws ServerConnectionException if connection to server fails
     */
    public List<RestPoint> searchRestPoints(RestPointSearchCriteria criteria) throws ServerConnectionException {
        // Simulate server connection check (random failure for demonstration)
        if (Math.random() < 0.05) { // 5% chance of connection failure
            throw new ServerConnectionException("Connection to server ETOUR interrupted.");
        }

        // Simulate processing time (max 15 seconds as per requirement)
        long startTime = System.currentTimeMillis();
        List<RestPoint> result = performSearch(criteria);
        long elapsed = System.currentTimeMillis() - startTime;

        // Ensure operation completes within 15 seconds (simulated delay)
        if (elapsed > 15000) {
            // In real scenario, we would implement timeout logic
            System.err.println("Warning: Search took longer than 15 seconds.");
        }

        return result;
    }

    private List<RestPoint> performSearch(RestPointSearchCriteria criteria) {
        return REST_POINTS_DB.stream()
                .filter(rp -> matchesCriteria(rp, criteria))
                .collect(Collectors.toList());
    }

    private boolean matchesCriteria(RestPoint rp, RestPointSearchCriteria criteria) {
        // Match name (case-insensitive partial match)
        if (criteria.getName() != null && !criteria.getName().isEmpty()) {
            if (!rp.getName().toLowerCase().contains(criteria.getName().toLowerCase())) {
                return false;
            }
        }

        // Match location (case-insensitive partial match)
        if (criteria.getLocation() != null && !criteria.getLocation().isEmpty()) {
            if (!rp.getLocation().toLowerCase().contains(criteria.getLocation().toLowerCase())) {
                return false;
            }
        }

        // Match shelter requirement
        if (criteria.getHasShelter() != null) {
            if (rp.isHasShelter() != criteria.getHasShelter()) {
                return false;
            }
        }

        // Match water requirement
        if (criteria.getHasWater() != null) {
            if (rp.isHasWater() != criteria.getHasWater()) {
                return false;
            }
        }

        // Match minimum rating
        if (criteria.getMinRating() != null) {
            if (rp.getRating() < criteria.getMinRating()) {
                return false;
            }
        }

        // Note: maxDistance not implemented as we lack geographical coordinates
        return true;
    }
}