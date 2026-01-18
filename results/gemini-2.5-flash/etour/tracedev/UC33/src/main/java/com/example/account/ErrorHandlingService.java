package com.example.account;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Service for centralizing error and exception handling. (Added to satisfy REQ-016)
 * In a real application, this would log errors, send notifications, or provide fallback mechanisms.
 */
public class ErrorHandlingService {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Handles an exception, typically by logging it.
     *
     * @param exception The exception that occurred.
     */
    public void handleException(Exception exception) {
        String timestamp = LocalDateTime.now().format(FORMATTER);
        System.err.println("[ERROR][" + timestamp + "] An exception occurred: " + exception.getClass().getSimpleName());
        System.err.println("  Message: " + exception.getMessage());
        // In a real system, you might log the full stack trace:
        // exception.printStackTrace(System.err);
        // Or send an alert, etc.
    }
}