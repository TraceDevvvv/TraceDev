package com.system.network;

/**
 * Simulates a network service that checks server connection.
 * Exit Condition: Server connection interruption handling.
 */
public class ETOURService {
    private boolean isConnected = true; // Simulate initial connection

    public boolean checkConnection() {
        // Simulate connection check. In real system, would ping server.
        return isConnected;
    }

    public boolean reconnect() {
        // Simulate reconnection attempt.
        isConnected = true;
        return true;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }
}