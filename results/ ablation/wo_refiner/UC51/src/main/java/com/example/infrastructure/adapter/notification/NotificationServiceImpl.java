package com.example.infrastructure.adapter.notification;

import com.example.usecase.interfaces.INotificationService;

/**
 * Infrastructure Adapter: Implements notification service port
 * <<Usability>>: Provides user feedback
 */
public class NotificationServiceImpl implements INotificationService {
    // Simulate messaging gateway
    private Object messagingGateway;

    public NotificationServiceImpl() {
        // In real implementation, initialize messaging gateway
        this.messagingGateway = new Object();
    }

    @Override
    public void notifyInsertion(String siteName) {
        // Simulate sending notification
        System.out.println("Notification: Site '" + siteName + "' has been added to bookmarks");
        // In real implementation, this might send email, push notification, etc.
    }
}