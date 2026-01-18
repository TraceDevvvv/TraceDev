// Utility service for showing messages and confirming read.
package com.feedback.management;

public class NotificationService {
    public void showMessage(String message) {
        // In a real system, this might show a UI dialog or log.
        System.out.println("Notification: " + message);
    }

    public boolean confirmRead() {
        // Simulate user confirming they have read the notification.
        // In a real application, this might involve user interaction.
        System.out.println("User acknowledged the notification.");
        return true;
    }
}