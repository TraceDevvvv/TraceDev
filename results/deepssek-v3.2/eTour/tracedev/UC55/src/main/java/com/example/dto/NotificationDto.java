package com.example.dto;

/**
 * Data transfer object for notifications.
 * Includes validation for clarity and actionability.
 */
public class NotificationDto {
    private String userId;
    private String message;
    private String actionMessage;
    private boolean isActionable;

    public NotificationDto(String userId, String message, String actionMessage, boolean isActionable) {
        this.userId = userId;
        this.message = message;
        this.actionMessage = actionMessage;
        this.isActionable = isActionable;
    }

    public String getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public String getActionMessage() {
        return actionMessage;
    }

    public boolean isActionable() {
        return isActionable;
    }

    @Override
    public String toString() {
        return "NotificationDto{" +
                "userId='" + userId + '\'' +
                ", message='" + message + '\'' +
                ", actionMessage='" + actionMessage + '\'' +
                ", isActionable=" + isActionable +
                '}';
    }

    /**
     * Validates that the notification is clear and actionable.
     * @return true if the notification is valid.
     */
    public boolean validateClarity() {
        return message != null && !message.trim().isEmpty() &&
               actionMessage != null && !actionMessage.trim().isEmpty() &&
               isActionable;
    }
}