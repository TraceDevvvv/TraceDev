package com.example.touristapp.infrastructure;

/**
 * A dummy class representing a database connection.
 * In a real application, this would manage actual database interactions.\n */
public class DatabaseConnection {
    private boolean connected;

    public DatabaseConnection() {
        // Simulate a connection attempt
        this.connected = true; // Assume connected for this example
        System.out.println("[DatabaseConnection] Initialized. Connection status: " + connected);
    }

    public boolean isConnected() {
        return connected;
    }

    /**
     * Simulates a connection interruption.\n     */
    public void simulateConnectionInterruption() {
        this.connected = false;
        System.out.println("[DatabaseConnection] Connection interrupted!");
    }

    /**
     * Simulates restoring a connection.\n     */
    public void restoreConnection() {
        this.connected = true;
        System.out.println("[DatabaseConnection] Connection restored.");
    }
}