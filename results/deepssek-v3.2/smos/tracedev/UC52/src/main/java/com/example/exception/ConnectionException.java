package com.example.exception;

import java.time.LocalDateTime;

/**
 * Exception thrown when connection to the SMOS server is interrupted.
 */
public class ConnectionException extends Exception {
    private String message;
    private LocalDateTime timestamp;

    public ConnectionException(String message) {
        super(message);
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    /**
     * Gets the detailed error message including timestamp.
     * @return formatted error message.
     */
    public String getErrorMessage() {
        return "[" + timestamp + "] Connection Error: " + message;
    }

    // Getters and Setters
    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}