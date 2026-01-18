package com.example.monitoring.infrastructure;

/**
 * Infrastructure Layer: Concrete implementation of {@link IErrorLogger} that logs messages to the console.
 * This is a simple logger for demonstration purposes. In a real application, this might
 * log to a file, a logging framework (e.g., Log4j, SLF4J), or an external monitoring system.
 */
public class ConsoleErrorLogger implements IErrorLogger {

    /**
     * Logs an error message and its associated exception to the standard error stream (console).
     * @param message A descriptive message about the error.
     * @param exception The exception that occurred.
     */
    @Override
    public void logError(String message, Exception exception) {
        System.err.println("--- ERROR LOG ---");
        System.err.println("Message: " + message);
        System.err.println("Exception Type: " + exception.getClass().getSimpleName());
        System.err.println("Exception Message: " + exception.getMessage());
        // In a real logger, you might log the full stack trace:
        // exception.printStackTrace(System.err);
        System.err.println("-----------------");
    }
}