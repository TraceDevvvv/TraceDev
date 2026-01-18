package com.etour.error;

/**
 * Controller for handling errors in use cases.
 */
public class ErroredUseCaseController {
    /**
     * Handles validation errors.
     * @param validationResult the validation result containing errors.
     */
    public void handleValidationError(ValidationResult validationResult) {
        // Log validation errors
        System.err.println("Validation errors encountered:");
        for (ValidationError error : validationResult.getErrors()) {
            System.err.println(" - Field: " + error.getField() + ", Message: " + error.getMessage() + ", Code: " + error.getCode());
        }
    }

    /**
     * Handles system errors.
     * @param exception the exception that occurred.
     */
    public void handleSystemError(Exception exception) {
        // Log system errors
        System.err.println("System error: " + exception.getMessage());
        exception.printStackTrace();
    }
}