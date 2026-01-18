package com.example.infrastructure.email;

import com.example.application.ports.EmailService;

/**
 * Implementation of EmailService using SMTP.
 */
public class EmailServiceImpl implements EmailService {
    private SmtpConfig smtpConfig; // configuration class assumed

    public EmailServiceImpl(SmtpConfig smtpConfig) {
        this.smtpConfig = smtpConfig;
    }

    @Override
    public boolean sendEmail(String to, String subject, String body) {
        // Simulate sending email via SMTP server
        System.out.println("EmailServiceImpl: Sending email to " + to + " with subject: " + subject);
        // Assume success for illustration
        return true;
    }

    // New method for sequence diagram message
    public void sendNotificationEmail(String to, String subject, String body) {
        System.out.println("EmailServiceImpl: Send notification email to " + to);
        sendEmail(to, subject, body);
    }
}

// Supporting config class (not in diagram, but required for compilation)
class SmtpConfig {
    // SMTP configuration properties
}