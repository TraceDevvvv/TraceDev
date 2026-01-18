package com.example;

import java.util.Random;

/**
 * Adapter that implements the NotificationServicePort using an email system.
 * This is a mock implementation simulating interaction with an external email system.
 */
public class EmailNotificationAdapter implements NotificationServicePort {

    private Logger logger;
    private Random random;

    /**
     * Constructs an EmailNotificationAdapter.
     *
     * @param logger The logger instance for logging notification operations.
     */
    public EmailNotificationAdapter(Logger logger) {
        this.logger = logger;
        this.random = new Random();
    }

    /**
     * Simulates sending an email notification via an external system.
     *
     * @param recipientEmail The email address of the recipient.
     * @param message        The message content of the notification.
     * @throws NotificationFailedException if the simulated email sending fails.
     */
    @Override
    public void sendNotification(String recipientEmail, String message) throws NotificationFailedException {
        logger.info("EmailNotificationAdapter: Attempting to send email to " + recipientEmail + " with message: " + message);
        try {
            // Simulate interaction with an external email system
            simulateParentEmailSystemSendEmail(recipientEmail, "Notification from School", message);
            logger.info("EmailNotificationAdapter: Successfully sent notification to " + recipientEmail);
        } catch (ExternalSystemException e) {
            logger.error("EmailNotificationAdapter: Failed to send notification to " + recipientEmail + ": " + e.getMessage());
            // Translate the external system exception into a domain-specific NotificationFailedException
            throw new NotificationFailedException("Failed to send email notification to " + recipientEmail, e);
        }
    }

    /**
     * Mock method simulating an external parent email system.
     *
     * @param to      The recipient email address.
     * @param subject The email subject.
     * @param body    The email body.
     * @throws ExternalSystemException if a simulated failure occurs in the external system.
     */
    private void simulateParentEmailSystemSendEmail(String to, String subject, String body) throws ExternalSystemException {
        // Simulate external system network issues or service unavailability
        if (random.nextInt(100) < 10) { // 10% chance of failure
            throw new ExternalSystemException("Simulated external email system failure for " + to);
        }
        // In a real scenario, this would involve calling an actual email API
        logger.info("ParentEmailSystem: Successfully sent email to '" + to + "' with subject '" + subject + "'");
    }
}