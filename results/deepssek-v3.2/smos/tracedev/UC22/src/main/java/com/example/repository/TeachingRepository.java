package com.example.repository;

import com.example.model.Teaching;
import java.util.List;

/**
 * Repository interface for Teaching entities.
 * Defines data access operations.
 */
public interface TeachingRepository {
    /**
     * Retrieves all teachings from the database.
     * @return List of all Teaching entities.
     */
    List<Teaching> findAll();

    /**
     * Finds a teaching by its ID.
     * @param id The teaching ID.
     * @return The Teaching entity, or null if not found.
     */
    Teaching findById(Long id);
}