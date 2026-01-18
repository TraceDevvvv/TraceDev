package com.example.presentation;

import com.example.controller.JustificationDeleteController;

/**
 * Presentation layer (UI) for handling user interaction.
 */
public class Presentation {
    private JustificationDeleteController controller;

    // Constructor
    public Presentation(JustificationDeleteController controller) {
        this.controller = controller;
    }

    /**
     * Handles a delete button click from the administrator.
     * @param justificationId the ID of the justification to delete (from current view)
     */
    public void handleDeleteClick(int justificationId) {
        // Validate justificationId from current view (message m2)
        if (!validateJustificationId(justificationId)) {
            // Invalid ID, show error
            System.out.println("[UI] Invalid justification ID: " + justificationId);
            return;
        }

        try {
            // Delegate to controller
            boolean success = controller.processDeleteRequest(justificationId);
            if (success) {
                // Show success message (message m23)
                displayDeletionSuccessfulMessage(justificationId);
                // Navigate back to registry screen (message m24)
                navigateBackToRegistryScreen();
            } else {
                // Show failure message (e.g., connection failed)
                System.out.println("[UI] Deletion failed for justification ID: " + justificationId);
            }
        } catch (IllegalArgumentException e) {
            // Justification not found error (message m46)
            displayJustificationNotFoundError(justificationId, e.getMessage());
        } catch (RuntimeException e) {
            // Handle other exceptions (authentication, etc.)
            System.out.println("[UI] Error: " + e.getMessage());
            // Specific error messages as per sequence diagram could be shown here.
        }
    }

    /**
     * Validates the justification ID.
     * @param justificationId the ID to validate
     * @return true if valid, false otherwise
     */
    private boolean validateJustificationId(int justificationId) {
        // Simple validation: ID must be positive
        return justificationId > 0;
    }

    /**
     * Handles cancellation of an ongoing deletion operation.
     * Called when administrator clicks "Cancel" during process (message m32).
     * @param justificationId the ID of the justification being deleted
     */
    public void handleCancelClick(int justificationId) {
        boolean cancelled = controller.cancelDeletion(justificationId);
        if (cancelled) {
            // Display operation cancelled message (message m39)
            displayOperationCancelledMessage(justificationId);
        } else {
            System.out.println("[UI] Cancellation failed for justification ID: " + justificationId);
        }
    }

    /**
     * Displays "Deletion successful" message (message m23).
     * @param justificationId the ID of the justification
     */
    private void displayDeletionSuccessfulMessage(int justificationId) {
        System.out.println("[UI] Deletion successful for justification ID: " + justificationId);
    }

    /**
     * Navigates back to registry screen (message m24).
     */
    private void navigateBackToRegistryScreen() {
        System.out.println("[UI] Navigating back to registry screen.");
    }

    /**
     * Displays "Connection failed" error (message m31).
     * @param justificationId the ID of the justification
     * @param errorMessage the error message
     */
    public void displayConnectionFailedError(int justificationId, String errorMessage) {
        System.out.println("[UI] Connection failed for justification ID: " + justificationId + ", error: " + errorMessage);
    }

    /**
     * Displays "Justification not found" error (message m46).
     * @param justificationId the ID of the justification
     * @param errorMessage the error message
     */
    private void displayJustificationNotFoundError(int justificationId, String errorMessage) {
        System.out.println("[UI] Justification not found for ID: " + justificationId + ", error: " + errorMessage);
    }

    /**
     * Displays "Operation cancelled" message (message m39).
     * @param justificationId the ID of the justification
     */
    private void displayOperationCancelledMessage(int justificationId) {
        System.out.println("[UI] Operation cancelled for justification ID: " + justificationId);
    }
}