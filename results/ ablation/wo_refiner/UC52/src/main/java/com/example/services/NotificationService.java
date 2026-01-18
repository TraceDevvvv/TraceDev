package com.example.serv;

/**
 * Concrete notification service.
 */
public class NotificationService implements INotificationService {
    public NotificationService() {
        // default constructor
    }

    @Override
    public void sendRemovalNotification(String touristId, String siteId) {
        System.out.println("Notification sent to tourist " + touristId + ": Site " + siteId + " removed from bookmarks.");
    }
}