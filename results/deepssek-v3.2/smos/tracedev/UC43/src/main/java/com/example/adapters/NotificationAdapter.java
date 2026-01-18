package com.example.adapters;

import com.example.ports.NotificationService;
import com.example.external.EmailService;

public class NotificationAdapter implements NotificationService {
    private EmailService emailService;

    public NotificationAdapter(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public boolean sendNotificationToParents(String studentId, String message) {
        System.out.println("Sending notification to parents of student ID: " + studentId);
        System.out.println("Message: " + message);
        // Use email service to send notification (simulated)
        String parentEmail = "parent@example.com"; // In real scenario, fetch from student
        boolean success = emailService.sendEmail(parentEmail, "Disciplinary Note Notification", message);
        if (success) {
            System.out.println("Notification sent successfully.");
        } else {
            System.out.println("Failed to send notification.");
        }
        return success;
    }
}