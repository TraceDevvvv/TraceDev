package com.example.app;

/**
 * ConnectionException is a custom runtime exception indicating a failure
 * in establishing or maintaining a connection to a data source (e.g., database).
 * This acts as the 'ConnectionError' shown in the Sequence Diagram.
 */
public class ConnectionException extends RuntimeException {

    /**
     * Constructs a new ConnectionException with a detail message.
     *
     * @param message The detail message.
     */
    public ConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new ConnectionException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause   The cause.
     */
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}