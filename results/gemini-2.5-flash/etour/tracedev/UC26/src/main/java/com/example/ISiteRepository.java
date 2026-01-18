package com.example;

import java.util.List;

/**
 * Interface for managing Site persistence.
 * Defines operations for retrieving Site entities.
 */
public interface ISiteRepository {
    /**
     * Retrieves all available Site entities.
     * @return A list of all Site entities. Returns an empty list on simulated DB errors.
     */
    List<Site> findAll();

    /**
     * Finds a Site entity by its unique ID.
     * @param siteId The ID of the site to find.
     * @return The Site entity if found, null otherwise. Returns null also on simulated DB errors.
     */
    Site findById(String siteId);
}