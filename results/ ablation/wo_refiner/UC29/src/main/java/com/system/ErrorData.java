package com.system;

import java.time.LocalDateTime;

/**
 * ErrorData class representing the data of an error.
 * Attributes: tag, errorCode, timestamp.
 * Includes getter methods.
 */
public class ErrorData {
    private String tag;
    private String errorCode;
    private LocalDateTime timestamp;

    public ErrorData(String tag, String errorCode) {
        this.tag = tag;
        this.errorCode = errorCode;
        this.timestamp = LocalDateTime.now();
    }

    public String getTag() {
        return tag;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}