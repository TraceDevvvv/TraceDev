package com.example.infrastructure;

import com.example.application.NotificationService;

/**
 * Implementation of NotificationService (requirement 12).
 */
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void notifySuccess(String touristId, String siteId) {
        // Simulate sending a notification (e.g., email, push, log)
        System.out.println("Notification: Tourist " + touristId + " successfully submitted feedback for site " + siteId);
    }
}