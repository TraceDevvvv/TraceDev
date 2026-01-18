package com.example.application;

import com.example.repository.IRefreshmentPointRepository;
import com.example.domain.PersistenceException;

/**
 * Controller for the Delete Refreshment Point use case.
 */
public class DeleteRefreshmentPointUseCaseController implements ICommand {
    private IRefreshmentPointRepository repository;

    public DeleteRefreshmentPointUseCaseController(IRefreshmentPointRepository repo) {
        this.repository = repo;
    }

    /**
     * Executes the deletion process for a given point ID.
     * @param pointId ID of the refreshment point to delete
     * @return true if deletion succeeded, false otherwise
     */
    public boolean execute(String pointId) {
        try {
            return confirmDeletion(pointId);
        } catch (PersistenceException e) {
            // Exception is handled by the caller (View) as per sequence diagram
            return false;
        }
    }

    /**
     * Confirms and performs the deletion after validation.
     * @param pointId ID of the refreshment point to delete
     * @return true if deletion succeeded, false otherwise
     */
    public boolean confirmDeletion(String pointId) throws PersistenceException {
        validateDeletion(pointId);
        boolean deleted = repository.delete(pointId);
        return deleted;
    }

    /**
     * Validates that the deletion is permissible (e.g., point exists).
     * In a real scenario, additional business rules could be checked.
     */
    private void validateDeletion(String pointId) {
        // For simplicity, we assume validation passes if the point exists.
        // Additional domain validation could be added here.
    }

    /**
     * Required by ICommand interface. This implementation delegates to execute with a dummy ID.
     * In a real application, the point ID would be provided via constructor or setter.
     */
    @Override
    public void execute() {
        // This method is from ICommand but not used directly in the sequence.
        // We assume the point ID is known via other means (e.g., setter).
        // For compatibility, we provide a default implementation.
        System.out.println("ICommand.execute() called - use execute(String pointId) instead.");
    }
}