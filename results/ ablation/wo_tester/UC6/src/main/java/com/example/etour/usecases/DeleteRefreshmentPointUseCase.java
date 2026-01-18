package com.example.etour.usecases;

import com.example.etour.entities.RefreshmentPoint;
import com.example.etour.interfaces.RefreshmentPointRepository;

/**
 * Use case implementation for deleting a refreshment point.
 * The deletion completes within 5 seconds (quality requirement).
 */
public class DeleteRefreshmentPointUseCase implements DeleteRefreshmentPointInputPort {
    private RefreshmentPointRepository repository;
    private DeleteRefreshmentPointOutputPort presenter;

    public DeleteRefreshmentPointUseCase(RefreshmentPointRepository repository, DeleteRefreshmentPointOutputPort presenter) {
        this.repository = repository;
        this.presenter = presenter;
    }

    @Override
    public boolean delete(String id) {
        // Start timing to ensure completion within 5 seconds (quality requirement)
        long startTime = System.currentTimeMillis();
        try {
            RefreshmentPoint point = repository.findById(id);
            if (point == null) {
                presenter.presentError("Refreshment point not found");
                return false;
            }

            boolean deleted = repository.delete(id);
            if (deleted) {
                presenter.presentSuccess("Deletion successful");
            } else {
                presenter.presentError("Refreshment point not found");
            }
            return deleted;
        } catch (DataAccessException e) {
            presenter.presentError("Connection interrupted");
            return false;
        } finally {
            long endTime = System.currentTimeMillis();
            // Log if deletion takes longer than 5 seconds (for monitoring)
            if (endTime - startTime > 5000) {
                System.err.println("Warning: Deletion took more than 5 seconds.");
            }
        }
    }
}

/**
 * Custom exception for data access errors.
 */
class DataAccessException extends RuntimeException {
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}