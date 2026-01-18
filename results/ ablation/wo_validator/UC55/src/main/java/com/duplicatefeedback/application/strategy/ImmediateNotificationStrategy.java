package com.duplicatefeedback.application.strategy;

import com.duplicatefeedback.application.ports.out.NotificationService;

/**
 * Concrete strategy that sends notifications immediately using a notification service.
 */
public class ImmediateNotificationStrategy implements UserNotificationStrategy {
    private final NotificationService notificationService;

    public ImmediateNotificationStrategy(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @Override
    public void notifyUser(String message) {
        // This method might need a userId; assuming it uses a default or context.
        // For simplicity, we call deliverNotification with a placeholder.
        // In a real scenario, the userId would be passed from the context.
        // We'll delegate to deliverNotification with a placeholder.
        // This is an assumption; the UML does not specify.
        deliverNotification("default-user-id", message);
    }

    @Override
    public void deliverNotification(String userId, String message) {
        // Delegate to the external notification service
        notificationService.sendNotification(userId, message);
    }
}