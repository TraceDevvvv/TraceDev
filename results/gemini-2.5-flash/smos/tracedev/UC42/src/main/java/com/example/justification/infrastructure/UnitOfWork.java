package com.example.justification.infrastructure;

import java.util.Objects;

/**
 * Implementation of the Unit of Work pattern.
 * Coordinates the work of multiple repositories and a single database context
 * to ensure that all operations are atomic.
 * Corresponds to the UnitOfWork class in the Class Diagram.
 */
public class UnitOfWork implements IUnitOfWork {
    private final IDbContext _dbContext; // Dependency on the database context

    /**
     * Constructs a UnitOfWork with a given database context.
     *
     * @param dbContext The IDbContext instance to manage transactions.
     */
    public UnitOfWork(IDbContext dbContext) {
        this._dbContext = Objects.requireNonNull(dbContext, "IDbContext cannot be null");
    }

    /**
     * {@inheritDoc}
     * Delegates to the underlying database context to begin a transaction.
     */
    @Override
    public void beginTransaction() {
        System.out.println("UnitOfWork: beginTransaction()");
        _dbContext.beginTransaction();
    }

    /**
     * {@inheritDoc}
     * Delegates to the underlying database context to commit the transaction,
     * typically after saving all changes.
     */
    @Override
    public void commit() {
        System.out.println("UnitOfWork: commit()");
        _dbContext.saveChanges(); // Ensure all pending changes are written before committing
        _dbContext.commitTransaction();
    }

    /**
     * {@inheritDoc}
     * Delegates to the underlying database context to roll back the transaction.
     */
    @Override
    public void rollback() {
        System.out.println("UnitOfWork: rollback()");
        _dbContext.rollbackTransaction();
    }
}