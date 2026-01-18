package com.example.justification.infrastructure;

/**
 * Interface for the Unit of Work pattern.
 * It manages transactions and ensures atomicity of business operations.
 * Corresponds to the IUnitOfWork interface in the Class Diagram.
 */
public interface IUnitOfWork {

    /**
     * Begins a new transaction. All operations performed within the scope
     * of this Unit of Work will be part of this transaction.
     */
    void beginTransaction();

    /**
     * Commits the current transaction, making all changes permanent.
     * This operation typically triggers a saveChanges() on the underlying DbContext.
     */
    void commit();

    /**
     * Rolls back the current transaction, discarding all changes made
     * since the transaction began.
     */
    void rollback();
}