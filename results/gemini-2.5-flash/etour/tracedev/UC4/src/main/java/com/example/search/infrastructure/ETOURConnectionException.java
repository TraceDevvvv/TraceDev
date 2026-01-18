package com.example.search.infrastructure;

/**
 * Custom exception to indicate a failure in connecting to the ETOUR external system.
 * This is used to handle the "Interruption of connection to server ETOUR" scenario
 * described in the sequence diagram.
 */
public class ETOURConnectionException extends RuntimeException {
    public ETOURConnectionException(String message) {
        super(message);
    }

    public ETOURConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}