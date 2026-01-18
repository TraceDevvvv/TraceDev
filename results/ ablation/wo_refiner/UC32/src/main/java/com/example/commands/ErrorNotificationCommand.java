package com.example.commands;

import com.example.interfaces.NotificationService;

/**
 * Command for error notification.
 */
public class ErrorNotificationCommand {
    
    private NotificationService notificationService;
    private String errorMessage;
    
    public ErrorNotificationCommand(NotificationService notificationService, String errorMessage) {
        this.notificationService = notificationService;
        this.errorMessage = errorMessage;
    }
    
    /**
     * Executes the command by showing the error message.
     */
    public void execute() {
        notificationService.showError(errorMessage);
    }
    
    /**
     * Constructor matching sequence diagram - new ErrorNotificationCommand(NotificationService, "Error Details")
     */
    public static ErrorNotificationCommand new_ErrorNotificationCommand(NotificationService notificationService, String errorMessage) {
        return new ErrorNotificationCommand(notificationService, errorMessage);
    }
}