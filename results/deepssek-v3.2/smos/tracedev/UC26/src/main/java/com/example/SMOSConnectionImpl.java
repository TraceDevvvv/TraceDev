package com.example;

/**
 * Implementation of SMOSConnection.
 * Simulates connection to a server.
 */
public class SMOSConnectionImpl implements SMOSConnection {
    private String serverUrl;
    private boolean connected;

    public SMOSConnectionImpl(String serverUrl) {
        this.serverUrl = serverUrl;
        this.connected = true; // Initially connected
    }

    @Override
    public boolean isConnected() {
        return connected;
    }

    @Override
    public void checkConnection() {
        // Simulate connection check.
        // In a real implementation, this would ping the server.
        if (!connected) {
            throw new RuntimeException("Error: Not connected to SMOS server at " + serverUrl);
        }
    }

    // For testing: simulate connection loss.
    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}