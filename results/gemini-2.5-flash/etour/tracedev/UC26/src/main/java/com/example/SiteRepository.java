package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of ISiteRepository.
 * This class simulates database interactions using a HashMap.
 * It includes a flag for simulating database connection errors (REQ-002).
 */
public class SiteRepository implements ISiteRepository {
    private final Map<String, Site> siteStore = new HashMap<>();
    private boolean simulateDbError = false; // Flag to simulate REQ-002 network resilience issues

    /**
     * Constructor for SiteRepository. Initializes with some dummy data.
     */
    public SiteRepository() {
        siteStore.put("s1", new Site("s1", "Site Alpha", "Description for site Alpha."));
        siteStore.put("s2", new Site("s2", "Site Beta", "Description for site Beta."));
        siteStore.put("s3", new Site("s3", "Site Gamma", "Description for site Gamma."));
    }

    /**
     * Sets the flag to simulate database errors.
     * @param simulateDbError If true, repository methods will simulate failure.
     */
    public void setSimulateDbError(boolean simulateDbError) {
        this.simulateDbError = simulateDbError;
    }

    @Override
    public List<Site> findAll() {
        if (simulateDbError) {
            System.err.println("SIMULATED DB ERROR: Failed to retrieve all sites due to connection issues.");
            return new ArrayList<>(); // Simulate connection error for REQ-002
        }
        return new ArrayList<>(siteStore.values());
    }

    @Override
    public Site findById(String siteId) {
        if (simulateDbError) {
            System.err.println("SIMULATED DB ERROR: Failed to retrieve site by ID due to connection issues.");
            return null; // Simulate connection error for REQ-002
        }
        return siteStore.get(siteId);
    }
}