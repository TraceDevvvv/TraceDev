package com.etour.banner.service;

import com.etour.banner.model.Banner;
import com.etour.banner.model.RefreshmentPoint;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Mock implementation of the {@link EtourClient} interface.
 * This class simulates interaction with an external ETOUR server by providing
 * hardcoded refreshment point data and logging banner insertion notifications.
 * In a production environment, this would be replaced by a client that
 * communicates with the actual ETOUR API.
 */
@Service
public class EtourClientImpl implements EtourClient {

    // In-memory storage for refreshment points, simulating data from ETOUR server
    private final ConcurrentMap<String, RefreshmentPoint> refreshmentPoints = new ConcurrentHashMap<>();

    /**
     * Initializes the mock refreshment points.
     */
    public EtourClientImpl() {
        // Populate with some sample data
        RefreshmentPoint rp1 = new RefreshmentPoint("rp001", "Central Cafe", 3);
        RefreshmentPoint rp2 = new RefreshmentPoint("rp002", "Parkside Bistro", 2);
        RefreshmentPoint rp3 = new RefreshmentPoint("rp003", "Riverside Diner", 5);

        refreshmentPoints.put(rp1.getId(), rp1);
        refreshmentPoints.put(rp2.getId(), rp2);
        refreshmentPoints.put(rp3.getId(), rp3);
    }

    /**
     * Fetches a list of all available refreshment points from the mock ETOUR server.
     *
     * @return A list of {@link RefreshmentPoint} objects.
     */
    @Override
    public List<RefreshmentPoint> fetchRefreshmentPoints() {
        // Simulate network delay
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return new ArrayList<>(refreshmentPoints.values());
    }

    /**
     * Notifies the mock ETOUR server about a newly inserted banner.
     * In this mock implementation, it simply prints a message to the console.
     *
     * @param banner The {@link Banner} that was inserted.
     * @param refreshmentPoint The {@link RefreshmentPoint} to which the banner was associated.
     */
    @Override
    public void notifyBannerInsertion(Banner banner, RefreshmentPoint refreshmentPoint) {
        // Simulate sending a notification to the ETOUR server
        System.out.println("ETOUR Client: Notifying ETOUR server about new banner insertion.");
        System.out.println("  Banner ID: " + banner.getId());
        System.out.println("  Image URL: " + banner.getImageUrl());
        System.out.println("  Refreshment Point ID: " + refreshmentPoint.getId());
        System.out.println("  Refreshment Point Name: " + refreshmentPoint.getName());
        // In a real scenario, this would involve an actual API call
    }
}