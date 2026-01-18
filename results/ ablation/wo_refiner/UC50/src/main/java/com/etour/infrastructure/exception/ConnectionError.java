package com.etour.infrastructure.exception;

import java.util.Date;

/**
 * Exception representing a connection failure.
 */
public class ConnectionError extends RuntimeException {
    private Date timestamp;

    public ConnectionError(String message) {
        super(message);
        this.timestamp = new Date();
    }

    public Date getTimestamp() {
        return timestamp;
    }
}