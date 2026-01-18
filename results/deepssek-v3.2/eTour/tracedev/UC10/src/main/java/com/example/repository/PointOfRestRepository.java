package com.example.repository;

import com.example.domain.PointOfRest;

/**
 * Repository interface for retrieving PointOfRest entities.
 */
public interface PointOfRestRepository {
    /**
     * Finds a PointOfRest by its ID.
     * @param id the point of rest identifier.
     * @return the PointOfRest entity.
     */
    PointOfRest findById(int id);
}