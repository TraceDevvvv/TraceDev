package com.example.model;

import java.time.LocalDateTime;

/**
 * Detailed error information.
 */
public class ErrorDetails {
    public String errorType;
    public String suggestedAction;
    public String technicalDetails;
    public LocalDateTime timestamp;

    public ErrorDetails(String errorType, String suggestedAction, String technicalDetails) {
        this.errorType = errorType;
        this.suggestedAction = suggestedAction;
        this.technicalDetails = technicalDetails;
        this.timestamp = LocalDateTime.now();
    }

    public String getFormattedMessage() {
        return String.format("[%s] %s - %s (Suggested: %s)",
                timestamp, errorType, technicalDetails, suggestedAction);
    }
}