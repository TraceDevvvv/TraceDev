package com.school.infrastructure;

/**
 * Concrete notification service using SMTP.
 */
public class EmailNotificationService implements NotificationService {
    private SmtpClient smtpClient;

    public EmailNotificationService(SmtpClient smtpClient) {
        this.smtpClient = smtpClient;
    }

    @Override
    public void sendEmail(String to, String subject, String body) {
        EmailMessage message = new EmailMessage(to, subject, body);
        smtpClient.send(message);
    }
}