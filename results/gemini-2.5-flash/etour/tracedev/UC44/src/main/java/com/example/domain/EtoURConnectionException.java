package com.example.domain;

/**
 * Custom runtime exception to indicate a connection error with the external EtoUR system (Agency).
 * (Added to satisfy REQ-003)
 */
public class EtoURConnectionException extends RuntimeException {

    /**
     * Constructs a new EtoURConnectionException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public EtoURConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new EtoURConnectionException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public EtoURConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}