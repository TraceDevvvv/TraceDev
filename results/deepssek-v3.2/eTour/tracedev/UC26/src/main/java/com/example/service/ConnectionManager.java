package com.example.service;

/**
 * Manages connection state.
 */
public class ConnectionManager {
    private boolean connectionAlive = true; // Simulated connection state

    /**
     * Checks the connection.
     * @return true if connection is OK
     */
    public boolean checkConnection() {
        return connectionAlive;
    }

    /**
     * Checks if connection is alive.
     * @return true if connection is alive
     */
    public boolean isConnectionAlive() {
        return connectionAlive;
    }

    /**
     * Simulates connection interruption for testing.
     */
    public void simulateInterruption() {
        connectionAlive = false;
    }

    /**
     * Restores connection for testing.
     */
    public void restoreConnection() {
        connectionAlive = true;
    }
}