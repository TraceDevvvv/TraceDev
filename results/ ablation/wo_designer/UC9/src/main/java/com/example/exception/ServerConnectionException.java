package com.example.exception;

/**
 * Custom exception for server connection failures.
 */
public class ServerConnectionException extends Exception {
    public ServerConnectionException(String message) {
        super(message);
    }
}