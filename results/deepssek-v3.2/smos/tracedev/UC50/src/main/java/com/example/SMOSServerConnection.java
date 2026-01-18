package com.example;

/**
 * External server connection.
 * Simulates connection status for interruption scenario (REQ-EXIT-001).
 */
public class SMOSServerConnection {
    private boolean connected = true;

    public boolean isConnected() {
        return connected;
    }

    public void disconnect() {
        connected = false;
        System.out.println("SMOSServerConnection disconnected.");
    }

    public void connect() {
        connected = true;
        System.out.println("SMOSServerConnection connected.");
    }
}