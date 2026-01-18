package com.touristagency.infrastructure.persistence;

import com.touristagency.domain.Tourist;
import com.touristagency.domain.TouristRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of TouristRepository.
 * For simplicity, uses a static map. In a real system, this would connect to a database.
 */
public class TouristRepositoryImpl implements TouristRepository {
    // Simulating a database with an in-memory map
    private static final Map<String, Tourist> touristStore = new HashMap<>();

    // Static initializer for sample data
    static {
        touristStore.put("tourist123", new Tourist("tourist123", "John Doe", "john@example.com"));
        touristStore.put("tourist456", new Tourist("tourist456", "Jane Smith", "jane@example.com"));
    }

    @Override
    public Tourist findById(String id) {
        return touristStore.get(id);
    }

    @Override
    public Tourist save(Tourist tourist) {
        touristStore.put(tourist.getId(), tourist);
        return tourist;
    }
}