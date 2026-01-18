package com.example.boundary;

import com.example.dto.TouristDTO;
import com.example.controller.ModifyTouristDataController;
import java.util.List;

/**
 * Boundary class representing the UI form for data modification.
 */
public class FormDisplay {
    private ModifyTouristDataController controller;
    private TouristDTO currentData;

    public FormDisplay(ModifyTouristDataController controller) {
        this.controller = controller;
    }

    /**
     * Display form with current data (trace REQ scripts-5-F.1).
     */
    public void displayForm(TouristDTO data) {
        this.currentData = data;
        System.out.println("Displaying form for user: " + data.getUserId());
        // In a real UI, render the form with data.
    }

    /**
     * Get modified data from the form (trace REQ scripts-5-F.2).
     */
    public TouristDTO getModifiedData() {
        // In a real scenario, collect data from UI fields.
        return currentData;
    }

    /**
     * Show confirmation dialog with modified data (trace REQ scripts-5-F.3).
     */
    public void showConfirmation(TouristDTO data) {
        System.out.println("Please confirm changes for user: " + data.getUserId());
        // Show confirmation UI
    }

    /**
     * Show success message after successful update (trace REQ-012).
     */
    public void showSuccessMessage() {
        System.out.println("Profile updated successfully!");
    }

    /**
     * Show error messages for validation failures.
     */
    public void showErrorMessage(List<String> errors) {
        System.out.println("Errors occurred:");
        for (String error : errors) {
            System.out.println(" - " + error);
        }
    }

    /**
     * Cancel the operation (REQ-013).
     */
    public void cancelOperation() {
        System.out.println("Operation cancelled by user.");
        controller.cancelModification(currentData.getUserId());
    }

    // Sequence diagram messages
    public void showSuccessNotification() {
        System.out.println("Success notification displayed to tourist.");
    }

    public void showValidationErrors(List<String> errors) {
        showErrorMessage(errors);
    }

    public void showConnectionError(String message) {
        System.out.println("Connection error: " + message);
    }

    public void showAuthenticationError() {
        System.out.println("Authentication error: User not authenticated.");
    }

    public void operationCancelled() {
        System.out.println("Operation cancelled.");
    }
}