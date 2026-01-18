package com.example.controller;

import com.example.dto.CulturalObjectDTO;
import com.example.exception.ConnectionException;
import com.example.result.UpdateResult;
import com.example.service.AuthenticationService;
import com.example.service.CulturalObjectService;
import com.example.view.CulturalObjectEditView;
import java.util.List;

/**
 * Controller for the Change Cultural Object Data use case.
 */
public class ChangeCulturalObjectController {
    private CulturalObjectEditView view;
    private CulturalObjectService service;
    private AuthenticationService authService;
    private CulturalObjectDTO currentDTO;
    private boolean submissionInProgress;

    public ChangeCulturalObjectController(CulturalObjectEditView view, CulturalObjectService service, AuthenticationService authService) {
        this.view = view;
        this.service = service;
        this.authService = authService;
        this.submissionInProgress = false;
    }

    public void handleObjectSelection(int objectId) {
        // Check user is logged
        if (!checkUserLogged()) {
            view.showErrorMessage("User not logged in");
            return;
        }
        activateChangeFunction(objectId);
    }

    public void activateChangeFunction(int objectId) {
        try {
            currentDTO = service.getCulturalObjectById(objectId);
            view.displayFormForEditing(currentDTO);
        } catch (Exception e) {
            view.showErrorMessage("Error loading object: " + e.getMessage());
        }
    }

    public void handleFormSubmission(CulturalObjectDTO updatedData) {
        if (!validateSubmissionState()) {
            return;
        }
        submissionInProgress = true;
        view.disableFormControls();
        try {
            UpdateResult result = service.updateCulturalObject(updatedData);
            if (result.getNeedsConfirmation()) {
                view.showConfirmationDialog();
            } else {
                // No confirmation needed, proceed directly
                handleConfirmation();
            }
        } catch (ConnectionException e) {
            view.showErrorMessage("Connection failed: " + e.getMessage());
            view.enableFormControls();
            submissionInProgress = false;
        } catch (Exception e) {
            view.showErrorMessage("Update failed: " + e.getMessage());
            view.enableFormControls();
            submissionInProgress = false;
        }
    }

    public void handleConfirmation() {
        try {
            // Re-attempt update after confirmation
            UpdateResult result = service.updateCulturalObject(currentDTO);
            if (result.isSuccess()) {
                view.displayFormForEditing(currentDTO); // Refresh with updated data
                // In real implementation, show success message
                view.displaySuccessMessage("Update successful");
                view.showsSuccessNotification("Cultural object updated successfully");
            } else {
                view.showErrorMessage(result.getMessage());
                view.enableFormControls();
            }
        } catch (ConnectionException e) {
            view.showErrorMessage("Connection failed: " + e.getMessage());
            view.enableFormControls();
        }
        submissionInProgress = false;
    }

    public void handleCancel() {
        view.disableFormControls();
        submissionInProgress = false;
        // In real implementation, navigate back or clear form
    }

    public void activateErroredUseCase(List<String> errors) {
        // This would delegate to a dedicated error controller
        // For simplicity, we just show errors
        StringBuilder errorMsg = new StringBuilder("Errors occurred:\n");
        for (String err : errors) {
            errorMsg.append("- ").append(err).append("\n");
        }
        view.showErrorMessage(errorMsg.toString());
    }

    private boolean validateSubmissionState() {
        if (submissionInProgress) {
            view.showErrorMessage("Another submission is already in progress");
            return false;
        }
        return true;
    }

    private boolean checkUserLogged() {
        return authService.isUserLogged();
    }
    
    /**
     * Operation cancelled.
     * Corresponds to sequence diagram messages m16 and m45.
     */
    public void operationCancelled() {
        handleCancel();
    }
}