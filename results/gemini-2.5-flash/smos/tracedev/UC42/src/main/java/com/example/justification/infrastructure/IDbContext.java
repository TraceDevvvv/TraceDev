package com.example.justification.infrastructure;

import com.example.justification.domain.Justification;

/**
 * Interface representing the Database Context.
 * It provides access to entity sets and manages transaction operations.
 * Connects to SMOS server for persistence (simulated in this example).
 * Corresponds to the IDbContext interface in the Class Diagram.
 */
public interface IDbContext {

    /**
     * Provides access to the DbSet of Justification entities.
     *
     * @return A DbSet containing Justification entities.
     */
    DbSet<Justification> getJustifications();

    /**
     * Saves all pending changes made in the context to the underlying data store.
     * In a real system, this would persist entity changes.
     */
    void saveChanges();

    /**
     * Begins a new transaction.
     * All subsequent database operations until commitTransaction() or rollbackTransaction()
     * will be part of this transaction.
     */
    void beginTransaction();

    /**
     * Commits the current transaction, making all changes permanent.
     */
    void commitTransaction();

    /**
     * Rolls back the current transaction, undoing all changes made since beginTransaction().
     */
    void rollbackTransaction();

    /**
     * Sets a flag to simulate a connection interruption.
     *
     * @param interrupted true to simulate interruption, false otherwise.
     */
    void setConnectionInterrupted(boolean interrupted);
}