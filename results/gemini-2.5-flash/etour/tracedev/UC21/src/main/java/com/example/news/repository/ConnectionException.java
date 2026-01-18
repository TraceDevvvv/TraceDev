package com.example.news.repository;

/**
 * Custom exception to simulate connection interruptions to the data store.
 * This is used to satisfy the ETOUR requirement in the sequence diagram.
 */
public class ConnectionException extends RuntimeException {
    public ConnectionException(String message) {
        super(message);
    }

    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}