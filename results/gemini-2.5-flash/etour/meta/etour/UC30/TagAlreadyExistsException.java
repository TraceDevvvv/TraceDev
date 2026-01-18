package com.example.inserttag;

/**
 * Custom exception thrown when an attempt is made to insert a tag
 * that already exists in the system. This corresponds to the
 * "ExistingErrorTag" use case mentioned in the requirements.
 */
public class TagAlreadyExistsException extends Exception {

    /**
     * Constructs a new TagAlreadyExistsException with no detail message.
     */
    public TagAlreadyExistsException() {
        super();
    }

    /**
     * Constructs a new TagAlreadyExistsException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public TagAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructs a new TagAlreadyExistsException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public TagAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new TagAlreadyExistsException with the specified cause and a detail message of (cause==null ? null : cause.toString())
     * (which typically contains the class and detail message of cause).
     *
     * @param cause The cause.
     */
    public TagAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}