package com.example.repository;

/**
 * Exception thrown when server connection fails.
 */
public class ServerConnectionException extends Exception {
    private String message;
    private String timestamp;

    public ServerConnectionException(String message) {
        super(message);
        this.message = message;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    public ServerConnectionException(String message, Throwable cause) {
        super(message, cause);
        this.message = message;
        this.timestamp = java.time.LocalDateTime.now().toString();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public String getTimestamp() {
        return timestamp;
    }
}