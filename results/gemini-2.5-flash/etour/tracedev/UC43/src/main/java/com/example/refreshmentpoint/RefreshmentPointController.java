package com.example.refreshmentpoint;

import java.util.List;
import java.util.Map;

/**
 * Controller layer for Refreshment Point editing functionality.
 * Acts as an entry point for UI interactions and orchestrates service and form.
 */
public class RefreshmentPointController {
    private RefreshmentPointService service;
    private RefreshmentPointForm form;
    private AuthenticationService authService; // Added to satisfy requirement EC1

    // Store the ID of the point being edited for subsequent operations
    private String currentlyEditedPointId;

    public RefreshmentPointController(RefreshmentPointService service, RefreshmentPointForm form, AuthenticationService authService) {
        this.service = service;
        this.form = form;
        this.authService = authService;
    }

    /**
     * Handles the request to display the edit form for a specific refreshment point.
     * Implements sequence diagram steps for `requestEditForm`.
     *
     * @param pointId The ID of the refreshment point to edit.
     * @param operatorId The ID of the operator performing the action (for EC1).
     * @return The DTO of the refreshment point if successful, null otherwise (e.g., error or not found).
     */
    public RefreshmentPointDTO requestEditForm(String pointId, String operatorId) {
        System.out.println("\nController: Operator '" + operatorId + "' requests edit form for point ID: " + pointId);

        // EC1: Precondition check - Operator MUST be authenticated
        if (!authService.isAuthenticated(operatorId)) {
            System.err.println("Controller: Access Denied. Operator '" + operatorId + "' is not authenticated.");
            form.displayError("Authentication failed for operator '" + operatorId + "'. Access denied.");
            return null;
        }

        this.currentlyEditedPointId = pointId; // Store the ID of the point being edited
        RefreshmentPointDTO refreshmentPointDTO = null;

        try {
            // Step 1 & 2: Operator enables functionality, System enables it.
            // Step 3: System uploads data.
            refreshmentPointDTO = service.retrievePointData(pointId);

            if (refreshmentPointDTO != null) {
                form.load(refreshmentPointDTO); // Step 4: System displays data in a form.
                System.out.println("Controller: Edit form displayed for " + pointId + ".");
                return refreshmentPointDTO;
            } else {
                form.displayError("Refreshment Point with ID " + pointId + " not found.");
                System.err.println("Controller: Refreshment Point with ID " + pointId + " not found.");
                return null;
            }
        } catch (ETOURConnectionException e) { // XC3: Handle connection error
            form.displayError("Failed to retrieve data: " + e.getMessage());
            System.err.println("Controller: ETOUR Connection Error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Handles the submission of the edit form.
     * Implements sequence diagram steps for `submitEditForm`.
     *
     * @param pointId The ID of the refreshment point being edited.
     * @param formData The RefreshmentPointDTO containing the data submitted from the form.
     */
    public void submitEditForm(String pointId, RefreshmentPointDTO formData) {
        System.out.println("\nController: Operator submits edit form for point ID: " + pointId + " with data: " + formData);

        // Ensure we're submitting for the correct point, although formData should contain it.
        if (!pointId.equals(this.currentlyEditedPointId)) {
            System.err.println("Controller: Error: Attempting to submit for a different point ID than currently edited.");
            form.displayError("Error: Internal ID mismatch. Please restart edit process.");
            return;
        }

        // Implicit user interaction (Form.getData() is not called from Controller->Form in SD,
        // but it is a common pattern to get data from form before passing to service).
        // The SD shows Controller -> Service with formData directly.
        // To match SD, we'll use the formData directly, implying form.getData() happened before.

        Map<String, Object> result = service.processPointUpdate(pointId, formData);

        boolean isValid = (boolean) result.get("isValid");

        if (!isValid) {
            // Data is invalid or insufficient (Step 9)
            List<String> errors = (List<String>) result.get("errors");
            form.displayErrors(errors); // Activates 'Errored' use case by displaying validation errors (FE9).
            System.out.println("Controller: Form submission invalid. Errors displayed to operator.");
        } else {
            // Data is valid
            // Step 8: System asks for confirmation of the change.
            form.displayConfirmationPrompt();
            System.out.println("Controller: Form submission valid. Confirmation prompt displayed to operator.");
        }
    }

    /**
     * Handles the confirmation of an edit operation.
     * Implements sequence diagram steps for `confirmEditOperation`.
     *
     * @param pointId The ID of the refreshment point to confirm edit for.
     */
    public void confirmEditOperation(String pointId) {
        System.out.println("\nController: Operator confirms edit operation for point ID: " + pointId);

        if (!pointId.equals(this.currentlyEditedPointId)) {
            System.err.println("Controller: Error: Attempting to confirm for a different point ID than currently edited.");
            form.displayError("Error: Internal ID mismatch during confirmation. Please restart edit process.");
            return;
        }

        try {
            boolean updateSuccess = service.finalizePointUpdate(pointId);
            if (updateSuccess) {
                form.displaySuccessNotification("Data updated successfully."); // Step 11 & Exit Condition: System notified successful change.
                System.out.println("Controller: Update finalized successfully for point ID: " + pointId);
                this.currentlyEditedPointId = null; // Clear after successful operation
            } else {
                form.displayError("Failed to finalize update for point ID " + pointId + ".");
                System.err.println("Controller: Failed to finalize update for point ID: " + pointId);
            }
        } catch (ETOURConnectionException e) { // XC3: Handle connection error
            form.displayError("Failed to update data: " + e.getMessage());
            System.err.println("Controller: ETOUR Connection Error during finalization: " + e.getMessage());
        }
    }

    /**
     * Handles the cancellation of an edit operation.
     * Implements sequence diagram steps for `cancelEditOperation`.
     */
    public void cancelEditOperation() {
        System.out.println("\nController: Operator cancels edit operation.");
        this.currentlyEditedPointId = null; // Clear the currently edited point ID
        System.out.println("Controller: Edit operation cancelled. Form state reset.");
        // In a real UI, this would typically navigate away or clear the form.
        form.displayError("Edit operation cancelled by operator."); // Using displayError for a neutral "info" message here.
    }
}