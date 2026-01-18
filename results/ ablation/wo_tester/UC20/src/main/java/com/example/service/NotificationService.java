package com.example.service;

import com.example.entity.AgencyOperator;

/**
 * Interface for notification service.
 */
public interface NotificationService {
    /**
     * Sends success notification.
     */
    void sendSuccessNotification(AgencyOperator operator);
    
    /**
     * Sends error notification.
     */
    void sendErrorNotification(AgencyOperator operator, String message);
    
    /**
     * Sends notification.
     */
    void sendNotification(AgencyOperator operator, String message);
}