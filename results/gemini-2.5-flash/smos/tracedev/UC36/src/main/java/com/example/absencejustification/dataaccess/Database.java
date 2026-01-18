package com.example.absencejustification.dataaccess;

/**
 * Represents a simulated database system.
 * This class handles low-level data storage operations and connection management.
 */
public class Database {
    // A flag to simulate connection failures for testing purposes.
    private boolean simulateConnectionFailure = false;

    /**
     * Connects to the database.
     * @return true if connection is successful, false otherwise.
     */
    public boolean connect() {
        System.out.println("[Database] Attempting to connect...");
        if (simulateConnectionFailure) {
            System.out.println("[Database] Connection failed (simulated).");
            return false;
        }
        System.out.println("[Database] Connection successful.");
        return true;
    }

    /**
     * Inserts an entity into the database.
     * @param entity The object to insert.
     * @return true if insertion is successful, false otherwise.
     * @throws RuntimeException if the connection is not active or fails during insert.
     */
    public boolean insert(Object entity) {
        if (simulateConnectionFailure) {
            System.out.println("[Database] Insert failed: Connection not available (simulated).");
            throw new RuntimeException("Database connection lost during insert.");
        }
        System.out.println("[Database] Inserting entity: " + entity.getClass().getSimpleName() + " into database.");
        // Simulate actual database insertion logic here
        return true;
    }

    /**
     * Sets the flag to simulate connection failure.
     * @param simulateConnectionFailure true to simulate failure, false otherwise.
     */
    public void setSimulateConnectionFailure(boolean simulateConnectionFailure) {
        this.simulateConnectionFailure = simulateConnectionFailure;
    }
}