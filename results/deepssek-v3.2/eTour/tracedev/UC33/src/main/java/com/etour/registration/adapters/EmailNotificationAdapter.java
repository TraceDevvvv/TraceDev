package com.etour.registration.adapters;

import com.etour.registration.ports.NotificationPort;

/**
 * Adapter for sending email notifications.
 */
public class EmailNotificationAdapter implements NotificationPort {
    private Object mailClient; // Simulated mail client

    public EmailNotificationAdapter(Object mailClient) {
        this.mailClient = mailClient;
    }

    @Override
    public boolean sendConfirmation(String accountId, String email) {
        try {
            // Simulate sending email
            System.out.println("Sending confirmation email to " + email + " for account " + accountId);
            return true;
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
            return false;
        }
    }

    // New method for sequence diagram traceability
    public void notifySuccessfulOperation() {
        System.out.println("System notifies successful operation: Account created and confirmation email sent.");
    }
}