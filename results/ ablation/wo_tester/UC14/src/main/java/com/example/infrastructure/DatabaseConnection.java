package com.example.infrastructure;

/**
 * Represents a database connection.
 */
public class DatabaseConnection {
    private boolean isConnected;
    private String serverUrl;

    public DatabaseConnection(String serverUrl) {
        this.serverUrl = serverUrl;
        this.isConnected = false;
    }

    public boolean connect() {
        // Simulate connection attempt
        isConnected = serverUrl != null && !serverUrl.isEmpty();
        return isConnected;
    }

    public void disconnect() {
        isConnected = false;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public boolean checkConnection() {
        // In a real scenario, this would ping the server
        return isConnected;
    }
}