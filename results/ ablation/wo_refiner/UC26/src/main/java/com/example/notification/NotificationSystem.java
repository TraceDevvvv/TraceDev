package com.example.notification;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles notification queuing and delivery.
 */
public class NotificationSystem {
    private List<String> notificationQueue;

    public NotificationSystem() {
        this.notificationQueue = new ArrayList<>();
    }

    public void notifyCommentModified(int commentId) {
        String message = "Comment " + commentId + " has been modified.";
        notificationQueue.add(message);
    }

    public List<String> getPendingNotifications() {
        return new ArrayList<>(notificationQueue);
    }

    public void clearNotifications() {
        notificationQueue.clear();
    }
}