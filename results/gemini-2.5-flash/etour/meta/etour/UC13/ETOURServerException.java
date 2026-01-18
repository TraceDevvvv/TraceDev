package com.etour.exception;

/**
 * Custom exception class to represent issues related to the ETOUR server connection.
 * This exception is thrown when there's an interruption in the connection to the ETOUR server,
 * as specified in the use case's exit conditions.
 */
public class ETOURServerException extends Exception {

    /**
     * Constructs a new ETOURServerException with no detail message.
     */
    public ETOURServerException() {
        super();
    }

    /**
     * Constructs a new ETOURServerException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ETOURServerException(String message) {
        super(message);
    }

    /**
     * Constructs a new ETOURServerException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ETOURServerException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new ETOURServerException with the specified cause and a detail message of (cause==null ? null : cause.toString())
     * (which typically contains the class and detail message of cause).
     *
     * @param cause The cause.
     */
    public ETOURServerException(Throwable cause) {
        super(cause);
    }
}