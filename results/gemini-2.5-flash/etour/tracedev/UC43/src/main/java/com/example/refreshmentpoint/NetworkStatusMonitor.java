package com.example.refreshmentpoint;

/**
 * Monitors the network status, specifically connection to the ETOUR server.
 * Used for handling requirement XC3.
 */
public class NetworkStatusMonitor {
    private boolean isConnected; // Simulates the connection status

    public NetworkStatusMonitor() {
        // Assume connected by default
        this.isConnected = true;
    }

    /**
     * Checks if the system is currently connected to the ETOUR server.
     *
     * @return true if connected, false otherwise.
     */
    public boolean isETOURConnected() {
        return isConnected;
    }

    /**
     * Simulates notifying about connection loss.
     * In a real system, this might log, trigger alerts, etc.
     */
    public void notifyConnectionLoss() {
        System.err.println("NetworkStatusMonitor: !!! ETOUR connection loss detected !!!");
    }

    /**
     * Helper method to simulate changing the connection status for testing.
     *
     * @param connected The new connection status.
     */
    public void setConnected(boolean connected) {
        this.isConnected = connected;
        System.out.println("NetworkStatusMonitor: ETOUR connection status set to: " + (connected ? "CONNECTED" : "DISCONNECTED"));
    }
}