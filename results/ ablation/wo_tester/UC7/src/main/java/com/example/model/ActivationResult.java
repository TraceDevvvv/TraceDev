package com.example.model;

/**
 * Result of an activation attempt.
 */
public class ActivationResult {
    private boolean success;
    private String errorMessage;
    private boolean notificationSent;

    public ActivationResult(boolean success, String errorMessage, boolean notificationSent) {
        this.success = success;
        this.errorMessage = errorMessage;
        this.notificationSent = notificationSent;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isNotificationSent() {
        return notificationSent;
    }
}