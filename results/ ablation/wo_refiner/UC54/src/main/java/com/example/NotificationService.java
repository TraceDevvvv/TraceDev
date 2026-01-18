package com.example;

/**
 * Service for sending notifications.
 */
public class NotificationService {
    /**
     * Notifies that a comment has been updated.
     */
    public void notifyCommentUpdated(Comment comment) {
        System.out.println("NotificationService: Comment updated (ID: " + comment.getId() + ")");
        // In a real system, this would send an email/push notification.
        // Send Ack per m10
        sendAck();
    }

    /**
     * Sends acknowledgment as per sequence diagram message m10.
     */
    public void sendAck() {
        System.out.println("NotificationService: Ack sent.");
    }
}