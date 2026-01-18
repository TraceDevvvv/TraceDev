package com.system.application;

import com.system.domain.NotificationType;

/**
 * Context object for notifications, ensuring they are clear and actionable.
 */
public class NotificationContext {
    private String message;
    private NotificationType type;

    public NotificationContext(String message, NotificationType type) {
        this.message = message;
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public NotificationType getType() {
        return type;
    }
}