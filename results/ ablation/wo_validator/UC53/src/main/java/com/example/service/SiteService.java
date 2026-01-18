package com.example.service;

import com.example.model.Site;

/**
 * Service interface for site operations.
 */
public interface SiteService {
    /**
     * Adds a site to the tourist's visited list.
     * @param touristId Tourist identifier.
     * @param siteId Site identifier.
     * @return true if successful, false otherwise.
     */
    boolean addSiteToVisitedList(String touristId, String siteId);

    /**
     * Gets the current site for a tourist (based on tourist card ID).
     * @param touristCardId The tourist's card identifier.
     * @return Site object representing the current site.
     */
    Site getCurrentSite(String touristCardId);
}