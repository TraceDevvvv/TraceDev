package com.example.service;

/**
 * Service for sending notifications.
 */
public class NotificationService {
    /**
     * Notifies that a comment has changed.
     * @param feedbackId the id of the feedback
     */
    public void notifyCommentChanged(String feedbackId) {
        System.out.println("Notification: Comment changed for feedback " + feedbackId);
    }
}