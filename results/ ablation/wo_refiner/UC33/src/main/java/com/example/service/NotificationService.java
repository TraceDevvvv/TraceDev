package com.example.service;

/**
 * Service for sending notifications.
 */
public class NotificationService {
    
    /**
     * Sends registration success notification.
     * @param email the user's email
     * @param username the username
     */
    public void sendRegistrationSuccessNotification(String email, String username) {
        // In a real implementation, this would send an email
        System.out.println("Notification sent to: " + email + 
            " for user: " + username + " - Registration successful");
    }
}