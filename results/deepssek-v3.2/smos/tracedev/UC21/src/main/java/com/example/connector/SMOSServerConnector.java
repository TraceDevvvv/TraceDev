package com.example.connector;

/**
 * Connector to SMOS server.
 * Based on the UML class diagram.
 */
public class SMOSServerConnector {
    private boolean connectionActive;

    public SMOSServerConnector() {
        this.connectionActive = true;
    }

    public boolean checkConnection() {
        return connectionActive;
    }

    public void handleConnectionError() {
        // Attempt to recover connection
        System.out.println("Handling connection error...");
        connectionActive = false;
        // Simulate reconnection logic
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        connectionActive = true;
    }

    // Additional getter and setter
    public boolean isConnectionActive() { return connectionActive; }
    public void setConnectionActive(boolean connectionActive) { this.connectionActive = connectionActive; }
}