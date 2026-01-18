package com.example.repository;

import com.example.model.Site;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Repository for site data access.
 * Marked as reliable with >99.9% reliability (requirement R10).
 */
public class SiteRepository {
    // In-memory simulation of a database
    private List<Site> siteDatabase;

    public SiteRepository() {
        siteDatabase = new ArrayList<>();
        // Initialize with some sample data for demonstration
        siteDatabase.add(new Site("S1", "Central Park"));
        siteDatabase.add(new Site("S2", "City Museum"));
        siteDatabase.add(new Site("S3", "Tech Hub"));
        siteDatabase.add(new Site("S4", "Community Center"));
    }

    /**
     * Searches sites by criteria (e.g., name contains criteria).
     * Sequence diagram message m3: query sites by criteria
     */
    public List<Site> searchSites(String criteria) {
        // Simulating database query - sequence diagram message m3
        System.out.println("Querying sites by criteria: " + criteria);
        List<Site> result;
        if (criteria == null || criteria.trim().isEmpty()) {
            result = new ArrayList<>(siteDatabase);
        } else {
            String lowerCriteria = criteria.toLowerCase();
            result = siteDatabase.stream()
                    .filter(site -> site.getName().toLowerCase().contains(lowerCriteria))
                    .collect(Collectors.toList());
        }
        // Sequence diagram message m4: site list (return from DB)
        System.out.println("Returning site list from DB: " + result.size() + " sites");
        return result;
    }

    /**
     * Retrieves a site by its ID.
     * Sequence diagram message m10: query site data
     */
    public Site getSiteById(String siteId) {
        // Simulating database query - sequence diagram message m10
        System.out.println("Querying site data by ID: " + siteId);
        Site site = siteDatabase.stream()
                .filter(s -> s.getId().equals(siteId))
                .findFirst()
                .orElse(null);
        // Sequence diagram message m11: site data (return from DB)
        System.out.println("Returning site data from DB: " + (site != null ? site.getName() : "null"));
        return site;
    }
}