package com.example;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Implementation of TouristSiteRepository.
 * Uses in‑memory maps for demonstration.
 */
public class TouristSiteRepositoryImpl implements TouristSiteRepository {
    // Map touristId -> set of visited site IDs
    private Map<String, Set<String>> visitedSites = new HashMap<>();
    // Valid site IDs
    private Set<String> validSites = new HashSet<>();
    // Tourist card status
    private Map<String, Boolean> touristCardStatus = new HashMap<>();

    public TouristSiteRepositoryImpl() {
        // Populate with some dummy data
        validSites.add("SITE001");
        validSites.add("SITE002");
        touristCardStatus.put("TOURIST001", true);
        touristCardStatus.put("TOURIST002", true);
    }

    @Override
    public boolean addToVisitedSites(String touristId, String siteId) {
        // Simulate server error: if siteId contains "ERROR", return false.
        if (siteId.contains("ERROR")) {
            return false;
        }
        visitedSites.computeIfAbsent(touristId, k -> new HashSet<>()).add(siteId);
        return true;
    }

    @Override
    public boolean isValidSite(String siteId) {
        return validSites.contains(siteId);
    }

    @Override
    public boolean getTouristCardStatus(String touristId) {
        return touristCardStatus.getOrDefault(touristId, false);
    }
}