package com.example.etour.service;

import com.example.etour.model.RefreshmentPoint;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for managing refreshment points.
 */
public class RefreshmentPointService {
    // Simulated in‑memory storage
    private List<RefreshmentPoint> points = new ArrayList<>();

    public RefreshmentPointService() {
        // Add sample refreshment points
        points.add(new RefreshmentPoint(1, "Rest Point Alpha", "Main Street 1"));
        points.add(new RefreshmentPoint(2, "Rest Point Beta", "Second Avenue 5"));
        points.add(new RefreshmentPoint(3, "Rest Point Gamma", "Park Lane 12"));
    }

    /**
     * Return all available refreshment points.
     */
    public List<RefreshmentPoint> getAllRefreshmentPoints() {
        return new ArrayList<>(points);
    }

    /**
     * Find a refreshment point by ID.
     */
    public RefreshmentPoint findById(int id) {
        return points.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
    }
}