package com.example.commentediting.exception;

/**
 * Custom exception to indicate technical service failures,
 * such as database connection issues or other infrastructure problems. (R12)
 */
public class TechnicalServiceException extends Exception {
    /**
     * Constructs a TechnicalServiceException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public TechnicalServiceException(String message) {
        super(message);
    }
}