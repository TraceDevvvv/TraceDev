package com.example;

/**
 * Interface for managing transactions.
 * Provides methods to begin, commit, and rollback transactions.
 */
public interface TransactionManager {

    /**
     * Begins a new transaction.
     */
    void beginTransaction();

    /**
     * Commits the current transaction.
     */
    void commit();

    /**
     * Rolls back the current transaction.
     */
    void rollback();
}