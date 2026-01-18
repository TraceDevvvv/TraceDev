package com.example.view;

import com.example.dto.CulturalObjectDTO;
import com.example.controller.ChangeCulturalObjectController;

/**
 * View for editing a cultural object.
 */
public class CulturalObjectEditView {
    private ChangeCulturalObjectController controller;

    public CulturalObjectEditView(ChangeCulturalObjectController controller) {
        this.controller = controller;
    }

    public void displayFormForEditing(CulturalObjectDTO dto) {
        // Simulated display - in real implementation would populate form fields
        System.out.println("Displaying edit form for object: " + dto.getName());
        System.out.println("  ID: " + dto.getId());
        System.out.println("  Description: " + dto.getDescription());
        System.out.println("  Location: " + dto.getLocation());
        System.out.println("  Period: " + dto.getHistoricalPeriod());
        enableFormControls();
    }

    public void enableFormControls() {
        System.out.println("Form controls enabled");
    }

    public void disableFormControls() {
        System.out.println("Form controls disabled");
    }

    public void showConfirmationDialog() {
        System.out.println("Showing confirmation dialog");
        // In real implementation, this would be a modal dialog
        // For simulation, we auto-confirm after a short delay
        simulateUserConfirmation();
    }

    public void showErrorMessage(String message) {
        System.out.println("ERROR: " + message);
    }

    public void onFormSubmitted(CulturalObjectDTO updatedData) {
        controller.handleFormSubmission(updatedData);
    }

    public void onCancel() {
        controller.handleCancel();
    }

    private void simulateUserConfirmation() {
        // Simulate user confirming after a short delay
        new Thread(() -> {
            try {
                Thread.sleep(500); // Simulate user thinking time
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            System.out.println("User confirmed operation");
            // In real app, this would be triggered by actual user action
            // For simulation, we call controller directly
            controller.handleConfirmation();
        }).start();
    }

    /**
     * Simulates user editing and submitting the form.
     */
    public void simulateEditAndSubmit() {
        // Create updated DTO
        CulturalObjectDTO updated = new CulturalObjectDTO(1, "Updated Name", 
                "Updated Description", "Updated Location", "Updated Period");
        onFormSubmitted(updated);
    }
    
    /**
     * Displays form for editing.
     * Corresponds to sequence diagram message m13.
     */
    public void displaysFormForEditing(CulturalObjectDTO dto) {
        displayFormForEditing(dto);
    }
    
    /**
     * Cancels operation.
     * Corresponds to sequence diagram message m14.
     */
    public void cancelsOperation() {
        onCancel();
    }
    
    /**
     * Changes data in the form.
     * Corresponds to sequence diagram message m17.
     */
    public void changesDataInTheForm(CulturalObjectDTO dto) {
        // This would typically be handled by form field listeners.
        // For simulation, we'll just call displayFormForEditing with new data.
        displayFormForEditing(dto);
    }
    
    /**
     * Submits form.
     * Corresponds to sequence diagram message m18.
     */
    public void submitsForm(CulturalObjectDTO updatedData) {
        onFormSubmitted(updatedData);
    }
    
    /**
     * Displays confirmation dialog.
     * Corresponds to sequence diagram message m26.
     */
    public void displaysConfirmationDialog() {
        showConfirmationDialog();
    }
    
    /**
     * Confirms operation.
     * Corresponds to sequence diagram message m27.
     */
    public void confirmsOperation() {
        // This would be triggered by user clicking confirm button.
        // We'll directly call controller's handleConfirmation.
        controller.handleConfirmation();
    }
    
    /**
     * Input controls blocked.
     * Corresponds to sequence diagram message m30.
     */
    public void inputControlsBlocked() {
        disableFormControls();
    }
    
    /**
     * Display success message.
     * Corresponds to sequence diagram message m35.
     */
    public void displaySuccessMessage(String message) {
        System.out.println("SUCCESS: " + message);
    }
    
    /**
     * Shows success notification.
     * Corresponds to sequence diagram message m36.
     */
    public void showsSuccessNotification(String message) {
        System.out.println("SUCCESS NOTIFICATION: " + message);
    }
    
    /**
     * Displays error message.
     * Corresponds to sequence diagram message m40.
     */
    public void displaysErrorMessage(String message) {
        showErrorMessage(message);
    }
    
    /**
     * Input controls re-enabled.
     * Corresponds to sequence diagram message m42.
     */
    public void inputControlsReenabled() {
        enableFormControls();
    }
    
    /**
     * Cancels operation (second occurrence).
     * Corresponds to sequence diagram message m43.
     */
    public void cancelsOperation2() {
        onCancel();
    }
    
    /**
     * Displays error messages.
     * Corresponds to sequence diagram message m49.
     */
    public void displaysErrorMessages(String errorMessages) {
        showErrorMessage(errorMessages);
    }
    
    /**
     * Input controls re-enabled (second occurrence).
     * Corresponds to sequence diagram message m51.
     */
    public void inputControlsReenabled2() {
        enableFormControls();
    }
}