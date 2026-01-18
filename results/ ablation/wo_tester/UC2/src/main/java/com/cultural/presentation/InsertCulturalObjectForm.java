package com.cultural.presentation;

import com.cultural.application.port.in.InsertCulturalObjectInputPort;
import com.cultural.application.service.ETOURServerService;
import com.cultural.application.dto.InsertCulturalObjectRequest;

/**
 * Form boundary class for inserting cultural objects.
 * Implements the UI interaction as per sequence diagram.
 */
public class InsertCulturalObjectForm {
    private InsertCulturalObjectInputPort controller;
    private ETOURServerService etourService;

    public InsertCulturalObjectForm(InsertCulturalObjectInputPort controller, ETOURServerService etourService) {
        this.controller = controller;
        this.etourService = etourService;
    }

    /**
     * Activates the insertion feature (step 1).
     * Sequence message: activates insertion feature (m1)
     */
    public void displayForm() {
        // Displays the form UI (step 2)
        // Sequence message: displays form (step 2) (m2)
        // In a real GUI implementation, this would create and show the form window.
    }

    /**
     * Handles form submission (step 4).
     * Sequence message: submits form (step 4) (m4)
     * This method is called when the user submits the form.
     */
    public void onSubmit(InsertCulturalObjectRequest formData) {
        // Step 4: AO submits form
        // The controller's execute method is invoked as part of the flow.
        // In the sequence diagram, this triggers controller.execute.
        // We assume the controller reference is used elsewhere.
    }

    /**
     * Displays an error message.
     * Sequence message: displays error (m10)
     */
    public void showErrorMessage(String message) {
        // Display error message to the user
    }

    /**
     * Displays a duplicate error.
     * Sequence message: displays duplicate error (m17)
     */
    public void showDuplicateError() {
        // Display duplicate error to the user
    }

    /**
     * Displays a connection error.
     * Sequence message: displays connection error (m20)
     */
    public void showConnectionError() {
        // Display connection error to the user
    }

    /**
     * Requests confirmation from the user (step 6).
     * Sequence message: requests confirmation (step 6) (m21)
     * This is triggered by the controller; the form shows a confirmation dialog.
     */
    public void showConfirmationDialog() {
        // Show a confirmation dialog to the AO
    }

    /**
     * Called when the AO confirms the operation (step 8).
     * Sequence message: confirms operation (step 8) (m22)
     */
    public void confirmOperation(String requestId) {
        // The AO confirms the operation; notify the controller.
        // In the sequence, this results in a return message "confirmation received" (m23) to the controller.
        // We assume the controller has a confirmOperation method.
        controller.confirmOperation(requestId);
    }

    /**
     * Called when the AO cancels the operation (step 11).
     * Sequence message: cancels operation (step 11) (m24)
     */
    public void cancelOperation(String requestId) {
        // The AO cancels the operation; notify the controller.
        // In the sequence, this results in a return message "cancellation received" (m25) to the controller.
        // We assume the controller has a cancelOperation method.
        controller.cancelOperation(requestId);
    }

    /**
     * Informs the AO that the operation was cancelled.
     * Sequence message: operation cancelled (m27)
     */
    public void notifyOperationCancelled() {
        // Display a message that the operation was cancelled.
    }

    /**
     * Notifies the AO of proper inclusion (success).
     * Sequence message: notifies proper inclusion (m34)
     */
    public void showSuccessMessage(String message) {
        // Display success message to the user
    }
}