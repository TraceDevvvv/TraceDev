package com.touristagency.domain;

/**
 * Repository interface for Tourist persistence operations.
 * Part of the domain layer.
 */
public interface TouristRepository {
    /**
     * Finds a tourist by ID.
     * @param id tourist identifier
     * @return Tourist or null if not found
     */
    Tourist findById(String id);

    /**
     * Saves (updates) a tourist.
     * @param tourist tourist to save
     * @return saved tourist
     */
    Tourist save(Tourist tourist);
}