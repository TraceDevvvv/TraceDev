package com.example.serv;

/**
 * Logging service for quality attributes (REQ-013).
 */
public class LoggingService {
    // In real system, use a logger like Log4j.
    public LoggingService() {
        // initialization if needed
    }

    public void logError(String errorMessage) {
        System.err.println("[ERROR] " + errorMessage);
    }

    public void logOperation(String operation, String userId) {
        System.out.println("[INFO] User " + userId + " - " + operation);
    }
}