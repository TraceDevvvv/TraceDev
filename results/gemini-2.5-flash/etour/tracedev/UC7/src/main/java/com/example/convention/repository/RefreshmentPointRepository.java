package com.example.convention.repository;

import com.example.convention.model.RefreshmentPoint;

/**
 * Interface for managing RefreshmentPoint persistence.
 * This interface defines the contract for operations related to RefreshmentPoint objects.
 */
public interface RefreshmentPointRepository {
    /**
     * Finds a RefreshmentPoint by its unique identifier.
     *
     * @param id The ID of the RefreshmentPoint to find.
     * @return The RefreshmentPoint object if found, null otherwise.
     */
    RefreshmentPoint findById(String id);
}