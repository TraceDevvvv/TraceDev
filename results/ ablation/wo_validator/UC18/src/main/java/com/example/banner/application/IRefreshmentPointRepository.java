package com.example.banner.application;

import com.example.banner.domain.RefreshmentPoint;

/**
 * Repository interface for RefreshmentPoint.
 * Part of the application layer (port).
 */
public interface IRefreshmentPointRepository {
    /**
     * Finds a refreshment point by its id.
     * @param id the refreshment point identifier
     * @return the RefreshmentPoint or null if not found
     */
    RefreshmentPoint findById(String id);

    /**
     * Saves or updates a refreshment point.
     * @param refreshmentPoint the entity to save
     */
    void save(RefreshmentPoint refreshmentPoint);
}