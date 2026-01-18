package com.example.command;

import com.example.domain.RefreshmentPoint;
import com.example.repository.IRefreshmentPointRepository;
import com.example.repository.RefreshmentPointRepositoryImpl;
import com.example.transaction.TransactionManager;

/**
 * Command pattern implementation for deleting a refreshment point.
 * Supports transactional execution and rollback.
 */
public class DeleteRefreshmentPointCommand {
    private int refreshmentPointId;
    private IRefreshmentPointRepository repository;
    private TransactionManager transactionManager;
    private RefreshmentPoint deletedPoint; // For undo support

    public DeleteRefreshmentPointCommand(int refreshmentPointId) {
        this.refreshmentPointId = refreshmentPointId;
        // In a real application, these would be injected
        this.repository = new RefreshmentPointRepositoryImpl();
        this.transactionManager = new TransactionManager();
    }

    /**
     * Executes the delete operation within a transaction.
     * @return true if delete was successful, false otherwise
     */
    public boolean execute() {
        try {
            transactionManager.beginTransaction();
            RefreshmentPoint point = repository.findById(refreshmentPointId);
            if (point == null) {
                System.err.println("Refreshment point not found with ID: " + refreshmentPointId);
                transactionManager.rollback();
                return false;
            }
            if (!validate()) {
                transactionManager.rollback();
                return false;
            }
            deletedPoint = point; // Store for possible undo
            boolean deleted = repository.delete(refreshmentPointId);
            if (deleted) {
                transactionManager.commit();
                System.out.println("Command executed: Deleted refreshment point ID " + refreshmentPointId);
                return true;
            } else {
                transactionManager.rollback();
                return false;
            }
        } catch (Exception e) {
            rollback();
            System.err.println("Error during command execution: " + e.getMessage());
            return false;
        }
    }

    /**
     * Undo the delete operation by re-saving the deleted point.
     * Requires that the deletedPoint was stored during execute.
     */
    public void undo() {
        if (deletedPoint != null) {
            repository.save(deletedPoint);
            System.out.println("Command undone: Restored refreshment point ID " + deletedPoint.getId());
        } else {
            System.err.println("Cannot undo: no point stored.");
        }
    }

    /**
     * Validates the command before execution.
     * @return true if valid, false otherwise
     */
    public boolean validate() {
        // Example validation: ensure ID is positive
        return refreshmentPointId > 0;
    }

    /**
     * Rollback mechanism in case of failure.
     */
    public void rollback() {
        System.out.println("Rolling back command for ID: " + refreshmentPointId);
        if (transactionManager.isActive()) {
            transactionManager.rollback();
        }
    }
}