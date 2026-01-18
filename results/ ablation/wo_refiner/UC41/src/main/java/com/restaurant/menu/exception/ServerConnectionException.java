package com.restaurant.menu.exception;

import java.util.Date;

/**
 * Exception thrown when server connection is lost (requirement R16).
 */
public class ServerConnectionException extends RuntimeException {
    private String message;
    private Date timestamp;

    public ServerConnectionException(String message) {
        super(message);
        this.message = message;
        this.timestamp = new Date();
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}