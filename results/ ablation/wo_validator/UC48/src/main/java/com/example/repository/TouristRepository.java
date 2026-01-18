package com.example.repository;

import com.example.model.Tourist;

/**
 * Repository interface for Tourist entities.
 */
public interface TouristRepository {
    /**
     * Finds a tourist by ID.
     * @param id the tourist ID
     * @return the Tourist, or null if not found
     */
    Tourist findById(String id);
}