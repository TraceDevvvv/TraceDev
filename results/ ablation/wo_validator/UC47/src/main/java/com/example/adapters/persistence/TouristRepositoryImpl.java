
package com.example.adapters.persistence;

import com.example.business.TouristRepository;
import com.example.core.TouristEntity;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of TouristRepository using an in‑memory map for demonstration.
 * In a real application, this would connect to a database via DatabaseGateway.
 */
public class TouristRepositoryImpl implements TouristRepository {
    // Simulating a database with an in‑memory map
    private Map<String, TouristEntity> store = new HashMap<>();
    // Assume DatabaseGateway is injected (simplified here)
    private Object databaseGateway;

    public TouristRepositoryImpl(Object databaseGateway) {
        this.databaseGateway = databaseGateway;
        // Pre‑populate with a sample tourist for testing
        store.put("tourist123", new TouristEntity("tourist123", "john_doe", "john@example.com", "+1234567890", "hashed_password"));
    }

    @Override
    public TouristEntity findById(String touristId) {
        // In real implementation, we would use databaseGateway.loadUserData(touristId)
        // and convert Map<String, String> to TouristEntity.
        // For simplicity, we return from in‑memory store.
        return store.get(touristId);
    }

    @Override
    public void save(TouristEntity tourist) {
        // In real implementation, we would call databaseGateway.saveUserData(...)
        // For now, just update the in‑memory store.
        store.put(tourist.getUserId(), tourist);
        // Simulate database save
        Map<String, String> data = new HashMap<>();
        data.put("username", tourist.getUsername());
        data.put("email", tourist.getEmail());
        data.put("phone", tourist.getPhone());
        // Note: databaseGateway.saveUserData() call removed as DatabaseGateway class doesn't exist
    }
}
