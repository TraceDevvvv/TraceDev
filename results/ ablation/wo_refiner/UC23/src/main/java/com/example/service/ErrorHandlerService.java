package com.example.service;

import com.example.model.OperationResult;
import com.example.controller.ErroredUseCaseController;

/**
 * Utility service for handling errors and invoking error use cases.
 */
public class ErrorHandlerService {
    private ErroredUseCaseController erroredUseCaseController;

    public ErrorHandlerService() {
        this.erroredUseCaseController = new ErroredUseCaseController();
    }

    public OperationResult handleError(Exception error) {
        // Log error, send alerts, etc.
        String message = error.getMessage();
        String errorCode = "UNKNOWN";
        if (error instanceof com.example.repository.DatabaseException) {
            errorCode = ((com.example.repository.DatabaseException) error).getErrorCode();
        }
        return new OperationResult(false, message, errorCode);
    }

    public void invokeErroredUseCase(String invalidDataMessage) {
        // Activate the error use case controller
        erroredUseCaseController.activate();
        // Could also log the invalid data message
        System.out.println("ErroredUseCase activated due to: " + invalidDataMessage);
    }
}