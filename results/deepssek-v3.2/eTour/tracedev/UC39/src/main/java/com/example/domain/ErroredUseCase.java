package com.example.domain;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents an errored use case scenario.
 * Added to satisfy requirement Quality Requirement (Use case "Errored")
 */
public class ErroredUseCase {
    private String errorCode;
    private String errorMessage;
    private LocalDateTime timestamp;

    public ErroredUseCase(String errorCode, String errorMessage, LocalDateTime timestamp) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.timestamp = timestamp;
    }

    public Map<String, String> getErrorDetails() {
        Map<String, String> details = new HashMap<>();
        details.put("errorCode", errorCode);
        details.put("errorMessage", errorMessage);
        details.put("timestamp", timestamp.toString());
        return details;
    }
}