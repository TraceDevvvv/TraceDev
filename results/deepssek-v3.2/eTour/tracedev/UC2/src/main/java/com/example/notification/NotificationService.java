package com.example.notification;

import java.util.UUID;

/**
 * Interface for notification serv.
 * Added to satisfy requirement: Exit Conditions: The system shall notify proper inclusion
 */
public interface NotificationService {
    void notifySuccess(String message);
    void notifyError(String message);
    void notifyInclusion(UUID culturalGoodId);
}