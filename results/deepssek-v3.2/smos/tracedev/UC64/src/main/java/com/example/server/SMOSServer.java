package com.example.server;

/**
 * Simulates the SMOS server connection.
 * Added to satisfy requirement: Exit Conditions - Connection to SMOS server interrupted.
 */
public class SMOSServer {
    private String serverUrl;
    private boolean isConnected;

    public SMOSServer(String serverUrl) {
        this.serverUrl = serverUrl;
        this.isConnected = false;
    }

    /**
     * Simulates connecting to the SMOS server.
     * Returns true if connection is successful, false otherwise.
     * For demonstration, we assume sometimes it fails.
     */
    public boolean connect() {
        // Simulate occasional connection failure.
        if (Math.random() < 0.2) {  // 20% chance of failure
            isConnected = false;
            return false;
        }
        isConnected = true;
        return true;
    }

    public void disconnect() {
        isConnected = false;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}