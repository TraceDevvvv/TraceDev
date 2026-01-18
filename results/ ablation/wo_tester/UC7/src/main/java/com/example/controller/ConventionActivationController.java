package com.example.controller;

import com.example.usecase.ProcessConventionActivationUseCase;
import com.example.dto.ActivationResultDTO;
import com.example.model.Convention;

/**
 * Controller for handling activation requests from the Agency Operator.
 */
public class ConventionActivationController {
    private ProcessConventionActivationUseCase useCase;
    private boolean confirmationPending = false;
    private Convention pendingConvention;

    public ConventionActivationController(ProcessConventionActivationUseCase useCase) {
        this.useCase = useCase;
    }

    public ActivationResultDTO processActivation(String conventionId) {
        // Execute the use case to load data and display form
        ActivationResultDTO result = useCase.execute(conventionId);
        if (result.success) {
            // If form displayed, set confirmation pending
            confirmationPending = true;
            // In a real app, we would store the convention for later confirmation.
            // For simplicity, we assume we can load it again.
            pendingConvention = useCase.loadConventionData(conventionId);
        }
        return result;
    }

    public void confirmActivation(String conventionId) {
        if (!confirmationPending) {
            System.out.println("No pending activation to confirm.");
            return;
        }
        if (pendingConvention == null || !pendingConvention.getId().equals(conventionId)) {
            System.out.println("Convention mismatch.");
            return;
        }
        ActivationResultDTO result = useCase.performActivation(pendingConvention);
        confirmationPending = false;
        pendingConvention = null;
        // In a real application, we would send the result back to the caller.
        System.out.println("Activation confirmed: " + result.message);
    }

    public void cancelActivation() {
        if (!confirmationPending) {
            System.out.println("No pending activation to cancel.");
            return;
        }
        if (pendingConvention != null) {
            useCase.cancelActivation(pendingConvention.getId());
        }
        confirmationPending = false;
        pendingConvention = null;
        System.out.println("Activation canceled.");
    }
}