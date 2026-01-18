package com.example;

/**
 * Repository interface for Convention entities.
 * Defines methods to find conventions.
 */
public interface ConventionRepository {
    /**
     * Finds a convention by its ID.
     * @param id The ID of the convention.
     * @return The Convention object, or null if not found.
     */
    Convention findById(String id);
}