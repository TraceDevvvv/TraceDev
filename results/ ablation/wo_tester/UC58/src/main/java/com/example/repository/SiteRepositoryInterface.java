package com.example.repository;

import com.example.model.SiteEntity;

/**
 * Interface for site repository operations.
 */
public interface SiteRepositoryInterface {
    /**
     * Finds a site entity by its ID.
     * @param id the site ID
     * @return SiteEntity if found, null otherwise
     */
    SiteEntity findById(String id);
}