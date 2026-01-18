package com.etour.service;

import com.etour.domain.Comment;

/**
 * Service for sending notifications about comment alterations.
 */
public interface NotificationService {
    /**
     * Notifies that a comment has been altered (refined for REQ-EXIT-001).
     * @param comment the altered comment.
     * @param touristId the ID of the tourist who made the alteration.
     */
    void notifyCommentAltered(Comment comment, String touristId);

    /**
     * Notifies that a comment has been updated.
     * @param comment the updated comment.
     * @param touristId the ID of the tourist who updated the comment.
     */
    void notifyCommentUpdated(Comment comment, String touristId);
}