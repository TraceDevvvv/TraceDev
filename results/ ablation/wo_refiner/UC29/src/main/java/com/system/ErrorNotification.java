package com.system;

/**
 * ErrorNotification class representing an error notification.
 * Attributes: notificationId, message, errorType.
 * Methods: isRead, markAsRead, getMessage.
 */
public class ErrorNotification {
    private String notificationId;
    private String message;
    private String errorType;
    private boolean read;

    public ErrorNotification(String notificationId, String message, String errorType) {
        this.notificationId = notificationId;
        this.message = message;
        this.errorType = errorType;
        this.read = false;
    }

    public boolean isRead() {
        return read;
    }

    public void markAsRead() {
        this.read = true;
    }

    public String getMessage() {
        return message;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public String getErrorType() {
        return errorType;
    }
}