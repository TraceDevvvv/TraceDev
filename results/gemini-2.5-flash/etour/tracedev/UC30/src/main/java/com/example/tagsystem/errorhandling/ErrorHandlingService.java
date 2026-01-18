package com.example.tagsystem.errorhandling;

/**
 * Service responsible for activating specific error use cases.
 * This acts as a centralized point for handling different error scenarios as per requirements R10, R11.
 */
public class ErrorHandlingService {

    /**
     * Activates the generic "Errored Use Case".
     * This might involve logging, sending alerts, or transitioning to an error state in a larger system.
     * @param message The error message associated with the errored use case.
     */
    public void activateErroredUseCase(String message) {
        System.out.println("[ErrorHandlingService] Activating 'Errored' Use Case: " + message);
        // Placeholder for complex error handling logic, e.g.,
        // - Log the error
        // - Send an alert to administrators
        // - Display a generic error page to the user (handled by controller)
    }

    /**
     * Activates the specific "Tag Already Exists Error Use Case".
     * This is distinct from the generic errored use case, potentially leading to a different user experience or internal process.
     * @param message The error message indicating a tag already exists.
     */
    public void activateErroreTagEsistenteUseCase(String message) {
        System.out.println("[ErrorHandlingService] Activating 'Errore Tag Esistente' Use Case: " + message);
        // Placeholder for specific error handling for duplicate tags, e.g.,
        // - Provide suggestions to the user
        // - Redirect to a tag search page
    }
}