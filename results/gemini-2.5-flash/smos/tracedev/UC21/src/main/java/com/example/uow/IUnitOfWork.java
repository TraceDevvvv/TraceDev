package com.example.uow;

/**
 * Interface for the Unit of Work pattern, managing transaction boundaries.
 * This ensures that multiple operations are treated as a single, atomic transaction.
 */
public interface IUnitOfWork {
    /**
     * Begins a new transaction. All subsequent operations until commit or rollback
     * are considered part of this transaction.
     * // Added to satisfy requirement: Quality Requirement: 'The system must reliably update the teachings associated with an address.'
     */
    void begin();

    /**
     * Commits the current transaction, making all changes permanent.
     */
    void commit();

    /**
     * Rolls back the current transaction, discarding all changes made since `begin()`.
     */
    void rollback();
}