package com.example.service;

import com.example.model.Feedback;

/**
 * Service for sending notifications.
 */
public class NotificationService {
    /**
     * Sends a notification about the feedback.
     * @param feedback The feedback to notify about.
     */
    public void sendNotification(Feedback feedback) {
        System.out.println("NotificationService: sending notification for feedback " + feedback.getFeedbackId());
    }

    /**
     * Modifies the notification system based on updated feedback.
     * @param feedback The updated feedback.
     */
    public void modifyNotificationSystem(Feedback feedback) {
        System.out.println("NotificationService: modifying notification system for feedback " + feedback.getFeedbackId());
    }
}