package com.example.infrastructure;

/**
 * Custom exception to simulate connection issues to an external system (SMOS).
 * This is used to demonstrate error handling in the EnrollmentService.
 */
public class SMOSConnectionException extends Exception {
    public SMOSConnectionException(String message) {
        super(message);
    }

    public SMOSConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}