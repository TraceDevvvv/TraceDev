package com.example.repository;

import com.example.entity.Tourist;

/**
 * Repository interface for Tourist entities.
 * Added findById method for efficient data retrieval per REQ-014.
 */
public interface ITouristRepository {
    /**
     * Find tourist by ID.
     * <<Performance>> - REQ-014
     */
    Tourist findById(String touristId);
    
    void save(Tourist tourist);
}