package com.example.handler;

import com.example.model.ValidationResult;

/**
 * Interface for handling errored use cases.
 * Added to satisfy requirement REQ-011
 */
public interface ErroredUseCaseHandler {
    /**
     * Handles image validation errors.
     * @param validationResult the validation result containing errors
     */
    void handleImageValidationError(ValidationResult validationResult);

    /**
     * Handles connection errors.
     * @param errorMessage the error message
     */
    void handleConnectionError(String errorMessage);
}