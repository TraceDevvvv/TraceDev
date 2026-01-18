package com.system.repository;

import com.system.RefreshmentPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * In-memory implementation of RefreshmentPointRepository for demonstration.
 */
public class RefreshmentPointRepositoryImpl implements RefreshmentPointRepository {
    private List<RefreshmentPoint> points = new ArrayList<>();

    public RefreshmentPointRepositoryImpl() {
        // Pre-populate with a sample point for testing.
        points.add(new RefreshmentPoint("RP001", "Main Entrance Kiosk"));
    }

    @Override
    public Optional<RefreshmentPoint> findById(String id) {
        return points.stream().filter(p -> id.equals(p.getId())).findFirst();
    }

    @Override
    public RefreshmentPoint save(RefreshmentPoint point) {
        // Simulate save; if exists, replace; otherwise add.
        points.removeIf(p -> p.getId().equals(point.getId()));
        points.add(point);
        return point;
    }

    @Override
    public List<RefreshmentPoint> findAll() {
        return new ArrayList<>(points);
    }
}