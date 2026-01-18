package com.example;

/**
 * Repository interface for RefreshmentPoint entities.
 * Defines methods to find and save refreshment points.
 */
public interface RefreshmentPointRepository {
    /**
     * Finds a refreshment point by its ID.
     * @param id The ID of the refreshment point.
     * @return The RefreshmentPoint object, or null if not found.
     */
    RefreshmentPoint findById(String id);

    /**
     * Saves or updates a refreshment point.
     * @param point The refreshment point to save.
     */
    void save(RefreshmentPoint point);
}