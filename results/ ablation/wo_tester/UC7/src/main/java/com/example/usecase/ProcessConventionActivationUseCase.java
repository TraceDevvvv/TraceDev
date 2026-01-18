package com.example.usecase;

import com.example.repository.ConventionRepository;
import com.example.service.ActivationHandler;
import com.example.model.*;
import com.example.dto.ActivationResultDTO;
import com.example.ui.FormDisplay;
import java.util.Date;

/**
 * Use case for processing convention activation.
 */
public class ProcessConventionActivationUseCase {
    private ConventionRepository conventionRepository;
    private ActivationHandler activationHandler;
    private FormDisplay formDisplay;

    public ProcessConventionActivationUseCase(ConventionRepository conventionRepository,
                                              ActivationHandler activationHandler,
                                              FormDisplay formDisplay) {
        this.conventionRepository = conventionRepository;
        this.activationHandler = activationHandler;
        this.formDisplay = formDisplay;
    }

    /**
     * Executes the activation process for a given convention ID.
     */
    public ActivationResultDTO execute(String conventionId) {
        Convention convention = loadConventionData(conventionId);
        if (convention == null) {
            return new ActivationResultDTO(false, "Convention not found", conventionId, null, false);
        }

        // Precondition checks
        if (!convention.validatePreconditions()) {
            return new ActivationResultDTO(false, "Preconditions not met", conventionId, null, false);
        }

        DataRequest dataRequest = convention.getDataRequest();
        // Load data (as per sequence diagram)
        dataRequest.loadData();

        // Display form (as per sequence diagram)
        displayConventionForm(convention);

        // At this point, the controller will wait for user confirmation.
        // The actual activation will be triggered by confirmActivation.
        // For now, return a result indicating that the form is displayed.
        return new ActivationResultDTO(true, "Convention form displayed", conventionId, null, false);
    }

    public Convention loadConventionData(String conventionId) {
        return conventionRepository.findById(conventionId);
    }

    /**
     * Performs activation after confirmation.
     */
    public ActivationResultDTO performActivation(Convention convention) {
        ActivationResult result = activationHandler.handleActivation(convention);
        conventionRepository.save(convention);

        return new ActivationResultDTO(
            result.isSuccess(),
            result.getErrorMessage() != null ? result.getErrorMessage() : "Activation successful",
            convention.getId(),
            new Date(),
            result.isNotificationSent()
        );
    }

    public void displayConventionForm(Convention convention) {
        formDisplay.displayConventionForm(convention);
    }

    public ActivationResultDTO cancelActivation(String conventionId) {
        // In a real scenario, we might revert any temporary changes.
        return new ActivationResultDTO(false, "Operation canceled", conventionId, null, false);
    }
}