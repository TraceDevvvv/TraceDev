package com.example.application.repositories;

import com.example.application.model.Site;

/**
 * Repository interface for Site entities.
 * Defines the contract for data access operations.
 */
public interface SiteRepository {
    /**
     * Finds a Site by its id.
     *
     * @param siteId the id of the site to find
     * @return the Site object, or null if not found
     */
    Site findById(String siteId);
}