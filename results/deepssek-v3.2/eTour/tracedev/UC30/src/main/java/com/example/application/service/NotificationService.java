package com.example.application.service;

import com.example.application.entity.TagEntity;

/**
 * Service for sending notifications.
 */
public class NotificationService {
    public void sendInclusionNotification(String tag) {
        System.out.println("Notification: Tag '" + tag + "' has been included.");
    }
}