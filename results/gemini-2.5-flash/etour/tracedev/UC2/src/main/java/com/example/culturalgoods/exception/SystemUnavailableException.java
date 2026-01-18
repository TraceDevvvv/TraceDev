package com.example.culturalgoods.exception;

/**
 * Custom exception for indicating that the system is currently unavailable.
 * This is a higher-level exception that might wrap underlying connection issues.
 * Satisfies requirement: Exit Condition: Connection to ETOUR server.
 */
public class SystemUnavailableException extends Exception {
    public SystemUnavailableException(String message) {
        super(message);
    }

    public SystemUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }
}