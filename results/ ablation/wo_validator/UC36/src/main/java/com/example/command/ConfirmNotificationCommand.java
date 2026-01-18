package com.example.command;

import com.example.service.NotificationService;

/**
 * Command to show a confirmation dialog to the user.
 */
public class ConfirmNotificationCommand extends NotificationCommand {
    private NotificationService notificationService;
    private String message;
    private boolean confirmed;

    public ConfirmNotificationCommand(NotificationService notificationService, String message) {
        this.notificationService = notificationService;
        this.message = message;
        this.confirmed = false;
    }

    @Override
    public void execute() {
        // Execute the command by showing the confirmation dialog.
        confirmed = notificationService.showConfirmationDialog(message);
    }

    @Override
    public void undo() {
        // Undo operation: reset the confirmation state.
        // In a real application, we might need to revert any state changes.
        System.out.println("Undo ConfirmNotificationCommand: confirmation was " + (confirmed ? "true" : "false"));
        confirmed = false;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}