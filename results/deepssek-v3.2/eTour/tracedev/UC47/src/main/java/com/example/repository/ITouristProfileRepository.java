package com.example.repository;

import com.example.model.TouristProfileEntity;

/**
 * Repository interface for tourist profile data access.
 */
public interface ITouristProfileRepository {
    /**
     * Find tourist profile by ID.
     * Step in Sequence Diagram: Repository.findById(touristId)
     */
    TouristProfileEntity findById(String id);

    /**
     * Save tourist profile entity.
     * Step in Sequence Diagram: Repository.save(updatedEntity)
     */
    boolean save(TouristProfileEntity entity);
}