package com.example.exception;

import java.util.Date;

/**
 * Exception for connection-related errors.
 * Added to satisfy requirement REQ-014.
 */
public class ConnectionException extends Exception {
    private String message;
    private int errorCode;
    private Date timestamp;
    
    public ConnectionException(String message, int errorCode, Date timestamp) {
        super(message);
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = timestamp;
    }
    
    @Override
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
}