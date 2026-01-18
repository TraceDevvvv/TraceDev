package com.example.repository;

import com.example.model.Convention;

/**
 * Repository interface for Convention persistence operations.
 */
public interface ConventionRepository {
    /**
     * Save a convention and return its generated ID.
     */
    int save(Convention convention);

    /**
     * Find a convention by its ID.
     */
    Convention findById(int conventionId);
}