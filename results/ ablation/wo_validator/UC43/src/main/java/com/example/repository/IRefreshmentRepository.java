package com.example.repository;

import com.example.model.Refreshment;

/**
 * Repository interface for Refreshment entity.
 * (Already defined above, included here for completeness)
 */
public interface IRefreshmentRepository {
    /**
     * Finds a Refreshment by its id.
     */
    Refreshment findById(String id);

    /**
     * Saves or updates a Refreshment entity.
     */
    Refreshment save(Refreshment refreshment);
}