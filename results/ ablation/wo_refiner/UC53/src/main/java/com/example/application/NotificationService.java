package com.example.application;

/**
 * Service for sending notifications (requirement 12).
 */
public interface NotificationService {
    void notifySuccess(String touristId, String siteId);
}