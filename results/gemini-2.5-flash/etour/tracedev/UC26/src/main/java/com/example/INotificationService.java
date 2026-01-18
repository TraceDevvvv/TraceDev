package com.example;

/**
 * Interface for the Notification Service.
 * This service is responsible for sending notifications related to feedback modifications.
 * (Added to satisfy Exit Condition: The notification system has been modified REQ-003)
 */
public interface INotificationService {
    /**
     * Notifies about a modified comment for a given feedback ID.
     * @param feedbackId The ID of the feedback whose comment was modified.
     */
    void notifyCommentModified(String feedbackId);
}