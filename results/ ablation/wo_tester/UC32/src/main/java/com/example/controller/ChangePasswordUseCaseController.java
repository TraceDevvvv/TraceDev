package com.example.controller;

import com.example.validation.PasswordValidationStrategy;
import com.example.error.ErrorNotificationSubject;
import com.example.service.PasswordChangeService;
import com.example.repository.UserRepository;
import com.example.util.ErrorMessages;

/**
 * Controller for the Change Password use case.
 * Orchestrates the flow as per the sequence diagram.
 */
public class ChangePasswordUseCaseController {
    private PasswordValidationStrategy validationStrategy;
    private ErrorNotificationSubject errorSubject;
    private PasswordChangeService passwordChangeService;
    private UserRepository userRepository;

    public ChangePasswordUseCaseController(PasswordValidationStrategy validationStrategy,
                                           ErrorNotificationSubject errorSubject,
                                           PasswordChangeService passwordChangeService,
                                           UserRepository userRepository) {
        this.validationStrategy = validationStrategy;
        this.errorSubject = errorSubject;
        this.passwordChangeService = passwordChangeService;
        this.userRepository = userRepository;
    }

    /**
     * Simulates the user pressing the password change button.
     * Entry condition: button has been pressed.
     */
    public void pressPasswordChangeButton() {
        System.out.println("Password change button pressed. Ready for input.");
    }

    /**
     * Handles the password change request.
     * @param userId the ID of the user changing the password
     * @param newPassword the new password
     * @param confirmation the password confirmation
     */
    public void handlePasswordChange(String userId, String newPassword, String confirmation) {
        System.out.println("Handling password change for user: " + userId);
        boolean isValid = validatePassword(newPassword, confirmation);
        if (!isValid) {
            // Validation failed: notify about error.
            errorSubject.setErrorMessage(ErrorMessages.PASSWORD_MISMATCH);
            errorSubject.notifyObservers();
            resetState();
        } else {
            // Validation succeeded: delegate to service.
            boolean success = passwordChangeService.changePassword(userId, newPassword);
            if (success) {
                System.out.println("Password changed successfully for user: " + userId);
                // System is ready for password change
                systemReadyForPasswordChange();
            } else {
                System.out.println("Failed to change password for user: " + userId);
            }
        }
    }

    /**
     * Validates the new password and confirmation using the strategy.
     * @param newPassword the new password
     * @param confirmation the password confirmation
     * @return true if validation passes, false otherwise
     */
    private boolean validatePassword(String newPassword, String confirmation) {
        return validationStrategy.validate(newPassword, confirmation);
    }

    /**
     * Resets the controller state after an error.
     * Called after error notification in the sequence diagram.
     */
    public void resetState() {
        System.out.println("Controller state reset. Ready for retry.");
    }

    /**
     * Note: System is ready for password change.
     * Corresponds to sequence diagram note "System is ready for password change".
     */
    public void systemReadyForPasswordChange() {
        System.out.println("Note: System is ready for password change.");
    }
}