/*
 * Provides simulated data access for sites.
 * In a real application, this would interact with a database or external API.
 */
package com.chatdev.service;
import com.chatdev.model.Site;
import com.chatdev.exception.ConnectionException; // Import common ConnectionException
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class SiteService {
    // Simulated database/data store for sites
    private static List<Site> sites;
    /**
     * Static initializer to populate some dummy site data.
     */
    static {
        sites = new ArrayList<>(Arrays.asList(
                new Site("SITE001", "ChatDev Office HQ"),
                new Site("SITE002", "Remote Work Hub A"),
                new Site("SITE003", "Training Center Beta"),
                new Site("SITE004", "Development Lab Z")
        ));
    }
    /**
     * Retrieves a list of all available sites.
     * Simulates the 'RicercaSito' use case result.
     * @return A list of Site objects.
     * @throws ConnectionException If a simulated server connection fails.
     */
    public List<Site> getAllSites() throws ConnectionException {
        // Simulate potential connection issues with a small chance
        if (Math.random() < 0.1) { // 10% chance of failure
            throw new ConnectionException("Simulated connection interruption during site retrieval.");
        }
        return new ArrayList<>(sites); // Return a copy to prevent external modification
    }
    /**
     * Retrieves a specific site by its ID.
     * @param siteId The unique identifier of the site.
     * @return The Site object if found, otherwise null.
     */
    public Site getSiteById(String siteId) {
        for (Site site : sites) {
            if (site.getId().equals(siteId)) {
                return site;
            }
        }
        return null;
    }
}