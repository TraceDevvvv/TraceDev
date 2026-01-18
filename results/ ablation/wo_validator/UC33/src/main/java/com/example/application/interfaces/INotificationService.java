package com.example.application.interfaces;

/**
 * Abstract notification service.
 */
public interface INotificationService {
    void notifySuccess(String message);
    void notifyError(String message);
}