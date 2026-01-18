package com.system;

import java.util.HashMap;
import java.util.Map;

/**
 * ErrorHandler class for handling errors and notifications.
 * Methods: handleError, confirmNotification, recoverPreviousState.
 */
public class ErrorHandler {
    private StateManager stateManager;
    private Map<String, ErrorNotification> notifications = new HashMap<>();

    public ErrorHandler(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    public void handleError(ErrorData errorData) {
        String notificationId = "notif_" + System.currentTimeMillis();
        String message = "Error: Tag '" + errorData.getTag() + "' already exists.";
        ErrorNotification notification = new ErrorNotification(notificationId, message, "DuplicateTag");
        notifications.put(notificationId, notification);
        System.out.println("Error notification created: " + notificationId);
    }

    public boolean confirmNotification(String confirmationId) {
        ErrorNotification notification = notifications.get(confirmationId);
        if (notification != null) {
            notification.markAsRead();
            System.out.println("Notification marked as read: " + confirmationId);
            return true;
        }
        return false;
    }

    public SystemState recoverPreviousState() {
        return stateManager.recoverPreviousState();
    }

    // Method corresponding to message m6: Handler -> Notification, "new ErrorNotification()"
    public ErrorNotification createErrorNotification(String notificationId, String message, String errorType) {
        ErrorNotification notification = new ErrorNotification(notificationId, message, errorType);
        notifications.put(notificationId, notification);
        return notification;
    }
}