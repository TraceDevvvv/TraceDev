package com.example.application;

/**
 * Application Layer: Handles errors originating from external systems or infrastructure.
 * Specifically added to satisfy the requirement 'Interruption of the connection to the server ETOUR.'
 */
public class ExternalErrorHandler {

    /**
     * Handles a connection error from an external source.
     * Corresponds to `handleConnectionError(source: String, error: Exception)` in the class diagram.
     * @param source The component or system where the error originated (e.g., "TouristRepository").
     * @param error The exception object representing the connection error.
     */
    public void handleConnectionError(String source, Exception error) {
        System.err.println("\n--- External Connection Error Handler ---");
        System.err.println("Source: " + source);
        System.err.println("Error Type: " + error.getClass().getSimpleName());
        System.err.println("Message: " + error.getMessage());
        System.err.println("Full Stack Trace: ");
        error.printStackTrace(System.err); // Print stack trace for debugging
        System.err.println("-----------------------------------------");

        // In a real system, this would involve:
        // 1. Logging the error with a high severity.
        // 2. Triggering an alert (e.g., PagerDuty, Slack notification).
        // 3. Potentially attempting a retry (if transient).
        // 4. Notifying system administrators.
        notifySystemAdministrator("Critical: Connection to " + source + " failed. Details: " + error.getMessage());
    }

    /**
     * Notifies system administrators about a critical issue.
     * Corresponds to `notifySystemAdministrator(message: String)` in the class diagram.
     * @param message The message to send to administrators.
     */
    public void notifySystemAdministrator(String message) {
        System.out.println("ExternalErrorHandler: Notifying system administrator: " + message);
        // This would integrate with an alerting system (e.g., email, SMS, incident management tool).
    }
}