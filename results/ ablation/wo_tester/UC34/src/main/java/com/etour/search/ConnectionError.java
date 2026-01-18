package com.etour.search;

import java.util.Date;

/**
 * Exception representing a connection error to the server.
 * Added to satisfy requirement Exit Conditions: Connection to server interrupted
 */
public class ConnectionError extends RuntimeException {
    private String message;
    private int errorCode;
    private Date timestamp;

    public ConnectionError(String message, int errorCode) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = new Date();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the error message for display.
     * @return Formatted error message.
     */
    public String getErrorMessage() {
        return String.format("[%d] %s at %s", errorCode, message, timestamp);
    }
}