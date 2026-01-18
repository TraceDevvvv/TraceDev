package com.address_management_system;

/**
 * Custom exception class for handling data validation errors.
 * This exception is thrown when input data does not meet the required criteria.
 */
public class ValidationException extends RuntimeException {

    /**
     * Constructs a new ValidationException with no detail message.
     */
    public ValidationException() {
        super();
    }

    /**
     * Constructs a new ValidationException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     * Constructs a new ValidationException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause (which is saved for later retrieval by the getCause() method).
     *                (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new ValidationException with the specified cause and a detail message of (cause==null ? null : cause.toString())
     * (which typically contains the class and detail message of cause).
     *
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ValidationException(Throwable cause) {
        super(cause);
    }
}