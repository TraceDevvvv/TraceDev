package com.example.banner.infrastructure;

import com.example.banner.domain.RefreshmentPoint;

/**
 * Adapter to external data source (e.g., E‑TOUR system).
 * Simulates loading refreshment point data.
 */
public class ConventionDataAdapter {
    // Simulated connection - in reality would be a database or API client
    private Object connection; // Using Object to represent ETOURConnection (assumed external type)

    public ConventionDataAdapter() {
        // Simulate establishing connection
        this.connection = new Object();
        System.out.println("ConventionDataAdapter: Connection established.");
    }

    /**
     * Loads refreshment point data from external system.
     * @param refreshmentPointId the point identifier
     * @return a RefreshmentPoint with dummy data (for simulation)
     */
    public RefreshmentPoint loadRefreshmentPointData(String refreshmentPointId) {
        // In a real implementation, this would query the external system
        // For simulation, we create a dummy point with max count 5 and current count 3
        RefreshmentPoint rp = new RefreshmentPoint(refreshmentPointId, "Refreshment Point " + refreshmentPointId, 5);
        // Simulate that there are already 3 banners
        for (int i = 0; i < 3; i++) {
            rp.incrementBannerCount();
        }
        System.out.println("ConventionDataAdapter: Loaded refreshment point " + refreshmentPointId);
        return rp;
    }

    /**
     * Terminates the external connection.
     */
    public void terminateConnection() {
        this.connection = null;
        System.out.println("ConventionDataAdapter: Connection terminated.");
    }
}