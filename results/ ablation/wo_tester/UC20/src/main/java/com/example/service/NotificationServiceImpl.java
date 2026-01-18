package com.example.service;

import com.example.entity.AgencyOperator;

/**
 * Implementation of notification service.
 */
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void sendSuccessNotification(AgencyOperator operator) {
        System.out.println("Success notification sent to operator: " + (operator != null ? operator.getName() : "unknown"));
    }

    @Override
    public void sendErrorNotification(AgencyOperator operator, String message) {
        System.out.println("Error notification sent to operator: " + (operator != null ? operator.getName() : "unknown") + " - " + message);
    }

    @Override
    public void sendNotification(AgencyOperator operator, String message)