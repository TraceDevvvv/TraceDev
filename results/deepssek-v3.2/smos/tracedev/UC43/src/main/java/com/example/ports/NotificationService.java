package com.example.ports;

public interface NotificationService {
    boolean sendNotificationToParents(String studentId, String message);
}