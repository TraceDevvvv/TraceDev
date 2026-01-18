package com.duplicatefeedback.adapter.notification;

import com.duplicatefeedback.application.ports.out.NotificationService;

/**
 * Concrete implementation of NotificationService.
 * Simulates sending notifications via a message queue.
 */
public class NotificationServiceImpl implements NotificationService {
    // Simulated message queue
    private final Object messageQueue;

    public NotificationServiceImpl() {
        this.messageQueue = new Object(); // placeholder
    }

    @Override
    public boolean sendNotification(String userId, String message) {
        // In a real implementation, this would enqueue a message to a queue (e.g., RabbitMQ, Kafka)
        System.out.println("Notification sent to user " + userId + ": " + message);
        // Assume always successful for simulation
        return true;
    }
}