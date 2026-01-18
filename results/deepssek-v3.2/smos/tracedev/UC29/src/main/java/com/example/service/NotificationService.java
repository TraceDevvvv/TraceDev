package com.example.service;

import com.example.dto.AbsenceNotificationDTO;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Service for sending notifications.
 */
public class NotificationService {
    private Queue<AbsenceNotificationDTO> notificationQueue = new LinkedList<>();

    /**
     * Sends an email notification.
     * Returns true if sent successfully.
     */
    public boolean sendEmail(AbsenceNotificationDTO notification) {
        // Simulate email sending
        System.out.println("Sending email to " + notification.parentEmail + ": " + notification.generateMessage());
        // Assume success for demonstration
        return true;
    }

    /**
     * Queues a notification for later processing.
     */
    public void queueNotification(AbsenceNotificationDTO notification) {
        notificationQueue.add(notification);
        System.out.println("Notification queued for " + notification.parentEmail);
    }

    /**
     * Processes the notification queue.
     */
    public void processQueue() {
        while (!notificationQueue.isEmpty()) {
            AbsenceNotificationDTO notification = notificationQueue.poll();
            sendEmail(notification);
        }
    }

    /**
     * Clears the notification queue.
     */
    public void clearQueue() {
        notificationQueue.clear();
        System.out.println("Notification queue cleared.");
    }
}