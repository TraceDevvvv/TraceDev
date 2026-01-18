package com.example.service;

import com.example.model.RefreshmentPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Service to manage refreshment points.
 */
public class RefreshmentPointService {
    private List<RefreshmentPoint> refreshmentPoints;

    public RefreshmentPointService() {
        this.refreshmentPoints = new ArrayList<>();
        // Add some sample data for testing
        refreshmentPoints.add(new RefreshmentPoint("1", "Cafe Central", "Coffee and snacks", "Main Street 1"));
        refreshmentPoints.add(new RefreshmentPoint("2", "Park Bistro", "Light meals", "Central Park"));
        refreshmentPoints.add(new RefreshmentPoint("3", "Museum Cafe", "Drinks and pastries", "Art Museum"));
    }

    /**
     * Returns a list of all refreshment points (simulating SearchCulturalHeritage use case).
     */
    public List<RefreshmentPoint> getAllRefreshmentPoints() {
        return new ArrayList<>(refreshmentPoints);
    }

    /**
     * Finds a refreshment point by its ID.
     */
    public Optional<RefreshmentPoint> findById(String id) {
        return refreshmentPoints.stream()
                .filter(rp -> rp.getId().equals(id))
                .findFirst();
    }

    /**
     * Deletes a refreshment point by its ID.
     * Returns true if deletion was successful, false otherwise.
     */
    public boolean deleteRefreshmentPoint(String id) {
        return refreshmentPoints.removeIf(rp -> rp.getId().equals(id));
    }

    /**
     * Adds a refreshment point (for testing purposes).
     */
    public void addRefreshmentPoint(RefreshmentPoint rp) {
        refreshmentPoints.add(rp);
    }
}