package com.example.exception;

import java.util.Date;

/**
 * Exception thrown when there is a problem with the ETOUR connection.
 */
public class ETOURConnectionException extends RuntimeException {
    private String message;
    private Date timestamp;

    public ETOURConnectionException(String message, Date timestamp) {
        super(message);
        this.message = message;
        this.timestamp = timestamp;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}