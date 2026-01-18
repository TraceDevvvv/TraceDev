package com.etour.util;

/**
 * Manages connection to the server ETOUR.
 * Simulates connection status.
 */
public class ConnectionManager {
    private String serverUrl;
    private boolean connected;

    public ConnectionManager(String serverUrl) {
        this.serverUrl = serverUrl;
        this.connected = true; // Initially connected
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    /**
     * Added to satisfy requirement: Exit Conditions: The connection to the server ETOUR is interrupted.
     */
    public boolean checkConnection() {
        return isConnected();
    }
}