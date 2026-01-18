package com.example.handler;

import com.example.model.ErroredUseCase;

/**
 * Handles various errors (REQ-009, REQ-015).
 */
public class ErrorHandler {

    public void handleInvalidImage(String errorDetails) {
        System.out.println("Invalid image error: " + errorDetails);
        invokeErroredUseCase();
        displayErrorDetails(errorDetails);
    }

    public void handleConnectionError() {
        System.out.println("Connection error: Connection to ETOUR server lost.");
        invokeErroredUseCase();
        displayErrorDetails("Connection to ETOUR server lost");
    }

    public void invokeErroredUseCase() {
        ErroredUseCase erroredUseCase = new ErroredUseCase();
        erroredUseCase.execute();
    }

    public void displayErrorDetails(String errorDetails) {
        System.out.println("Error details displayed to user: " + errorDetails);
    }
}