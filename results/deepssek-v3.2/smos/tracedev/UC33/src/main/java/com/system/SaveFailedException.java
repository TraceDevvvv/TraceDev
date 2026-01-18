package com.system;

import java.util.Date;

/**
 * Exception thrown when saving fails due to SMOS server disconnection.
 */
public class SaveFailedException extends RuntimeException {
    private String message;
    private Date timestamp;

    public SaveFailedException(String message) {
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