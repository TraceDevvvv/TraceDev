package com.example;

/**
 * Handles errors and activates the Errodati use case.
 */
public class ErrorHandler {
    public void handleDataError(ValidationResult validationResult) {
        System.err.println("Data error occurred:");
        for (String err : validationResult.getErrors()) {
            System.err.println(" - " + err);
        }
        activateErrorDataUseCase();
    }

    public void handleSystemError(Exception exception) {
        System.err.println("System error: " + exception.getMessage());
        activateErrorDataUseCase();
    }

    /**
     * Activates the Errodati use case (Flow #4).
     */
    public void activateErrorDataUseCase() {
        System.out.println("Activating Errodati use case...");
        // In a real system, this would trigger specific error handling flows.
    }
}