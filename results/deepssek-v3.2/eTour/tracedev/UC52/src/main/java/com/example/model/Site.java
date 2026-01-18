package com.example.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a tourist site.
 */
public class Site {
    private String siteId;
    private String name;
    private static Set<String> existingSiteIds = new HashSet<>(); // Simulates site registry

    static {
        // Pre-populate some sites for simulation
        existingSiteIds.add("site1");
        existingSiteIds.add("site2");
        existingSiteIds.add("site3");
    }

    public Site(String siteId, String name) {
        this.siteId = siteId;
        this.name = name;
    }

    public String getSiteId() {
        return siteId;
    }

    public String getName() {
        return name;
    }

    /**
     * Checks if this site exists in the system.
     */
    public boolean exists() {
        return existingSiteIds.contains(siteId);
    }

    // For testing: add a site
    public static void addSite(String siteId) {
        existingSiteIds.add(siteId);
    }
}