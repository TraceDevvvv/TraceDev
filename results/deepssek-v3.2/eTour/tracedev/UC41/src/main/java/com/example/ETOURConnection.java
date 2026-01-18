package com.example;

/**
 * ETOURConnection class representing connection to external ETOUR system.
 */
public class ETOURConnection {
    /**
     * Check the connection to ETOUR.
     * Added to satisfy requirement Exit Conditions (connection interrupted).
     * @return true if connection is available, false otherwise.
     */
    public boolean checkConnection() {
        // In a real system, this would check network connectivity.
        return true;
    }

    /**
     * Returns whether the connection is active.
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return checkConnection();
    }
}