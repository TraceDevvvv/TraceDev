package com.example.application.usecases;

import com.example.application.interfaces.IErrorHandler;
import com.example.application.dto.ValidationResult;

/**
 * Use case dedicated to handling errors.
 */
public class ErroredUseCase {
    private IErrorHandler errorHandler;

    public ErroredUseCase(IErrorHandler handler) {
        this.errorHandler = handler;
    }

    public void execute(ValidationResult error) {
        if (errorHandler != null) {
            errorHandler.handle(error);
        } else {
            System.err.println("Error handler not set. Error message: " + error.getErrorMessage());
        }
    }
}