package com.example.infrastructure.transaction;

/**
 * Simple transaction manager for data integrity.
 */
public class TransactionManager {
    public void beginTransaction() {
        // Simulate beginning a transaction
        System.out.println("Transaction started");
    }

    public void commitTransaction() {
        // Simulate committing a transaction
        System.out.println("Transaction committed");
    }

    public void rollbackTransaction() {
        // Simulate rolling back a transaction
        System.out.println("Transaction rolled back");
    }
}