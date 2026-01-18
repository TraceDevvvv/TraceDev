package com.example.facade;

import com.example.model.Confirmation;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles user notifications and confirmations (implements REQ‑1006, REQ‑1007, REQ‑1008).
 */
public class NotificationFacade {
    // Attribute Handler>> from class diagram
    private List<Notification> handler = new ArrayList<>();

    /**
     * Asks the user for confirmation (renamed from showError per REQ‑1006).
     * @param message the message to display
     * @return a Confirmation object with a generated notification ID
     */
    public Confirmation askForConfirmation(String message) {
        System.out.println("[Notification] " + message);
        String notificationId = "notif-" + UUID.randomUUID().toString().substring(0, 8);
        return new Confirmation(false, notificationId, false);
    }

    /**
     * Shows an error notification as per class diagram.
     * @param message the error message
     * @param notificationType the type of notification
     * @return a Confirmation object
     */
    public Confirmation showError(String message, String notificationType) {
        System.out.println("[Notification] Error (" + notificationType + "): " + message);
        String notificationId = "err-" + UUID.randomUUID().toString().substring(0, 8);
        // Create and store a notification
        Notification notif = new Notification(notificationId, message, notificationType);
        handler.add(notif);
        return new Confirmation(false, notificationId, false);
    }

    /**
     * Dismisses a notification.
     * @param notificationId the notification ID
     * @return true if dismissed
     */
    public boolean dismissNotification(String notificationId) {
        System.out.println("[Notification] Dismissed: " + notificationId);
        boolean removed = handler.removeIf(n -> n.notificationId.equals(notificationId));
        return removed;
    }

    /**
     * Clears all pending notifications.
     */
    public void clearPendingNotifications() {
        System.out.println("[Notification] All pending notifications cleared.");
        handler.clear();
    }

    /**
     * User acknowledges a notification (implements REQ‑1007).
     * @param notificationId the notification ID
     * @return a Confirmation indicating user acknowledgement
     */
    public Confirmation acknowledgeNotification(String notificationId) {
        System.out.println("[Notification] User acknowledged: " + notificationId);
        // Mark notification as acknowledged
        for (Notification n : handler) {
            if (n.notificationId.equals(notificationId)) {
                n.acknowledged = true;
                break;
            }
        }
        return new Confirmation(true, notificationId, true);
    }

    // Inner class representing <<Notification>> stereotype
    private static class Notification {
        String notificationId;
        String message;
        String type;
        boolean acknowledged;

        Notification(String notificationId, String message, String type) {
            this.notificationId = notificationId;
            this.message = message;
            this.type = type;
            this.acknowledged = false;
        }
    }
}