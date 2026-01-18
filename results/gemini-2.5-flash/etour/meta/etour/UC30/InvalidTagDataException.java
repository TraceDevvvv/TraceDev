package com.example.inserttag;

/**
 * Custom exception thrown when the data provided for a tag is invalid or insufficient.
 * This corresponds to the "Errored" use case mentioned in the requirements.
 */
public class InvalidTagDataException extends Exception {

    /**
     * Constructs a new InvalidTagDataException with no detail message.
     */
    public InvalidTagDataException() {
        super();
    }

    /**
     * Constructs a new InvalidTagDataException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public InvalidTagDataException(String message) {
        super(message);
    }

    /**
     * Constructs a new InvalidTagDataException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public InvalidTagDataException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new InvalidTagDataException with the specified cause and a detail message of (cause==null ? null : cause.toString())
     * (which typically contains the class and detail message of cause).
     *
     * @param cause The cause.
     */
    public InvalidTagDataException(Throwable cause) {
        super(cause);
    }
}