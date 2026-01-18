package com.example.application.result;

import java.util.UUID;

/**
 * Result object returned by the insert justification use case.
 */
public class JustificationResult {
    private boolean success;
    private UUID justificationId;
    private String message;

    public JustificationResult(boolean success, UUID justificationId, String message) {
        this.success = success;
        this.justificationId = justificationId;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public UUID getJustificationId() {
        return justificationId;
    }

    public String getMessage() {
        return message;
    }
}