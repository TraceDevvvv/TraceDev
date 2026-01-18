package com.example.repository;

import com.example.entity.Banner;

/**
 * Implementation of BannerRepository.
 * In a real application, this would interact with a database.
 */
public class BannerRepositoryImpl implements BannerRepository {
    // In-memory storage simulation for demonstration
    private java.util.Map<String, Banner> storage = new java.util.HashMap<>();

    @Override
    public boolean save(Banner banner) throws DataAccessException {
        // Simulate potential server connection error
        if (Math.random() < 0.1) { // 10% chance of simulated error
            throw new DataAccessException("Server connection error simulated");
        }
        storage.put(banner.getBannerId(), banner);
        return true;
    }

    @Override
    public int countByPointId(String pointId) {
        return (int) storage.values().stream()
                .filter(b -> b.getPointId().equals(pointId))
                .count();
    }

    @Override
    public Banner findById(String id) {
        return storage.get(id);
    }
}