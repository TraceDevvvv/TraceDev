package com.etour.service;

import com.etour.exception.ConnectionException;
import com.etour.model.RefreshmentPointDetails;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Simulates interaction with ETOUR server
 */
public class ETOURServerService {
    private boolean connectionActive = true;
    private Random random = new Random();

    public RefreshmentPointDetails fetchRefreshmentPointData(String pointId) {
        // Check connection before fetching
        if (!checkConnection()) {
            throw new ConnectionException("Connection to ETOUR server interrupted");
        }

        // Simulate fetching data from server
        // In a real scenario, this would be an API call
        List<String> amenities = Arrays.asList("WiFi", "Restrooms", "Parking");
        return new RefreshmentPointDetails(pointId, "Café Central", "123 Main St", "Cafe",
                "40.7128,-74.0060", 50, amenities, "8:00-22:00");
    }

    private boolean checkConnection() {
        // For demo, simulate occasional connection failure (20% chance)
        if (random.nextDouble() < 0.2) {
            connectionActive = false;
            return false;
        }
        connectionActive = true;
        return true;
    }
}