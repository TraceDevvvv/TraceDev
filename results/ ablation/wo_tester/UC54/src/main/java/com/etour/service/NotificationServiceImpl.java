package com.etour.service;

import com.etour.domain.Comment;

/**
 * Implementation of NotificationService.
 */
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void notifyCommentAltered(Comment comment, String touristId) {
        // In a real system, this would send an email, push notification, etc.
        System.out.println("Notification: Comment " + comment.getId() +
                " altered by tourist " + touristId);
    }

    @Override
    public void notifyCommentUpdated(Comment comment, String touristId) {
        // Call the existing method to preserve logic, or implement distinct behavior.
        notifyCommentAltered(comment, touristId);
    }
}