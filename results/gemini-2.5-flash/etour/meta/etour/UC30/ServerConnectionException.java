package com.example.inserttag;

/**
 * Custom exception for simulating server connection interruptions (ETOUR).
 * This exception is thrown to represent a scenario where the connection
 * to the server is interrupted, as described in the use case's exit conditions.
 */
public class ServerConnectionException extends Exception {

    /**
     * Constructs a new ServerConnectionException with no detail message.
     */
    public ServerConnectionException() {
        super();
    }

    /**
     * Constructs a new ServerConnectionException with the specified detail message.
     *
     * @param message The detail message (which is saved for later retrieval by the getMessage() method).
     */
    public ServerConnectionException(String message) {
        super(message);
    }

    /**
     * Constructs a new ServerConnectionException with the specified detail message and cause.
     *
     * @param message The detail message.
     * @param cause The cause (which is saved for later retrieval by the getCause() method).
     */
    public ServerConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new ServerConnectionException with the specified cause and a detail message of (cause==null ? null : cause.toString())
     * (which typically contains the class and detail message of cause).
     *
     * @param cause The cause.
     */
    public ServerConnectionException(Throwable cause) {
        super(cause);
    }
}