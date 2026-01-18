package com.example;

/**
 * Manages connections to the SMOS system.
 * Implements IConnectionHandler to satisfy requirement R9.
 */
public class SMOSConnectionManager implements IConnectionHandler {
    // Assumption: For this example, we'll simulate connection status.
    private boolean isConnected = true; // Default to connected

    @Override
    public boolean connect() {
        System.out.println("SMOSConnectionManager: Attempting to connect to SMOS...");
        // Simulate connection logic
        this.isConnected = true;
        System.out.println("SMOSConnectionManager: Connected to SMOS.");
        return true;
    }

    @Override
    public boolean disconnect() {
        System.out.println("SMOSConnectionManager: Attempting to disconnect from SMOS...");
        // Simulate disconnection logic
        this.isConnected = false;
        System.out.println("SMOSConnectionManager: Disconnected from SMOS.");
        return true;
    }

    @Override
    public boolean checkConnection() {
        // Comment: Simulating connection check. Can be toggled for testing failure scenarios.
        System.out.println("SMOSConnectionManager: Checking connection status. Currently: " + (isConnected ? "Connected" : "Disconnected"));
        return isConnected;
    }

    /**
     * Helper method to simulate connection failure for testing purposes.
     * Not part of the UML, but useful for demonstrating sequence diagram paths.
     * @param fail If true, the connection will report as failed.
     */
    public void simulateConnectionFailure(boolean fail) {
        this.isConnected = !fail;
    }
}