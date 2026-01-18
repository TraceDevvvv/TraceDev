package com.example.ui;

import com.example.controller.EditRefreshmentPointController;
import com.example.dto.*;
import com.example.validation.ValidationResult;
import com.example.transaction.ConfirmationRequest;
import com.example.transaction.UpdateResult;
import com.example.domain.ConnectionStatus;
import java.util.Map;

/**
 * UI component representing the edit form.
 * Manages form state and coordinates with controller.
 */
public class RefreshmentPointEditForm {
    private FormState formState;
    private String currentPointId;
    private EditRefreshmentPointController editController;
    private RefreshmentPointUpdateDTO currentUpdateDTO; // holds changes before submission

    public RefreshmentPointEditForm(EditRefreshmentPointController controller) {
        this.editController = controller;
        this.formState = FormState.IDLE;
    }

    /**
     * Loads the list of points (step 1).
     */
    public void loadPointList() {
        // This would typically update UI; for simplicity we just call controller.
        editController.getRefreshmentPointList();
        // In a real UI, we'd store the list.
    }

    /**
     * Selects a point for editing (step 2).
     */
    public void selectPoint(String pointId) {
        this.currentPointId = pointId;
        loadPointData();
    }

    /**
     * Loads point data and displays the edit form.
     */
    public void loadPointData() {
        setFormState(FormState.LOADING);
        // Step 4: fetch latest data (as per sequence diagram)
        Map<String, Object> latestData = editController.fetchLatestPointData(currentPointId);
        // In a real app, we'd merge this data with the details.
        RefreshmentPointDetailsDTO detailsDTO = editController.loadRefreshmentPointDetails(currentPointId);
        displayEditForm(detailsDTO);
        setFormState(FormState.EDITING);
    }

    /**
     * Displays the edit form with current data (step 5).
     */
    public void displayEditForm(RefreshmentPointDetailsDTO detailsDTO) {
        // In a real UI, this would populate form fields.
        System.out.println("Displaying edit form for: " + detailsDTO.getName());
        // Initialize an empty update DTO for this point.
        currentUpdateDTO = new RefreshmentPointUpdateDTO(detailsDTO.getId());
    }

    /**
     * Handles changes in form fields (step 6 loop).
     */
    public void handleFormInputChange(String field, String value) {
        // Update the currentUpdateDTO based on field.
        switch (field) {
            case "name":
                currentUpdateDTO.setUpdatedName(value);
                break;
            case "description":
                currentUpdateDTO.setUpdatedDescription(value);
                break;
            case "location":
                currentUpdateDTO.setUpdatedLocation(value);
                break;
            default:
                // Assume it's an editable attribute.
                currentUpdateDTO.addUpdatedAttribute(field, value);
                break;
        }
    }

    /**
     * Submits the form (step 7).
     */
    public void submitForm(RefreshmentPointUpdateDTO updateDTO) {
        setFormState(FormState.SUBMITTING);
        blockInputControls(); // as per quality requirement
        ValidationResult validationResult = editController.validateUpdateData(updateDTO);
        if (!validationResult.isValid()) {
            displayError(validationResult);
            enableInputControls();
            // Activate errored use case (step 11)
            activateErroredUseCase();
            return;
        }
        // Data is valid, request confirmation.
        ConfirmationRequest request = editController.requestConfirmation(updateDTO);
        displayConfirmationRequest(request);
        setFormState(FormState.CONFIRMING);
    }

    /**
     * Blocks input controls (quality requirement).
     */
    public void blockInputControls() {
        System.out.println("Input controls blocked.");
    }

    /**
     * Enables input controls.
     */
    public void enableInputControls() {
        System.out.println("Input controls enabled.");
    }

    /**
     * Displays a confirmation request to the operator (step 9).
     */
    public void displayConfirmationRequest(ConfirmationRequest request) {
        System.out.println("Confirm update? Confirmation ID: " + request.getConfirmationId());
    }

    /**
     * Displays the result of an update.
     */
    public void displayResult(UpdateResult result) {
        if (result.isSuccess()) {
            System.out.println("Success: " + result.getMessage());
            setFormState(FormState.COMPLETED);
        } else {
            System.out.println("Error: " + result.getMessage());
            setFormState(FormState.ERROR);
        }
        // Controls remain blocked until operation completes (as per quality requirement).
        // In a real UI, we might enable after a delay.
    }

    /**
     * Displays validation errors.
     */
    public void displayError(ValidationResult error) {
        System.out.println("Validation errors:");
        for (String msg : error.getErrorMessages()) {
            System.out.println(" - " + msg);
        }
        setFormState(FormState.ERROR);
    }

    /**
     * Simulates activating the errored use case (step 11).
     */
    public void activateErroredUseCase() {
        System.out.println("Activating errored use case.");
        // In a real system, this might navigate to an error handling screen.
    }

    /**
     * Checks connection status (exit condition).
     */
    public void checkConnectionStatus() {
        ConnectionStatus status = editController.checkServerConnection();
        if (status != ConnectionStatus.CONNECTED) {
            enableInputControls();
            System.out.println("Connection error: " + status);
        }
    }

    // Getters and setters for form state.
    public FormState getFormState() {
        return formState;
    }

    private void setFormState(FormState formState) {
        this.formState = formState;
        System.out.println("Form state changed to: " + formState);
    }

    // Methods for operator interaction (simulated from sequence diagram).
    public void operatorConfirms(String confirmationId) {
        // Assuming operatorId is retrieved from authentication service via controller.
        // For simplicity, we pass null; in real app we'd get current operator.
        UpdateResult result = editController.confirmAndExecuteUpdate(confirmationId, null);
        displayResult(result);
    }

    public void operatorCancels(String confirmationId) {
        editController.cancelUpdate(confirmationId);
        enableInputControls();
        System.out.println("Operation cancelled by operator.");
        setFormState(FormState.IDLE);
    }
}