package com.example.addressapp.usecase;

/**
 * Represents the "Errodati" use case, typically activated on error conditions.
 * In a real application, this might log errors, send notifications, or
 * trigger other error handling processes.
 */
public class ErrodatiUseCase {

    /**
     * Activates the Errodati use case with specific error details.
     *
     * @param errorCode A string code representing the type of error.
     * @param errorMessage A descriptive message about the error.
     */
    public void activate(String errorCode, String errorMessage) {
        System.out.println("[Errodati] ACTIVATED! Error Code: " + errorCode + ", Message: " + errorMessage);
        // In a real system, this would do more complex error handling,
        // like logging, notifying administrators, etc.
    }
}