package com.example.infrastructure;

import com.example.domain.Site; // Although not directly manipulated by addVisitedSite, it's a managed entity.
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Repository for managing Site related data.
 * Simulates persistence operations, specifically tracking visited sites by tourists.
 */
public class SiteRepository {

    // In-memory store: Map touristId to a Set of siteIds they have visited
    private final Map<String, Set<String>> visitedSitesByTourist = new HashMap<>();

    /**
     * Records that a specific tourist has visited a specific site.
     * This method is called after a successful feedback submission, indicating
     * the site was 'visited' in the context of this use case.
     * @param touristId The ID of the tourist.
     * @param siteId The ID of the site.
     */
    public void addVisitedSite(String touristId, String siteId) {
        System.out.println("[DB] Recording visited site for tourist '" + touristId + "': '" + siteId + "'");
        visitedSitesByTourist.computeIfAbsent(touristId, k -> new HashSet<>()).add(siteId);
        System.out.println("[DB] Visited site recorded successfully.");
    }

    /**
     * Utility for testing: check if a tourist has visited a site.
     * (Not part of the original UML, but useful for verification).
     * @param touristId The ID of the tourist.
     * @param siteId The ID of the site.
     * @return true if the tourist has visited the site, false otherwise.
     */
    public boolean hasTouristVisitedSite(String touristId, String siteId) {
        return visitedSitesByTourist.getOrDefault(touristId, new HashSet<>()).contains(siteId);
    }
}