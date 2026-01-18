package com.example;

/**
 * Simulates a connection to an ETOUR server.
 */
public class ETOURServerConnection {
    private boolean isConnected = false;

    public void connect() {
        // Simulate connection attempt
        isConnected = true;
        System.out.println("ETOURServerConnection: Connected.");
    }

    public void disconnect() {
        isConnected = false;
        System.out.println("ETOURServerConnection: Disconnected.");
    }

    public boolean isConnected() {
        return isConnected;
    }
}