package com.example;

/**
 * Utility class for handling different types of errors.
 */
public class ErrorHandler {
    private ETOURService etourService;
    private ErroredUseCaseController erroredUseCaseController;

    public ErrorHandler(ETOURService etourService, ErroredUseCaseController erroredUseCaseController) {
        this.etourService = etourService;
        this.erroredUseCaseController = erroredUseCaseController;
    }

    /**
     * Handles validation errors.
     */
    public void handleValidationError(String errorMessage) {
        System.out.println("ErrorHandler: Validation error - " + errorMessage);
        erroredUseCaseController.handleErroredUseCase("VALIDATION_ERROR");
        // After error processed, return m13
        returnErrorProcessed();
    }

    /**
     * Handles server connection errors.
     */
    public void handleServerConnectionError() {
        System.out.println("ErrorHandler: Server connection error.");
        if (!etourService.isConnected()) {
            erroredUseCaseController.handleErroredUseCase("CONNECTION_ERROR");
            // After error processed, return m21
            returnErrorProcessed();
        }
    }

    /**
     * Returns error processed as per m13, m16, m21.
     */
    public void returnErrorProcessed() {
        System.out.println("ErrorHandler: Error processed.");
    }
}