package com.example.util;

import com.example.exception.ConnectionInterruptedException;

/**
 * Manages connection status and handles interruptions.
 * Added to satisfy Exit Conditions requirement.
 */
public class ConnectionManager {
    private boolean isConnected = true;

    public boolean checkConnection() {
        // Simulate connection check
        return isConnected;
    }

    public boolean reconnect() {
        // Simulate reconnection attempt
        isConnected = true;
        return true;
    }

    public boolean getConnectionStatus() {
        return isConnected;
    }

    /**
     * Simulates a connection loss event triggered by ETOUR server.
     */
    public void connectionLost() {
        isConnected = false;
        // Throw an exception as per sequence diagram
        throw new ConnectionInterruptedException("CONN_LOST", "Connection lost with ETOUR server", new java.util.Date());
    }
}