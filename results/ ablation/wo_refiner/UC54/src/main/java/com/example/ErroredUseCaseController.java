package com.example;

/**
 * Controller for handling errored use cases.
 * Satisfies REQ-006, REQ-007.
 */
public class ErroredUseCaseController {
    /**
     * Handles different types of errors.
     */
    public void handleErroredUseCase(String errorType) {
        System.out.println("ErroredUseCaseController: Handling error type - " + errorType);
        // In a real application, this would trigger specific error recovery flows.
        // Return m15 after completion.
        returnErrorUseCaseCompleted();
    }

    /**
     * Returns error use case completed per m15.
     */
    public void returnErrorUseCaseCompleted() {
        System.out.println("ErroredUseCaseController: Error use case completed.");
    }
}