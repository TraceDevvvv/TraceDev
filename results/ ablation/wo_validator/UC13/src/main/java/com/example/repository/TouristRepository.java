package com.example.repository;

import com.example.tourist.Tourist;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * Repository implementation for managing Tourist entities.
 * Uses an in-memory map for simplicity; in a real application would use a DataSource.
 */
public class TouristRepository implements TouristRepositoryInterface {
    private DataSource dataSource;
    // In-memory storage for demonstration
    private Map<String, Tourist> touristMap = new HashMap<>();

    public TouristRepository(DataSource dataSource) {
        this.dataSource = dataSource;
        // Initialize with some dummy data for testing
        touristMap.put("T001", new Tourist("T001", "John Doe", "john@example.com"));
        touristMap.put("T002", new Tourist("T002", "Jane Smith", "jane@example.com"));
    }

    @Override
    public Tourist findById(String id) {
        return touristMap.get(id);
    }

    @Override
    public Tourist update(Tourist tourist) {
        if (tourist == null || tourist.getId() == null) {
            return null;
        }
        touristMap.put(tourist.getId(), tourist);
        return tourist;
    }

    @Override
    public Tourist save(Tourist tourist) {
        // For simplicity, treat save same as update
        return update(tourist);
    }
}