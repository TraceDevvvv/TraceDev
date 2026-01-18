package com.example.refreshment_agency.exception;

/**
 * Custom exception for when a RefreshmentPoint is not found.
 * This exception is thrown when an operation is requested on a RefreshmentPoint
 * that does not exist in the system (e.g., by ID).
 */
public class RefreshmentPointNotFoundException extends RuntimeException {

    /**
     * Constructs a {@code RefreshmentPointNotFoundException} with no detail message.
     */
    public RefreshmentPointNotFoundException() {
        super();
    }

    /**
     * Constructs a {@code RefreshmentPointNotFoundException} with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public RefreshmentPointNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause (which is saved for later retrieval by the {@link #getCause()} method).
     *                (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public RefreshmentPointNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message of
     * {@code (cause==null ? null : cause.toString())} (which typically contains the class and detail message of {@code cause}).
     *
     * @param cause The cause (which is saved for later retrieval by the {@link #getCause()} method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public RefreshmentPointNotFoundException(Throwable cause) {
        super(cause);
    }
}