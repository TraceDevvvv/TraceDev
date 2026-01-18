package com.example.application;

import java.util.Date;

/**
 * Represents contextual information about an error that occurred during a use case execution.
 * This can be used to provide detailed error feedback to the presentation layer.
 * (Added to satisfy REQ-005)
 */
public class ErroredUseCaseContext {
    private String errorDetails;
    private String errorCode;
    private Date timestamp;

    /**
     * Constructs an ErroredUseCaseContext.
     *
     * @param errorDetails A descriptive message about the error.
     * @param errorCode A specific code identifying the type of error.
     */
    public ErroredUseCaseContext(String errorDetails, String errorCode) {
        this.errorDetails = errorDetails;
        this.errorCode = errorCode;
        this.timestamp = new Date(); // Set current timestamp
    }

    public String getErrorDetails() {
        return errorDetails;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "ErroredUseCaseContext{" +
               "errorDetails='" + errorDetails + '\'' +
               ", errorCode='" + errorCode + '\'' +
               ", timestamp=" + timestamp +
               '}';
    }
}