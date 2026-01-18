package com.example.monitoring.infrastructure;

/**
 * Infrastructure Layer: Interface for an error logging mechanism.
 * Defines the contract for how errors should be logged within the application.
 */
public interface IErrorLogger {

    /**
     * Logs an error message along with an associated exception.
     * @param message A descriptive message about the error.
     * @param exception The exception that occurred.
     */
    void logError(String message, Exception exception);
}