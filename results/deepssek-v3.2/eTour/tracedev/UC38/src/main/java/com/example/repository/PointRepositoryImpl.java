package com.example.repository;

import com.example.entity.PointOfRestaurant;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of PointRepository.
 * In a real application, this would interact with a database.
 */
public class PointRepositoryImpl implements PointRepository {
    // In-memory storage simulation for demonstration
    private Map<String, PointOfRestaurant> storage = new HashMap<>();

    public PointRepositoryImpl() {
        // Initialize with some sample data
        storage.put("point1", new PointOfRestaurant("point1", "Main Entrance", 5));
        storage.put("point2", new PointOfRestaurant("point2", "Dining Area", 3));
    }

    @Override
    public PointOfRestaurant findByPointId(String pointId) {
        return storage.get(pointId);
    }

    @Override
    public boolean update(PointOfRestaurant point) {
        storage.put(point.getPointId(), point);
        return true;
    }
}