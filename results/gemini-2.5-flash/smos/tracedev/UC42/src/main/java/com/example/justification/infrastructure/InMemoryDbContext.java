package com.example.justification.infrastructure;

import com.example.justification.domain.Justification;

/**
 * In-memory implementation of the IDbContext interface.
 * This class simulates a database connection and operations for demonstration purposes.
 * It uses a simple {@link DbSet} to store entities in memory.
 */
public class InMemoryDbContext implements IDbContext {

    // Represents the collection of Justification entities in the "database".
    private final DbSet<Justification> justifications;
    // Flag to simulate an active transaction.
    private boolean inTransaction;
    // Flag to simulate a database connection interruption.
    private boolean connectionInterrupted;

    public InMemoryDbContext() {
        // Initialize DbSet for Justifications, using their ID as the key.
        this.justifications = new DbSet<>(Justification::getId);
        this.inTransaction = false;
        this.connectionInterrupted = false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public DbSet<Justification> getJustifications() {
        return justifications;
    }

    /**
     * {@inheritDoc}
     * In this in-memory implementation, changes to DbSet are immediately reflected.
     * This method could be used to trigger consistency checks or events in a real system.
     */
    @Override
    public void saveChanges() {
        if (connectionInterrupted) {
            throw new RuntimeException("PersistenceException: Connection to SMOS server interrupted during saveChanges.");
        }
        System.out.println("InMemoryDbContext: saveChanges() called. (Changes are immediate in-memory)");
        // In a real DB context, this would flush changes from a change tracker to the database.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void beginTransaction() {
        if (connectionInterrupted) {
            throw new RuntimeException("PersistenceException: Connection to SMOS server interrupted during beginTransaction.");
        }
        if (inTransaction) {
            throw new IllegalStateException("A transaction is already active.");
        }
        inTransaction = true;
        System.out.println("InMemoryDbContext: Transaction started.");
        // In a real DB context, this would begin a database transaction.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void commitTransaction() {
        if (connectionInterrupted) {
            throw new RuntimeException("PersistenceException: Connection to SMOS server interrupted during commitTransaction.");
        }
        if (!inTransaction) {
            throw new IllegalStateException("No active transaction to commit.");
        }
        inTransaction = false;
        System.out.println("InMemoryDbContext: Transaction committed.");
        // In a real DB context, this would commit the database transaction.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void rollbackTransaction() {
        if (connectionInterrupted) {
            // Even during rollback, connection might be interrupted.
            // For simplicity, we just log and exit the transaction state.
            System.err.println("InMemoryDbContext: Warning: Connection to SMOS server interrupted during rollbackTransaction. State might be inconsistent.");
        }
        if (!inTransaction) {
            // An attempt to rollback without an active transaction might indicate an issue,
            // but for a simple simulation, we might just log it or ignore.
            System.err.println("InMemoryDbContext: No active transaction to rollback, but rollbackTransaction() was called.");
            return;
        }
        inTransaction = false;
        System.out.println("InMemoryDbContext: Transaction rolled back. (In-memory changes might not be fully reverted without snapshotting)");
        // In a real DB context, this would rollback the database transaction.
        // For this in-memory mock, changes are typically already applied to the DbSet before commit.
        // A full rollback would require snapshotting the DbSet state before the transaction.
        // For the purpose of this exercise, the rollback primarily signals failure and prevents commit.
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setConnectionInterrupted(boolean interrupted) {
        this.connectionInterrupted = interrupted;
        System.out.println("InMemoryDbContext: Connection interrupted simulation set to: " + interrupted);
    }
}