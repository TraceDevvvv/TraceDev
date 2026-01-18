package com.example.infrastructure;

/**
 * Represents a connection to the SMOS server.
 * Satisfies exit conditions requirement.
 */
public class SMOSConnection {
    private boolean connected = false;

    public void connect() {
        // Simulate connection establishment
        System.out.println("Connecting to SMOS server...");
        connected = true;
        System.out.println("Connected to SMOS server.");
    }

    public boolean isConnected() {
        return connected;
    }
}