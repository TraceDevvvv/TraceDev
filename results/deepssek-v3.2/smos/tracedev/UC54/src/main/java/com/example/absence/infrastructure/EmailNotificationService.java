package com.example.absence.infrastructure;

import com.example.absence.domain.Student;
import com.example.absence.domain.Notification;
import com.example.absence.domain.NotificationStatus;
import java.util.Date;
import java.util.List;

/**
 * Notification service that sends emails via SMTP.
 */
public class EmailNotificationService implements INotificationService {
    private String smtpServer;
    private String emailTemplate;

    public EmailNotificationService(String smtpServer, String emailTemplate) {
        this.smtpServer = smtpServer;
        this.emailTemplate = emailTemplate;
    }

    @Override
    public void sendAbsenceNotification(Student student, Date absenceDate) {
        // Create a notification and send it
        String email = student.getParentEmail();
        String subject = "Absence Notification";
        String body = String.format("Dear Parent, your child %s was absent on %s.", student.getFullName(), absenceDate);
        Notification notification = new Notification(email, subject, body);
        boolean sent = notification.send();
        System.out.println("Notification sent to " + email + ": " + (sent ? "SUCCESS" : "FAILED"));
    }

    @Override
    public int sendBulkNotifications(List<Notification> notifications) {
        int successCount = 0;
        for (Notification notification : notifications) {
            if (notification.send()) {
                successCount++;
            }
        }
        return successCount;
    }

    /**
     * Prepares notifications for sending.
     * This method simulates the preparation step mentioned in sequence diagram.
     */
    public void prepareNotifications() {
        System.out.println("Preparing notifications for sending...");
        // In a real implementation, this might validate emails, apply templates, etc.
    }
}