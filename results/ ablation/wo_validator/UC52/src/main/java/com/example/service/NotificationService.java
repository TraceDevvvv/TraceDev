package com.example.service;

/**
 * Service for sending notifications.
 */
public class NotificationService {
    
    /**
     * Sends a notification when a bookmark is removed.
     * 
     * @param userId The ID of the user
     * @param siteId The ID of the site
     */
    public void sendRemovalNotification(String userId, String siteId) {
        // In a real implementation, this would send an email, push notification, etc.
        System.out.println("Notification: Bookmark for site " + siteId + 
                         " has been removed for user " + userId);
        // Simulate notification sending logic
    }
}