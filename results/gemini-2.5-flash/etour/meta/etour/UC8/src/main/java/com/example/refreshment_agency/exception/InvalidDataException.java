package com.example.refreshment_agency.exception;

/**
 * Custom exception for invalid data scenarios.
 * This exception is thrown when data provided for a RefreshmentPoint
 * does not meet the defined validation rules.
 */
public class InvalidDataException extends RuntimeException {

    /**
     * Constructs an {@code InvalidDataException} with no detail message.
     */
    public InvalidDataException() {
        super();
    }

    /**
     * Constructs an {@code InvalidDataException} with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public InvalidDataException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause (which is saved for later retrieval by the {@link #getCause()} method).
     *                (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public InvalidDataException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new exception with the specified cause and a detail message of
     * {@code (cause==null ? null : cause.toString())} (which typically contains the class and detail message of {@code cause}).
     *
     * @param cause The cause (which is saved for later retrieval by the {@link #getCause()} method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public InvalidDataException(Throwable cause) {
        super(cause);
    }
}