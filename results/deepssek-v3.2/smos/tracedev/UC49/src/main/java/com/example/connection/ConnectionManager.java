package com.example.connection;

/**
 * Manages connection to external systems (e.g., SMOS server).
 * Handles exit conditions for server interruptions.
 */
public class ConnectionManager {
    private boolean connectionActive = true;

    public void interruptConnection() {
        // Satisfies exit condition (SMOS server interruption)
        this.connectionActive = false;
        System.out.println("Connection interrupted: SMOS server connection terminated.");
    }

    public boolean isConnectionActive() {
        return connectionActive;
    }

    // Added to reset connection for testing/demo purposes
    public void resetConnection() {
        this.connectionActive = true;
    }

    // New method to close connection as per diagram (sequence termination)
    public void closeConnection() {
        this.connectionActive = false;
        System.out.println("Connection closed by user.");
    }
}