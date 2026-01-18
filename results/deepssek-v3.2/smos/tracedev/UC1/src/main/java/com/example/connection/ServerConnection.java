package com.example.connection;

/**
 * Represents connection to the SMOS server.
 * Handles connection interruptions (Exit Condition).
 */
public class ServerConnection {
    private boolean isConnected;
    private String serverUrl;

    public ServerConnection(String serverUrl) {
        this.serverUrl = serverUrl;
        this.isConnected = false;
    }

    /**
     * Checks if the server connection is alive.
     * For demonstration, returns a simulated status.
     */
    public boolean checkConnection() {
        // In a real system, this would ping the server.
        // For simulation, we alternate between true and false.
        isConnected = !isConnected; // Toggle for simulation
        return isConnected;
    }

    /**
     * Attempts to establish a connection.
     */
    public boolean establishConnection() {
        isConnected = true; // Simulate successful connection
        return true;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public String getServerUrl() {
        return serverUrl;
    }
}