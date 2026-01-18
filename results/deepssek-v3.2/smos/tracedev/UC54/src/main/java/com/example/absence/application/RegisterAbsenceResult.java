package com.example.absence.application;

/**
 * Result object returned after executing the register absence use case.
 */
public class RegisterAbsenceResult {
    private boolean success;
    private String message;
    private int notificationCount;

    public RegisterAbsenceResult(boolean success, String message, int notificationCount) {
        this.success = success;
        this.message = message;
        this.notificationCount = notificationCount;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public int getNotificationCount() {
        return notificationCount;
    }
}