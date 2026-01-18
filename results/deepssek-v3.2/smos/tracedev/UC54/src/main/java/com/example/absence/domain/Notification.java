package com.example.absence.domain;

/**
 * Represents a notification to be sent.
 */
public class Notification {
    private String recipientEmail;
    private String subject;
    private String body;
    private NotificationStatus status;

    public Notification(String recipientEmail, String subject, String body) {
        this.recipientEmail = recipientEmail;
        this.subject = subject;
        this.body = body;
        this.status = NotificationStatus.PENDING;
    }

    /**
     * Sends the notification.
     * @return true if sent successfully, false otherwise
     */
    public boolean send() {
        // Simulate sending an email
        System.out.println("Sending email to: " + recipientEmail);
        System.out.println("Subject: " + subject);
        System.out.println("Body: " + body);
        // Assume success for demonstration
        status = NotificationStatus.SENT;
        return true;
    }

    public NotificationStatus getStatus() {
        return status;
    }

    public void setStatus(NotificationStatus status) {
        this.status = status;
    }

    public String getRecipientEmail() {
        return recipientEmail;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }
}