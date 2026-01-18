package com.example.datasource;

import com.example.domain.Statistics;
import java.util.Arrays;
import java.util.List;

/**
 * Data source simulating server communication.
 * Includes connection handling per REQ-010.
 */
public class ServerDataSource {
    private boolean connectionHealthy = true; // Simulated connection state

    /**
     * Fetches statistics data from server.
     * May throw ConnectionException if connection fails (REQ-009).
     */
    public Statistics fetchStatisticsData(String operatorId) {
        if (!handleConnection()) {
            throw new RuntimeException("ConnectionException: Connection interrupted");
        }
        // Simulate fetched data
        List<String> peakHours = Arrays.asList("12:00-14:00", "18:00-20:00");
        return new Statistics(operatorId, 12500.75, 45.60, 274, peakHours);
    }

    /**
     * Checks and handles connection status.
     * @return true if connection is successful, false otherwise.
     */
    boolean handleConnection() {
        // Simulate occasional connection failure
        if (!connectionHealthy) {
            return false;
        }
        // In real scenario, would attempt ping/check network
        return true;
    }

    // For testing: simulate connection failure
    public void setConnectionHealthy(boolean healthy) {
        this.connectionHealthy = healthy;
    }
}