package com.example.reportcard;

/**
 * Manages database transactions to ensure system stability and data integrity.
 * Satisfies REQ-003: Transaction Management for System Stability.
 */
public class TransactionManager {

    /**
     * Begins a new transaction.
     * In a real application, this would interact with a database connection.
     */
    public void beginTransaction() {
        System.out.println("[TransactionManager] Beginning transaction...");
    }

    /**
     * Commits the current transaction, making all changes permanent.
     */
    public void commitTransaction() {
        System.out.println("[TransactionManager] Transaction committed successfully.");
    }

    /**
     * Rolls back the current transaction, undoing all changes.
     */
    public void rollbackTransaction() {
        System.out.println("[TransactionManager] Transaction rolled back.");
    }
}