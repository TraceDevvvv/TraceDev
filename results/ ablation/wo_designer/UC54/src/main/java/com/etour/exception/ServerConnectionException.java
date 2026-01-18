package com.etour.exception;

/**
 * Exception thrown when connection to the server is interrupted.
 */
public class ServerConnectionException extends Exception {
    public ServerConnectionException(String message) {
        super(message);
    }
}