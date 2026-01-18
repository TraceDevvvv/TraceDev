package com.etour.repository;

import com.etour.domain.Site;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for Site entities.
 */
public interface SiteRepository {
    /**
     * Finds a site by its ID.
     *
     * @param siteId the site ID
     * @return an Optional containing the Site if found, empty otherwise
     */
    Optional<Site> findById(String siteId);

    /**
     * Fetches all sites.
     *
     * @return a List of all sites
     */
    List<Site> fetchAll();
}