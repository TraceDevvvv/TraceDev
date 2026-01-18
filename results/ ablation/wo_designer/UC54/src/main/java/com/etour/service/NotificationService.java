package com.etour.service;

import com.etour.entity.Comment;

/**
 * Service for sending notifications.
 */
public class NotificationService {
    /**
     * Notifies that a comment has been updated.
     * @param comment the updated comment
     */
    public void notifyCommentUpdated(Comment comment) {
        // In a real application, this might send an email, push notification, or log.
        System.out.println("Notification: Comment with ID " + comment.getId() + " has been updated.");
    }
}