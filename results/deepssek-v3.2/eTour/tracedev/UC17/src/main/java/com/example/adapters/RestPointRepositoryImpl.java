package com.example.adapters;

import com.example.domain.RestPoint;
import com.example.exceptions.ConnectionException;
import com.example.ports.RestPointRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of RestPointRepository.
 * Uses an in-memory map for simplicity.
 */
public class RestPointRepositoryImpl implements RestPointRepository {
    // Simulating DataSource with in-memory storage
    private Map<String, RestPoint> restPointStore = new HashMap<>();

    public RestPointRepositoryImpl() {
        // Initialize with some dummy data
        restPointStore.put("rest1", new RestPoint("rest1", "Rest Point 1"));
        restPointStore.put("rest2", new RestPoint("rest2", "Rest Point 2"));
    }

    @Override
    public List<RestPoint> findAll() {
        // Simulate potential connection error
        if (Math.random() < 0.1) { // 10% chance for simulation
            throw new ConnectionException("Connection lost while finding all rest points");
        }
        return new ArrayList<>(restPointStore.values());
    }

    @Override
    public RestPoint findById(String restPointId) {
        // Simulate potential connection error
        if (Math.random() < 0.1) { // 10% chance for simulation
            throw new ConnectionException("Connection lost while finding rest point by ID");
        }
        return restPointStore.get(restPointId);
    }
}