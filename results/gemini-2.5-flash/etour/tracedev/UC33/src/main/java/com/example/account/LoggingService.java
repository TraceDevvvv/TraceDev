package com.example.account;

/**
 * Service for logging application activities. (Added to satisfy REQ-004)
 * In a real application, this would integrate with a logging framework like Log4j or SLF4J.
 */
public class LoggingService {
    private boolean isInitialized = false;

    /**
     * Logs a message to the console.
     *
     * @param message The message to log.
     */
    public void log(String message) {
        if (isInitialized) {
            System.out.println("[LOG]: " + message);
        } else {
            System.out.println("[LOG - Uninitialized]: " + message + " (Logging service not yet fully configured)");
        }
    }

    /**
     * Marks the logging service as initialized.
     * (Called by SystemInitializer as per REQ-004)
     */
    public void setInitialized(boolean initialized) {
        this.isInitialized = initialized;
        if (initialized) {
            System.out.println("[LOG]: LoggingService is now fully initialized.");
        } else {
            System.out.println("[LOG]: LoggingService initialization status changed to uninitialized.");
        }
    }
}