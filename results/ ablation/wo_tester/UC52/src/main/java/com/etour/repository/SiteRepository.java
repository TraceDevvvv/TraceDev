package com.etour.repository;

import com.etour.model.Site;
import java.util.HashMap;
import java.util.Map;

/**
 * Repository for managing Site entities.
 */
public class SiteRepository {
    // In-memory storage for demo purposes.
    private Map<String, Site> sites = new HashMap<>();

    public SiteRepository() {
        // Initialize with some dummy data.
        sites.put("site1", new Site("site1", "Eiffel Tower", "Famous tower in Paris"));
        sites.put("site2", new Site("site2", "Colosseum", "Ancient amphitheater in Rome"));
    }

    /**
     * Retrieves a site by its ID.
     * @param siteId the site ID.
     * @return the site, or null if not found.
     */
    public Site getById(String siteId) {
        return sites.get(siteId);
    }

    /**
     * Checks if a site exists.
     * @param siteId the site ID.
     * @return true if exists, false otherwise.
     */
    public boolean exists(String siteId) {
        return sites.containsKey(siteId);
    }

    /**
     * Adds a site to the repository (for initialization).
     * @param site the site to add.
     */
    public void addSite(Site site) {
        sites.put(site.getSiteId(), site);
    }
}