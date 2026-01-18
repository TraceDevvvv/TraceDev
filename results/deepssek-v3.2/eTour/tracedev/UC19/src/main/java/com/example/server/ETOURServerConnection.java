package com.example.server;

/**
 * Represents a connection to the ETOUR server.
 * Automatically interrupted after deletion (post-condition).
 */
public class ETOURServerConnection {
    private boolean isConnected = false;

    public boolean connect() {
        // Simulate connection attempt
        isConnected = true;
        return true;
    }

    public void disconnect() {
        isConnected = false;
    }

    public boolean isConnected() {
        return isConnected;
    }
}