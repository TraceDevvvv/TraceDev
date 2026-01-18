package com.example.tagsystem.errorhandling;

/**
 * Custom exception indicating that provided tag data is invalid.
 * This is a runtime exception, meaning it does not need to be explicitly declared in method signatures (unchecked).
 */
public class InvalidTagDataException extends RuntimeException {

    /**
     * Constructs a new InvalidTagDataException with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public InvalidTagDataException(String message) {
        super(message);
    }
}