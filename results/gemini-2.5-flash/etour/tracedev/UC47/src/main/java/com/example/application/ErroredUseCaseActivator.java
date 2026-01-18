package com.example.application;

/**
 * Application Layer: Activates specific handling for errored use cases.
 * This could involve logging, notification, or triggering alternative flows.
 */
public class ErroredUseCaseActivator {

    /**
     * Activates an errored use case scenario.
     * Corresponds to `activate(errorCode: String, message: String)` in the class diagram.
     * @param errorCode A specific code identifying the type of error.
     * @param message A descriptive message about the error.
     */
    public void activate(String errorCode, String message) {
        System.err.println("\n--- Errored Use Case Activated ---");
        System.err.println("Error Code: " + errorCode);
        System.err.println("Message: " + message);
        System.err.println("This would trigger specific error handling logic (e.g., logging, alerting, user notification).");
        System.err.println("----------------------------------");
    }
}