package com.example.infrastructure.database;

/**
 * Simulates a database connection with transaction support.
 */
public class DatabaseConnection {
    private String url;
    private boolean isConnected = false;

    public void connect() {
        isConnected = true;
        System.out.println("Connected to database.");
    }

    public void disconnect() {
        isConnected = false;
        System.out.println("Disconnected from database.");
    }

    /**
     * Executes an operation within a transaction.
     * Simulates transaction begin, commit, and rollback on exception.
     *
     * @param operation the operation to execute
     * @throws RuntimeException if connection lost or operation fails
     */
    public void executeTransaction(Runnable operation) {
        if (!isConnected) {
            throw new RuntimeException("Database connection lost.");
        }
        beginTransaction();
        try {
            operation.run();
            commitTransaction();
        } catch (Exception e) {
            System.out.println("Rollback transaction due to: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Sequence diagram message: begin transaction.
     */
    public void beginTransaction() {
        System.out.println("begin transaction");
    }

    /**
     * Sequence diagram message: commit transaction.
     */
    public void commitTransaction() {
        System.out.println("commit transaction");
    }

    // For testing connection loss scenario.
    public void simulateConnectionLoss() {
        isConnected = false;
    }

    /**
     * Sequence diagram message: connection lost (lost message m24).
     * This method simulates the lost message by setting connection state.
     */
    public void connectionLost() {
        isConnected = false;
        System.out.println("connection lost");
    }
}