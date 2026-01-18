package com.example;

/**
 * Custom exception representing a connection error to an external service.
 * Used to satisfy requirement R7 from the audit report.
 */
public class ConnectionError extends Exception {

    /**
     * Constructs a new ConnectionError with no detail message.
     */
    public ConnectionError() {
        super();
    }

    /**
     * Constructs a new ConnectionError with the specified detail message.
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ConnectionError(String message) {
        super(message);
    }

    /**
     * Constructs a new ConnectionError with the specified detail message and cause.
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ConnectionError(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new ConnectionError with the specified cause and a detail message of (cause==null ? null : cause.toString())
     * (which typically contains the class and detail message of cause).
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     *              (A null value is permitted, and indicates that the cause is nonexistent or unknown.)
     */
    public ConnectionError(Throwable cause) {
        super(cause);
    }
}