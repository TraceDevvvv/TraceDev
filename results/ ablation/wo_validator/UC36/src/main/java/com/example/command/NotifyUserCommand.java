package com.example.command;

import com.example.service.NotificationService;

/**
 * Command to notify the user with an error message.
 */
public class NotifyUserCommand extends NotificationCommand {
    private NotificationService notificationService;
    private String message;

    public NotifyUserCommand(NotificationService notificationService, String message) {
        this.notificationService = notificationService;
        this.message = message;
    }

    @Override
    public void execute() {
        // Execute the command by showing the error message via NotificationService.
        notificationService.showErrorMessage(message);
    }

    @Override
    public void undo() {
        // Undo operation: In a real scenario, we might hide the error message.
        // For simplicity, we just print a message to the console.
        System.out.println("Undo NotifyUserCommand: error message was shown.");
    }
}