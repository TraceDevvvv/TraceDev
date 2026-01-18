package com.example;

/**
 * Implementation of INotificationService.
 * This service is responsible for sending notifications related to feedback modifications.
 * (Added to satisfy Exit Condition: The notification system has been modified REQ-003)
 */
public class NotificationService implements INotificationService {

    @Override
    public void notifyCommentModified(String feedbackId) {
        // In a real application, this would involve sending an email, push notification,
        // or updating an internal dashboard.
        System.out.println("NotificationService: Notifying that comment for Feedback ID '" + feedbackId + "' has been modified.");
        // Assume notification always succeeds for this simulation.
    }
}