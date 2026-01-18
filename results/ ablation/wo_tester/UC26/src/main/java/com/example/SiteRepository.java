package com.example;

import java.util.ArrayList;
import java.util.List;

/**
 * Repository for Site entities.
 */
public class SiteRepository {
    private Database database;
    // Simulated in-memory storage
    private List<Site> siteStore = new ArrayList<>();

    public SiteRepository(Database database) {
        this.database = database;
        // Pre-populate with sample sites
        siteStore.add(new Site("s1", "Site Alpha", "123 Main St"));
        siteStore.add(new Site("s2", "Site Beta", "456 Oak Ave"));
    }

    /**
     * Retrieves all sites.
     * @return list of all sites
     */
    public List<Site> findAll() {
        database.query("SELECT * FROM sites");
        return new ArrayList<>(siteStore);
    }

    /**
     * Finds a site by ID.
     * @param id site ID
     * @return Site or null if not found
     */
    public Site findById(String id) {
        database.query("SELECT * FROM sites WHERE id = " + id);
        return siteStore.stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}