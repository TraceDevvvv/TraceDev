package com.etour.exception;

/**
 * Custom exception for ETOUR connection errors.
 */
public class ETOURConnectionException extends Exception {
    private String errorCode;
    private String serverName;

    public ETOURConnectionException(String message, String errorCode, String serverName) {
        super(message);
        this.errorCode = errorCode;
        this.serverName = serverName;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getServerName() {
        return serverName;
    }

    /**
     * Returns a formatted error message.
     */
    public String getErrorMessage() {
        return "ETOUR Connection Error [" + errorCode + "] on server " + serverName + ": " + getMessage();
    }
}