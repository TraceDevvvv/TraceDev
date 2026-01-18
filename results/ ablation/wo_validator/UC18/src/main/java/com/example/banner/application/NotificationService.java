package com.example.banner.application;

import java.util.ArrayList;
import java.util.List;

/**
 * Service for sending and acknowledging notifications.
 */
public class NotificationService {
    private List<String> notificationQueue; // simplified: store messages as strings

    public NotificationService() {
        this.notificationQueue = new ArrayList<>();
    }

    /**
     * Sends a notification by adding it to the queue.
     * @param message the notification message
     */
    public void sendNotification(String message) {
        notificationQueue.add(message);
        // In a real system, this would push to a message broker or UI
        System.out.println("Notification sent: " + message);
    }

    /**
     * Acknowledges the last notification (removes from queue).
     */
    public void acknowledgeNotification() {
        if (!notificationQueue.isEmpty()) {
            String msg = notificationQueue.remove(notificationQueue.size() - 1);
            System.out.println("Notification acknowledged: " + msg);
        }
    }
}