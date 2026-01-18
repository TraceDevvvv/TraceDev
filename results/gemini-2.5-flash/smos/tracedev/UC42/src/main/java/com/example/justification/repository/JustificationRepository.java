package com.example.justification.repository;

import com.example.justification.domain.Justification;
import com.example.justification.infrastructure.IDbContext;

import java.util.Objects;

/**
 * Implementation of the IJustificationRepository interface.
 * This class handles data access for Justification entities, interacting with the IDbContext.
 * Corresponds to the JustificationRepository class in the Class Diagram.
 */
public class JustificationRepository implements IJustificationRepository {
    private final IDbContext _dbContext; // Dependency on the database context

    /**
     * Constructs a JustificationRepository with a given database context.
     *
     * @param dbContext The IDbContext instance to use for data operations.
     */
    public JustificationRepository(IDbContext dbContext) {
        this._dbContext = Objects.requireNonNull(dbContext, "IDbContext cannot be null");
    }

    /**
     * {@inheritDoc}
     * Retrieves a Justification from the database context's DbSet.
     */
    @Override
    public Justification getById(String id) {
        System.out.println("IJustificationRepository: getById(" + id + ")");
        return _dbContext.getJustifications().get(id);
    }

    /**
     * {@inheritDoc}
     * Removes the specified Justification from the database context's DbSet.
     * This operation is typically part of a Unit of Work transaction.
     */
    @Override
    public void remove(Justification justification) {
        Objects.requireNonNull(justification, "Justification to remove cannot be null");
        System.out.println("IJustificationRepository: remove(" + justification.getId() + ")");
        _dbContext.getJustifications().remove(justification.getId());
        // In a real application, _dbContext.saveChanges() might be called here
        // if not managed by a UnitOfWork. With UnitOfWork, saveChanges is part of commit.
    }
}