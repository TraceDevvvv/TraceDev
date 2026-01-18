package com.example.serv;

/**
 * Interface for sending notifications.
 */
public interface INotificationService {
    void sendRemovalNotification(String touristId, String siteId);
}