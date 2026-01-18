package com.tourism.infrastructure;

import com.tourism.domain.Site;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of ISiteRepository.
 * Simulates database access. In a real application, this would connect to a DB.
 */
public class SiteRepositoryImpl implements ISiteRepository {
    /**
     * Simulates a network‑dependent database query.
     * Throws a RuntimeException to simulate connection interruption (REQ‑008).
     */
    @Override
    public List<Site> findSitesByTouristFeedback(String touristId) {
        // Simulate possible connection interruption
        if (Math.random() < 0.2) { // 20% chance to simulate interruption
            throw new RuntimeException("Server connection interrupted");
        }

        // Simulate data retrieval
        List<Site> sites = new ArrayList<>();
        // Sample data for demonstration
        if ("TOURIST123".equals(touristId)) {
            sites.add(new Site("SITE001", "Eiffel Tower", "Paris, France"));
            sites.add(new Site("SITE002", "Colosseum", "Rome, Italy"));
        } else if ("TOURIST456".equals(touristId)) {
            sites.add(new Site("SITE003", "Statue of Liberty", "New York, USA"));
        }
        return sites;
    }
}