package com.example.infrastructure.error;

import com.example.application.interfaces.IErrorHandler;
import com.example.application.dto.ValidationResult;

/**
 * Concrete error handler that logs errors.
 */
public class ErrorHandlerImpl implements IErrorHandler {
    @Override
    public void handle(ValidationResult error) {
        System.err.println("Error handled: " + error.getErrorMessage());
        // Could also send to monitoring system, etc.
    }
}