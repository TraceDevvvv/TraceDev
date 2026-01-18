package com.touristagency.repository;

import com.touristagency.model.Tourist;

import java.util.*;

/**
 * Repository for managing Tourist entities.
 * Simulates database operations.
 */
public class TouristRepository {
    private final Map<String, Tourist> touristMap = new HashMap<>();

    public TouristRepository() {
        // Initialize with some sample data
        touristMap.put("T001", new Tourist("T001", "Alice Smith", "alice@example.com", true));
        touristMap.put("T002", new Tourist("T002", "Bob Johnson", "bob@example.com", false));
        touristMap.put("T003", new Tourist("T003", "Carol Davis", "carol@example.com", true));
    }

    /**
     * Find a tourist by ID.
     *
     * @param touristId the tourist ID
     * @return the Tourist object or null if not found
     */
    public Tourist findById(String touristId) {
        return touristMap.get(touristId);
    }

    /**
     * Update the tourist in the repository.
     *
     * @param tourist the tourist to update
     * @return true if update succeeded, false if tourist not found
     */
    public boolean update(Tourist tourist) {
        if (tourist == null || !touristMap.containsKey(tourist.getId())) {
            return false;
        }
        touristMap.put(tourist.getId(), tourist);
        return true;
    }

    /**
     * Get all tourists for display purposes.
     *
     * @return list of all tourists
     */
    public List<Tourist> findAll() {
        return new ArrayList<>(touristMap.values());
    }
}