package com.culturalheritage.application.service;

/**
 * Service to send notifications to operators.
 */
public interface NotificationService {
    void notifySuccess(String message, String recipient);
    void notifyFailure(String message, String recipient);
}