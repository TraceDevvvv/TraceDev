package com.example.model;

import java.time.LocalDateTime;

/**
 * The result of a validation operation.
 */
public class ValidationResult {
    public boolean isValid;
    public int errorCode;
    public String errorMessage;
    public LocalDateTime validationTimestamp;
    private ErrorDetails errorDetails;

    public ValidationResult(boolean isValid, int errorCode, String errorMessage) {
        this.isValid = isValid;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.validationTimestamp = LocalDateTime.now();
        if (!isValid) {
            this.errorDetails = new ErrorDetails("ValidationError", "Check credentials", errorMessage);
        }
    }

    public ErrorDetails getErrorDetails() {
        return errorDetails;
    }
}