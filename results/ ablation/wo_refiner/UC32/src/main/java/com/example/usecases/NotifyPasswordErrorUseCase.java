package com.example.usecases;

import com.example.commands.ErrorNotificationCommand;
import com.example.interfaces.NotificationService;

/**
 * Use case for notifying password error.
 */
public class NotifyPasswordErrorUseCase {
    
    private NotificationService notificationService;
    
    public NotifyPasswordErrorUseCase(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
    
    /**
     * Executes the error notification.
     */
    public void execute() {
        // This method is called from HandlePasswordModificationUseCase when error occurs.
        // The actual notification is performed via ErrorNotificationCommand.
        // This method is kept for compatibility with the class diagram.
    }
    
    public NotificationService getNotificationService() {
        return notificationService;
    }
    
    /**
     * Create Command - aligns with sequence diagram message m11
     */
    public ErrorNotificationCommand Create_Command(String errorDetails) {
        return new ErrorNotificationCommand(notificationService, errorDetails);
    }
}