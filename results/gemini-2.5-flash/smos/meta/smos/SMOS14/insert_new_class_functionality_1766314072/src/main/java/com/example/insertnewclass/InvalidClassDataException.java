package com.example.insertnewclass;

/**
 * Custom exception thrown when class data validation fails.
 * This exception is used to signal that the provided data for creating or updating a class
 * does not meet the defined business rules or format requirements.
 * It extends {@link RuntimeException} so it does not need to be explicitly declared in method signatures.
 */
public class InvalidClassDataException extends RuntimeException {

    /**
     * Constructs a new InvalidClassDataException with the specified detail message.
     * The detail message is a String that describes this particular exception.
     *
     * @param message The detail message (which is saved for later retrieval by the {@link #getMessage()} method).
     */
    public InvalidClassDataException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidClassDataException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the {@link #getCause()} method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public InvalidClassDataException(String message, Throwable cause) {
        super(message, cause);
    }
}