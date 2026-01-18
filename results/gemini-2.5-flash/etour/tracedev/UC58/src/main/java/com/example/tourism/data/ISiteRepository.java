package com.example.tourism.data;

import com.example.tourism.domain.Site;

/**
 * Data Access Layer - Interface: Defines the contract for site data operations.
 * This promotes loose coupling between the service layer and the concrete data access implementation.
 */
public interface ISiteRepository {

    /**
     * Finds a site by its unique identifier.
     * @param siteId The ID of the site to find.
     * @return The Site entity if found, otherwise null.
     */
    Site findById(String siteId);
}