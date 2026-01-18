package com.example.connection;

/**
 * Manages connection to the persistence layer.
 * Added to satisfy requirement REQ-014.
 */
public class ConnectionManager {
    private String serverUrl;
    
    public ConnectionManager(String serverUrl) {
        this.serverUrl = serverUrl;
    }
    
    public boolean isConnected() {
        // Simulate connection status.
        return testConnection();
    }
    
    public boolean testConnection() {
        // For simulation, assume connection is always available.
        // In real scenario, would check network or database connectivity.
        return true;
    }
    
    public String getServerUrl() {
        return serverUrl;
    }
    
    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }
}