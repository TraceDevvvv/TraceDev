package com.example.convention;

/**
 * NotificationAdapter implementation that uses email (and notifies ETOUR server).
 */
public class EmailNotificationAdapter implements NotificationAdapter {
    private ETOURServerAdapter etourServerAdapter;

    public EmailNotificationAdapter(ETOURServerAdapter etourServerAdapter) {
        this.etourServerAdapter = etourServerAdapter;
    }

    @Override
    public void sendActivationNotification(String conventionId) {
        // In a real implementation, this would send an email.
        System.out.println("Sending activation email for convention: " + conventionId);

        // Also notify the ETOUR server as per sequence diagram.
        etourServerAdapter.notifyActivation(conventionId);
        // According to the sequence diagram, connection interruption may happen after notification.
        // This is a condition we cannot control in code, so we just note it.
    }
}