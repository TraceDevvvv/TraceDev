package com.example.serv;

import com.example.models.RefreshmentPoint;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

/**
 * Implementation of RefreshmentPointService.
 * Simulates database/remote server interaction.
 */
public class RefreshmentPointServiceImpl implements RefreshmentPointService {
    // In-memory mock data
    private Map<String, RefreshmentPoint> mockDatabase;
    private Random random;

    public RefreshmentPointServiceImpl() {
        mockDatabase = new HashMap<>();
        random = new Random();
        initializeMockData();
    }

    private void initializeMockData() {
        mockDatabase.put("RP001", new RefreshmentPoint(
                "RP001", "Grand Central Cafe", "123 Main St, City",
                "+1-555-0101", "08:00-22:00", 50,
                "A cozy cafe with free Wi-Fi and charging stations."));
        mockDatabase.put("RP002", new RefreshmentPoint(
                "RP002", "Highway Diner", "456 Highway Rd, County",
                "+1-555-0202", "06:00-00:00", 100,
                "24/7 diner with truck parking and showers."));
        mockDatabase.put("RP003", new RefreshmentPoint(
                "RP003", "Mountain View Rest Area", "789 Mountain Pass",
                "+1-555-0303", "Always Open", 200,
                "Scenic rest area with picnic tables and restrooms."));
    }

    @Override
    public Optional<RefreshmentPoint> getRefreshmentPointDetails(String id) throws ServerConnectionException {
        // Simulate random server interruption
        if (random.nextInt(10) < 2) { // 20% chance of interruption
            throw new ServerConnectionException("Connection to server ETOUR interrupted.");
        }

        // Simulate network delay
        try {
            Thread.sleep(100 + random.nextInt(200));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        RefreshmentPoint point = mockDatabase.get(id);
        return Optional.ofNullable(point);
    }
}