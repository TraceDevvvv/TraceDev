package com.example.repository;

import com.example.domain.RefreshmentPoint;
import com.example.domain.RefreshmentPointStatus;
import java.util.List;
import java.util.Optional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Concrete implementation of IRefreshmentPointRepository.
 * Uses an in-memory map for simplicity.
 */
public class RefreshmentPointRepositoryImpl implements IRefreshmentPointRepository {
    private Map<String, RefreshmentPoint> points = new HashMap<>();

    public RefreshmentPointRepositoryImpl() {
        // Initialize with some sample data
        RefreshmentPoint p1 = new RefreshmentPoint("RP001", "Main Cafe", "Central cafe with coffee", "Building A", RefreshmentPointStatus.ACTIVE, new HashMap<>());
        RefreshmentPoint p2 = new RefreshmentPoint("RP002", "Snack Bar", "Quick snacks", "Building B", RefreshmentPointStatus.ACTIVE, new HashMap<>());
        points.put(p1.getId(), p1);
        points.put(p2.getId(), p2);
    }

    @Override
    public List<RefreshmentPoint> findAllActive() {
        return points.values().stream()
                .filter(p -> p.getStatus() == RefreshmentPointStatus.ACTIVE)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<RefreshmentPoint> findById(String id) {
        return Optional.ofNullable(points.get(id));
    }

    @Override
    public RefreshmentPoint save(RefreshmentPoint point) {
        points.put(point.getId(), point);
        return point;
    }
}