
package com.etoour.utility;

/**
 * Manages connection to the ETOUR server.
 */
public class ConnectionManager {
    private String serverUrl;
    private boolean isConnected;

    public ConnectionManager(String serverUrl) {
        this.serverUrl = serverUrl;
        this.isConnected = true; // assume initially connected
    }

    /**
     * Check connection status.
     * Simulate random disconnections.
     */
    public boolean checkConnection() {
        // Simulate 20% chance of disconnection
        if (Math.random() < 0.2) {
            isConnected = false;
        } else {
            isConnected = true;
        }
        return isConnected;
    }
}
