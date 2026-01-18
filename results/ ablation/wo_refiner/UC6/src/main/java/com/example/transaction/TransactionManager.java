package com.example.transaction;

/**
 * trace(R13)
 * Quality requirement: Transactional integrity
 * Manages transaction boundaries for database operations.
 */
public class TransactionManager {
    private Transaction currentTransaction;
    private boolean active = false;

    public Transaction beginTransaction() {
        System.out.println("Transaction started.");
        currentTransaction = new Transaction();
        active = true;
        return currentTransaction;
    }

    public boolean commit() {
        if (active) {
            System.out.println("Transaction committed.");
            active = false;
            return true;
        }
        return false;
    }

    public boolean rollback() {
        if (active) {
            System.out.println("Transaction rolled back.");
            active = false;
            return true;
        }
        return false;
    }

    public boolean isActive() {
        return active;
    }

    // Inner class representing a transaction
    public static class Transaction {
        // Transaction details could be added here
    }
}