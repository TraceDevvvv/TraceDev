package com.example.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a tourist with bookmarks and location tracking.
 */
public class Tourist {
    private String touristId;
    private Set<String> visitedSites; // Tracks sites the tourist is currently at (for simulation)

    public Tourist(String touristId) {
        this.touristId = touristId;
        this.visitedSites = new HashSet<>();
    }

    public String getTouristId() {
        return touristId;
    }

    public void removeBookmark(String siteId) {
        // Implementation would interact with bookmark repository; here we just simulate.
        System.out.println("Tourist " + touristId + " requests removal of bookmark for site " + siteId);
    }

    public void cancelOperation() {
        System.out.println("Tourist " + touristId + " cancels operation.");
    }

    /**
     * Checks if the tourist is currently at the given site (for entry condition).
     * This is a simulation; in real scenario, location tracking would be used.
     */
    public boolean isTouristAtSite(String siteId) {
        // Simulate: assume tourist is at site if it's in visitedSites
        return visitedSites.contains(siteId);
    }

    // Helper to simulate tourist visiting a site (for testing)
    public void visitSite(String siteId) {
        visitedSites.add(siteId);
    }

    // Helper to simulate leaving a site (for testing)
    public void leaveSite(String siteId) {
        visitedSites.remove(siteId);
    }
}