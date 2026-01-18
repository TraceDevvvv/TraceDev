package com.example.infrastructure;

/**
 * Simple error logger component.
 * Added to satisfy R19.
 */
public class ErrorLogger {

    /**
     * Logs an exception. In a real application, this would write to a log file,
     * monitoring system, etc.
     * @param exception The exception to log.
     */
    public void logError(Exception exception) {
        System.err.println("--- ERROR LOG ---");
        System.err.println("Timestamp: " + new java.util.Date());
        System.err.println("Exception Type: " + exception.getClass().getSimpleName());
        System.err.println("Message: " + exception.getMessage());
        // For demonstration, print stack trace
        exception.printStackTrace(System.err);
        System.err.println("-----------------");
    }
}