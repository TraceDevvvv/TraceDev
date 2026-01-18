package com.example.infrastructure;

import com.example.application.ITouristRepository;
import com.example.domain.Tourist;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * Infrastructure Layer - Implements Repository Pattern.
 * Simulates database operations using an in‑memory map.
 */
public class TouristRepository implements ITouristRepository {
    // Simulating DbContext with a map
    private Map<Integer, Tourist> touristDatabase = new HashMap<>();
    private DbContext dbContext;

    public TouristRepository() {
        this.dbContext = new DbContext();
        // Populate some sample data for demonstration
        touristDatabase.put(1, new Tourist(1, "John Doe", "john@example.com", true));
        touristDatabase.put(2, new Tourist(2, "Jane Smith", "jane@example.com", true));
        touristDatabase.put(3, new Tourist(3, "Bob Johnson", "bob@example.com", true));
    }

    @Override
    public Tourist getById(int id) {
        // Returns null if not found (as per sequence diagram alternative flow)
        return touristDatabase.get(id);
    }

    @Override
    public List<Tourist> getAll() {
        return new ArrayList<>(touristDatabase.values());
    }

    @Override
    public void delete(Tourist tourist) {
        // In a real implementation, this would mark for deletion or remove from DB.
        // Here we just remove from map.
        touristDatabase.remove(tourist.getId());
    }

    @Override
    public void update(Tourist tourist) {
        touristDatabase.put(tourist.getId(), tourist);
    }
}