package com.example.model;

import java.time.LocalDateTime;

/**
 * Represents a search error with details.
 */
public class SearchError {
    private int errorId;
    private String errorMessage;
    private LocalDateTime timestamp;

    public SearchError(String errorMessage, ValidationResult validationResult) {
        this.errorId = validationResult.getErrorCode();
        this.errorMessage = errorMessage + " - " + validationResult.getErrorMessage();
        this.timestamp = LocalDateTime.now();
    }

    public SearchError(String errorMessage, int errorId) {
        this.errorId = errorId;
        this.errorMessage = errorMessage;
        this.timestamp = LocalDateTime.now();
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getErrorId() {
        return errorId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}