package com.example.editabsence;

/**
 * Provides functionality for sending email notifications.
 * In a real application, this would integrate with an email service provider.
 * For this simulation, it simply prints the email details to the console.
 */
public class NotificationService {

    /**
     * Sends an email notification to a specified recipient.
     * This method simulates the process of sending an email.
     *
     * @param recipientEmail The email address of the recipient (e.g., parent's email).
     * @param subject        The subject line of the email.
     * @param body           The main content of the email.
     */
    public void sendEmail(String recipientEmail, String subject, String body) {
        if (recipientEmail == null || recipientEmail.trim().isEmpty()) {
            System.err.println("Error: Cannot send email, recipient email is null or empty.");
            return;
        }
        if (subject == null || subject.trim().isEmpty()) {
            System.err.println("Warning: Sending email with empty subject to " + recipientEmail);
        }
        if (body == null || body.trim().isEmpty()) {
            System.err.println("Warning: Sending email with empty body to " + recipientEmail);
        }

        System.out.println("--- Sending Email Notification ---");
        System.out.println("To: " + recipientEmail);
        System.out.println("Subject: " + subject);
        System.out.println("Body:\n" + body);
        System.out.println("----------------------------------");
        // In a real application, this would involve calling an external email API
        // e.g., JavaMail API, SendGrid, AWS SES, etc.
    }
}