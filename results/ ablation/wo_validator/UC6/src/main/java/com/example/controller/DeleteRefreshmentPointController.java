package com.example.controller;

import com.example.model.AgencyOperator;
import com.example.model.DeletionResult;
import com.example.service.AuthorizationService;
import com.example.repository.RefreshmentPointRepository;

/**
 * Controller responsible for handling refreshment point deletion.
 */
public class DeleteRefreshmentPointController {
    private RefreshmentPointRepository repository;
    private AuthorizationService authService;

    public DeleteRefreshmentPointController(RefreshmentPointRepository repository) {
        this.repository = repository;
        this.authService = new AuthorizationService();
    }

    /**
     * Executes the deletion process for a refreshment point.
     *
     * @param operator the agency operator performing the deletion.
     * @param restPointId the ID of the refreshment point to delete.
     * @return a DeletionResult indicating the outcome.
     */
    public DeletionResult execute(AgencyOperator operator, int restPointId) {
        // Step 1: Validate authorization
        if (!validateAuthorization(operator)) {
            return new DeletionResult(false, "Unauthorized deletion attempt");
        }

        // Step 2: Check server connection
        if (!repository.isConnected()) {
            return new DeletionResult(false, "Connection lost to ETOUR server");
        }

        // Step 3: Retrieve the refreshment point
        var refreshmentPoint = repository.findById(restPointId);
        if (refreshmentPoint == null) {
            return new DeletionResult(false, "Refreshment point not found");
        }

        // Step 4: Confirm deletion with operator
        if (!confirmDeletion(operator)) {
            // Operator cancelled
            return new DeletionResult(false, "Operation cancelled");
        }

        // Step 5: Perform deletion
        boolean deletionSuccess = performDeletion(restPointId);
        if (deletionSuccess) {
            return new DeletionResult(true, "Elimination successful");
        } else {
            return new DeletionResult(false, "Deletion failed due to server error");
        }
    }

    /**
     * Validates if the operator is authorized for deletion.
     *
     * @param operator the agency operator.
     * @return true if authorized.
     */
    private boolean validateAuthorization(AgencyOperator operator) {
        return authService.isAuthorizedForDeletion(operator);
    }

    /**
     * Simulates the confirmation dialog with the operator.
     *
     * @param operator the agency operator.
     * @return true if confirmed, false if cancelled.
     */
    private boolean confirmDeletion(AgencyOperator operator) {
        // Simulate displaying a confirmation dialog.
        // For simplicity, we assume operator.confirmDeletion() returns true for confirmation,
        // and false for cancellation (triggered by cancelOperation).
        // In the alternative flow, the operator may call cancelOperation().
        // Assumption: In this simulation, we directly call the operator's method.
        return operator.confirmDeletion();
    }

    /**
     * Performs the deletion through the repository.
     *
     * @param restPointId the ID of the refreshment point.
     * @return true if deletion succeeded.
     */
    private boolean performDeletion(int restPointId) {
        return repository.delete(restPointId);
    }
}