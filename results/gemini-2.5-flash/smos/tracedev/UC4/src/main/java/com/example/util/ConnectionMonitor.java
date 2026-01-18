package com.example.util;

/**
 * Monitors the connection status to external systems (e.g., SMOS server, database).
 * Added to satisfy requirement Exit Conditions: User cancels connection interruption to SMOS server.
 */
public class ConnectionMonitor {
    private boolean connected = true; // Simulates initial connected state

    /**
     * Checks if the system is currently connected.
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return connected;
    }

    /**
     * Notifies the monitor that a connection has been lost.
     */
    public void notifyConnectionLost() {
        this.connected = false;
        System.out.println("[ConnectionMonitor] Connection lost to external system!");
    }

    /**
     * Notifies the monitor that a connection has been restored.
     */
    public void notifyConnectionRestored() {
        this.connected = true;
        System.out.println("[ConnectionMonitor] Connection restored to external system.");
    }

    /**
     * Simulates a connection loss for testing purposes.
     */
    public void simulateConnectionLost() {
        notifyConnectionLost();
    }

    /**
     * Simulates a connection restore for testing purposes.
     */
    public void simulateConnectionRestored() {
        notifyConnectionRestored();
    }
}