package com.example.touristmgmt;

/**
 * Service responsible for sending notifications.
 */
public class NotificationService {

    /**
     * Sends a deletion notification for a specific tourist.
     * In a real system, this might send an email or an in-app message.
     *
     * @param touristId The ID of the tourist for whom the notification is sent.
     */
    public void sendDeletionNotification(String touristId) {
        System.out.println("NotificationService: Sending deletion notification for tourist ID: " + touristId);
        // Simulate sending a notification
        System.out.println("NotificationService: Notification sent successfully.");
    }
}