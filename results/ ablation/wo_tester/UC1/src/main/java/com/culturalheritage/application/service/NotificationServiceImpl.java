package com.culturalheritage.application.service;

/**
 * Simple implementation of NotificationService.
 * In real application, would send emails, push notifications, etc.
 */
public class NotificationServiceImpl implements NotificationService {
    @Override
    public void notifySuccess(String message, String recipient) {
        System.out.println("SUCCESS NOTIFICATION to " + recipient + ": " + message);
    }

    @Override
    public void notifyFailure(String message, String recipient) {
        System.out.println("FAILURE NOTIFICATION to " + recipient + ": " + message);
    }
}