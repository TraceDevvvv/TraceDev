package com.example.service;

/**
 * Represents a connection to the ETOUR system.
 * Added to satisfy requirement 14.
 */
public class ETOURConnection {
    private String connectionStatus;

    public ETOURConnection() {
        this.connectionStatus = "DISCONNECTED";
    }

    public String getConnectionStatus() {
        return connectionStatus;
    }

    /**
     * Checks the connection status.
     * Linked to sequence messages m8, m28.
     */
    public boolean checkConnection() {
        // Simulate connection check.
        connectionStatus = Math.random() > 0.2 ? "CONNECTED" : "DISCONNECTED";
        System.out.println("ETOURConnection: Connection status " + connectionStatus);
        return "CONNECTED".equals(connectionStatus);
    }

    /**
     * Handles a disconnection.
     * Linked to sequence messages m11, m31.
     */
    public void handleDisconnection() {
        System.out.println("ETOURConnection: Handling disconnection.");
        connectionStatus = "DISCONNECTED";
    }

    /**
     * Attempts to reconnect.
     */
    public boolean reconnect() {
        System.out.println("ETOURConnection: Attempting to reconnect.");
        connectionStatus = "CONNECTED";
        return true;
    }

    // Method to return connection status as string (for sequence messages).
    public String getConnectionStatusMessage() {
        return connectionStatus;
    }
}