package application;

import exceptions.EditPointException;

/**
 * Handles error scenarios (Flow step 10).
 */
public class ErroredUseCase {

    /**
     * Handles validation errors.
     * @param validationResult the validation result containing errors.
     */
    public void handleError(ValidationResult validationResult) {
        // Log validation errors (simulated)
        System.out.println("Validation errors: " + validationResult.getErrors());
    }

    /**
     * Logs exceptions.
     * @param errorDetails the exception to log.
     */
    public void logError(EditPointException errorDetails) {
        // Log the exception (simulated)
        System.out.println("Error logged: " + errorDetails.getMessage() + " - Type: " + errorDetails.getErrorType());
    }
}