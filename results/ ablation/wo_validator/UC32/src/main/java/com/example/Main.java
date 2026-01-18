package com.example;

import com.example.actor.User;
import com.example.command.PasswordConfirmationCommand;
import com.example.interactor.PasswordChangeInteractor;
import com.example.viewmodel.PasswordViewModel;
import com.example.boundary.PasswordBoundary;
import com.example.validator.PasswordValidator;
import com.example.builder.ErrorMessageBuilder;
import com.example.service.NotificationService;

/**
 * Main class to demonstrate the password change flow.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize components
        PasswordViewModel viewModel = new PasswordViewModel();
        PasswordValidator validator = new PasswordValidator();
        ErrorMessageBuilder errorBuilder = new ErrorMessageBuilder();
        NotificationService notificationService = new NotificationService();
        
        PasswordChangeInteractor interactor = new PasswordChangeInteractor(
                viewModel, validator, errorBuilder
        );
        
        PasswordBoundary boundary = new PasswordBoundary(
                interactor, viewModel, notificationService
        );
        
        User user = new User();
        
        // Scenario 1: Password confirmation mismatch (from sequence diagram)
        System.out.println("=== Scenario 1: Password confirmation mismatch ===");
        PasswordConfirmationCommand mismatchCommand = new PasswordConfirmationCommand(
                "oldPassword123",
                "newPassword456",
                "differentPassword789" // Mismatch
        );
        boundary.onPasswordConfirmationPressed(mismatchCommand);
        
        // Simulate user reading error and going back to change password
        notificationService.dismiss();
        boundary.navigateBack();
        
        // Scenario 2: Valid password change
        System.out.println("\n=== Scenario 2: Valid password change ===");
        PasswordConfirmationCommand validCommand = new PasswordConfirmationCommand(
                "oldPassword123",
                "newPassword456",
                "newPassword456" // Matching
        );
        boundary.onPasswordConfirmationPressed(validCommand);
        
        System.out.println("\nPassword change process completed.");
    }
}