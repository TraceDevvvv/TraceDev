package com.example.error;

import com.example.heritage.CulturalGood;
import com.example.ui.model.ValidationResult;
import com.example.exception.ConnectionException;

/**
 * Handles different types of errors.
 * Added to satisfy requirement REQ-009.
 */
public class ErrorHandler {
    
    public void handleValidationError(ValidationResult validationResult) {
        // Log error and maybe notify monitoring system.
        logError("Validation error: " + String.join(", ", validationResult.getErrorMessages()));
    }
    
    public void handleDuplicateError(CulturalGood culturalGood) {
        logError("Duplicate cultural good found: " + culturalGood.getName() + " at " + culturalGood.getLocation());
    }
    
    public void handleConnectionError(ConnectionException connectionException) {
        logError("Connection error: " + connectionException.getMessage() + " (code: " + connectionException.getErrorCode() + ")");
    }
    
    private void logError(String errorDetails) {
        // Simplified logging (to console).
        System.err.println("Error logged: " + errorDetails);
    }
}