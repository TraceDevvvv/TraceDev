package com.example.repository;

import com.example.model.Site;
import com.example.service.NetworkConnectionException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Concrete implementation of ISiteRepository.
 * This class simulates data access for Sites.
 */

public class SiteRepository implements ISiteRepository {

    // Represents a database connection or ORM context. For this example, it's a dummy object.
    private Object dataSource;

    // Dummy data for simulation
    private final List<Site> dummySites;

    // Flag to simulate network connection issues
    private boolean simulateNetworkError = false;

    /**
     * Constructs a SiteRepository with a given data source.
     * @param dataSource The data source object (e.g., database connection pool).
     */
    public SiteRepository(Object dataSource) {
        this.dataSource = dataSource;
        this.dummySites = new ArrayList<>(Arrays.asList(
            new Site("S001", "Eiffel Tower", "Paris, France"),
            new Site("S002", "Statue of Liberty", "New York, USA"),
            new Site("S003", "Great Wall of China", "Beijing, China")
        ));
        System.out.println("SiteRepository: Initialized with dummy data.");
    }

    @Override
    public Site getSiteById(String id) {
        System.out.println("SiteRepository: Retrieving site by ID: " + id + " from data source.");
        // Simulate database lookup
        for (Site site : dummySites) {
            if (site.getSiteId().equals(id)) {
                return site;
            }
        }
        return null;
    }

    @Override
    public List<Site> getAllSites() throws NetworkConnectionException {
        System.out.println("SiteRepository: Retrieving all sites from data source.");
        if (simulateNetworkError) {
            System.out.println("SiteRepository: Simulating NetworkConnectionException for getAllSites().");
            throw new NetworkConnectionException("Failed to connect to site data source.");
        }
        // Simulate network delay
        try {
            Thread.sleep(100); // Simulate some processing time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new ArrayList<>(dummySites); // Return a copy to prevent external modification
    }

    /**
     * Sets a flag to simulate a network connection error for testing purposes.
     * @param simulateNetworkError If true, getAllSites() will throw NetworkConnectionException.
     */
    public void setSimulateNetworkError(boolean simulateNetworkError) {
        this.simulateNetworkError = simulateNetworkError;
    }
}