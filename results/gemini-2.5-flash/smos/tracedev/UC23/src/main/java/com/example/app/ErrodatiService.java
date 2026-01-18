package com.example.app;

import java.util.List;

/**
 * ErrodatiService simulates an external error reporting or display service.
 * It activates a specific screen or process to handle reported errors.
 */
public class ErrodatiService {

    /**
     * Activates the Errodati service, typically by displaying an error screen
     * or logging errors for further processing.
     *
     * @param errors The list of error messages to be handled by Errodati.
     */
    public void activate(List<String> errors) {
        System.out.println("[ErrodatiService] Activating Errodati screen with errors:");
        for (String error : errors) {
            System.err.println("  - Errodati: " + error);
        }
        // Sequence Diagram: Errodati -> UI: displayErrodatiScreen() (implied, handled by Presenter)
        // Here, we'll just acknowledge the activation.
        System.out.println("[ErrodatiService] Errodati service handled the errors.");
    }
}