package com.example.exceptions;

import java.util.Date;

/**
 * Exception for SMOS server connection interruption.
 * Exit Condition: The connection to the SMOS server IS interrupted.
 */
public class SMOSConnectionException extends Exception {
    private String errorCode;
    private String message;
    private Date timestamp;

    public SMOSConnectionException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
        this.timestamp = new Date();
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}