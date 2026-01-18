package com.cultural.application.dto;

import java.util.Map;

/**
 * Generic operation result for error handling and service responses.
 */
public class OperationResult {
    private boolean success;
    private String message;
    private String errorCode;
    private Map<String, String> details;

    public OperationResult(boolean success, String message, String errorCode, Map<String, String> details) {
        this.success = success;
        this.message = message;
        this.errorCode = errorCode;
        this.details = details;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Map<String, String> getDetails() {
        return details;
    }
}