package com.example.attendancesystem;

/**
 * Custom exception for errors occurring in the external server gateway.
 * This class is added to satisfy R16 from the class diagram.
 */
public class ExternalServerError extends Exception {
    /**
     * Constructs a new ExternalServerError with the specified detail message.
     * @param message the detail message.
     */
    public ExternalServerError(String message) {
        super(message);
    }

    /**
     * Constructs a new ExternalServerError with the specified detail message and cause.
     * @param message the detail message.
     * @param cause the cause (which is saved for later retrieval by the Throwable.getCause() method).
     */
    public ExternalServerError(String message, Throwable cause) {
        super(message, cause);
    }
}