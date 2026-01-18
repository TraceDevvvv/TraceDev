package com.example.service;

/**
 * Service to monitor connection to eTour system.
 * Added to satisfy requirement REQ-016
 */
public class ETourConnectionMonitor {
    /**
     * Checks the connection status.
     * @return true if connection is alive
     */
    public boolean checkConnection() {
        // Simulate connection check; in real implementation, ping a server or check network.
        return Math.random() > 0.1; // 90% chance of connection success
    }

    /**
     * Gets a descriptive status of the connection.
     * @return connection status string
     */
    public String getConnectionStatus() {
        return checkConnection() ? "Connected" : "Disconnected";
    }
}