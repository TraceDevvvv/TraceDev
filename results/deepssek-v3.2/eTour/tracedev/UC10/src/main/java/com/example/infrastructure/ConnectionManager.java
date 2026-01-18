package com.example.infrastructure;

/**
 * Manages the connection to the external ETOUR server.
 * Infrastructure/Data Layer class.
 */
public class ConnectionManager {
    private boolean isConnected;
    private String serverETOUR;

    public ConnectionManager(String serverETOUR) {
        this.serverETOUR = serverETOUR;
        this.isConnected = false;
    }

    /**
     * Ensures a connection to the ETOUR server is established.
     * @throws ConnectionException if the connection cannot be established.
     */
    public void ensureConnection() throws ConnectionException {
        if (!isConnected) {
            // Simulate connection attempt
            maintainConnection();
            if (!isConnected) {
                throw new ConnectionException("Failed to connect to server: " + serverETOUR);
            }
        }
    }

    /**
     * Maintains the connection to the ETOUR server.
     * In a real implementation, this would handle heartbeats/reconnections.
     */
    public void maintainConnection() {
        // Simulate connection maintenance
        // For this example, assume connection always succeeds.
        isConnected = true;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public String getServerETOUR() {
        return serverETOUR;
    }
}