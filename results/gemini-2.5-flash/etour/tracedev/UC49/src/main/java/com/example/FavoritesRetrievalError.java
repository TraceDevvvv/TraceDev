package com.example;

/**
 * Custom exception representing an error during the retrieval or parsing of favorites data.
 * This can wrap underlying connection errors or data parsing issues.
 * Used to satisfy requirement R7 from the audit report.
 */
public class FavoritesRetrievalError extends Exception {

    /**
     * Constructs a new FavoritesRetrievalError with no detail message.
     */
    public FavoritesRetrievalError() {
        super();
    }

    /**
     * Constructs a new FavoritesRetrievalError with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public FavoritesRetrievalError(String message) {
        super(message);
    }

    /**
     * Constructs a new FavoritesRetrievalError with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public FavoritesRetrievalError(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new FavoritesRetrievalError with the specified cause and a detail message of (cause==null ? null : cause.toString())
     * (which typically contains the class and detail message of cause).
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public FavoritesRetrievalError(Throwable cause) {
        super(cause);
    }
}