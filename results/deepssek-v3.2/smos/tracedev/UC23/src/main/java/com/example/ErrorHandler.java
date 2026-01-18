package com.example;

/**
 * Handles different types of errors and notifies the UI.
 */
public class ErrorHandler {
    private TeachingFormUI ui; // Assumption: UI is injected for notification

    /**
     * Constructor for ErrorHandler.
     * Assumption: UI is set separately or via setter.
     */
    public ErrorHandler() {
        // UI will be set later
    }

    /**
     * Sets the UI for error notifications.
     * @param ui the TeachingFormUI instance
     */
    public void setUi(TeachingFormUI ui) {
        this.ui = ui;
    }

    /**
     * Handles validation errors.
     * @param errorMessage the validation error message
     */
    public void handleValidationError(String errorMessage) {
        System.out.println("Validation error: " + errorMessage);
        if (ui != null) {
            ui.notifyError(errorMessage);
        }
    }

    /**
     * Handles connection errors.
     */
    public void handleConnectionError() {
        String errorMessage = "Connection to SMOS server interrupted";
        System.out.println("Connection error: " + errorMessage);
        if (ui != null) {
            ui.notifyError(errorMessage);
        }
    }

    /**
     * Handles operation interruption.
     */
    public void handleInterruption() {
        String errorMessage = "Operation cancelled";
        System.out.println("Interruption: " + errorMessage);
        if (ui != null) {
            ui.notifyError(errorMessage);
        }
    }
}