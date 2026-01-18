package com.example.bannerapp;

/**
 * Custom exception to indicate an operation was attempted without proper authorization.
 */
public class UnauthorizedOperationException extends RuntimeException {
    /**
     * Constructs a new UnauthorizedOperationException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public UnauthorizedOperationException(String message) {
        super(message);
    }

    /**
     * Constructs a new UnauthorizedOperationException with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public UnauthorizedOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}