package com.example.repository;

import com.example.model.Site;
import com.example.exception.DatabaseException;
import java.util.List;
import java.util.ArrayList;

/**
 * Implementation of SiteRepository.
 * Simulates database interaction and connection status.
 */
public class SiteRepositoryImpl implements SiteRepository {
    private boolean isConnected;

    public SiteRepositoryImpl() {
        // Assume connection is initially established
        this.isConnected = true;
    }

    @Override
    public List<Site> findAllSites() throws DatabaseException {
        // Simulate connection check
        if (!isConnected) {
            throw new DatabaseException("Database connection lost while fetching sites.", 1001);
        }

        // Simulate database query and result mapping
        List<Site> sites = new ArrayList<>();
        sites.add(new Site(1, "Site A", "New York"));
        sites.add(new Site(2, "Site B", "Los Angeles"));
        sites.add(new Site(3, "Site C", "Chicago"));
        return sites;
    }

    @Override
    public boolean isConnected() {
        // For simulation, we can randomly disconnect, but we'll keep it simple.
        return isConnected;
    }

    // Method to simulate connection interruption (for testing)
    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}