package com.example.service;

import com.example.model.StudentDelay;

/**
 * Interface for sending notifications to parents.
 */
public interface INotificationService {
    void sendParentNotification(StudentDelay delay);
}