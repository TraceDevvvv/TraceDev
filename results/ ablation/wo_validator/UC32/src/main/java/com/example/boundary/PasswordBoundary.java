package com.example.boundary;

import com.example.command.PasswordConfirmationCommand;
import com.example.interactor.PasswordChangeInteractor;
import com.example.viewmodel.PasswordViewModel;
import com.example.service.NotificationService;

/**
 * Boundary handling user interactions for password change.
 */
public class PasswordBoundary {
    private PasswordChangeInteractor interactor;
    private PasswordViewModel viewModel;
    private NotificationService notificationService;

    public PasswordBoundary(PasswordChangeInteractor interactor, 
                           PasswordViewModel viewModel, 
                           NotificationService notificationService) {
        this.interactor = interactor;
        this.viewModel = viewModel;
        this.notificationService = notificationService;
    }

    /**
     * Called when user confirms password change.
     * @param command the password confirmation command
     */
    public void onPasswordConfirmationPressed(PasswordConfirmationCommand command) {
        interactor.execute(command);
        
        // Check if there was an error
        if (viewModel.getErrorMessage() != null) {
            notificationService.showError(viewModel.getErrorMessage());
            // Process terminates here as per sequence diagram
            return;
        }
        
        // If successful, display success notification
        if (viewModel.getIsValid()) {
            displayNotification("Password changed successfully!");
        }
    }

    /**
     * Display generic notification.
     * @param message the notification message
     */
    public void displayNotification(String message) {
        System.out.println("Notification: " + message);
    }

    /**
     * Navigate back to password change screen.
     * Assumption: This method is called when user wants to correct passwords.
     */
    public void navigateBack() {
        String errorMessage = viewModel.getErrorMessage();
        if (errorMessage != null) {
            System.out.println("Previous error: " + errorMessage);
            // User can now correct passwords based on this error message
        }
    }
}