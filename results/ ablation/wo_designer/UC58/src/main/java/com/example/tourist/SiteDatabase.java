package com.example.tourist;

import java.util.HashMap;
import java.util.Map;

/**
 * Simulates a database of sites. In a real application, this would connect to an actual database.
 */
public class SiteDatabase {
    private static final Map<String, Site> SITES = new HashMap<>();

    static {
        // Pre-populate with sample data
        SITES.put("1", new Site("1", "Eiffel Tower", "A wrought-iron lattice tower on the Champ de Mars in Paris, France.", "Paris, France", 4.8, 10000, true));
        SITES.put("2", new Site("2", "Colosseum", "An oval amphitheatre in the centre of the city of Rome, Italy.", "Rome, Italy", 4.7, 8000, false));
        SITES.put("3", new Site("3", "Statue of Liberty", "A colossal neoclassical sculpture on Liberty Island in New York Harbor.", "New York, USA", 4.6, 9500, true));
    }

    /**
     * Retrieves a site by its ID.
     * @param siteId the ID of the site
     * @return the Site object, or null if not found
     */
    public Site getSiteById(String siteId) {
        // Simulate database retrieval delay (max 1.5 seconds to meet quality requirement)
        try {
            Thread.sleep(1500); // Simulate network/database delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return SITES.get(siteId);
    }
}