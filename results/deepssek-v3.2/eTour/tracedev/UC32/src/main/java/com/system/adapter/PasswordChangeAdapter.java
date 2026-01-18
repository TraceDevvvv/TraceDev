package com.system.adapter;

import com.system.application.PasswordChangeService;
import com.system.application.NotificationContext;
import com.system.domain.NotificationType;
import com.system.domain.ValidationResult;
import com.system.dto.ChangeRequest;
import com.system.dto.ChangeResponse;
import com.system.ports.PasswordChangeInputPort;
import com.system.ports.NotificationOutputPort;

/**
 * Adapter implementing the input port, coordinating between core service and output ports.
 */
public class PasswordChangeAdapter implements PasswordChangeInputPort {
    private PasswordChangeService service;
    private NotificationOutputPort notificationPort;

    public PasswordChangeAdapter(PasswordChangeService service, 
                                 NotificationOutputPort notificationPort) {
        this.service = service;
        this.notificationPort = notificationPort;
    }

    @Override
    public ChangeResponse handlePasswordChange(ChangeRequest request) {
        // Get current hash from the entity (simplified: assume we have it)
        String currentHash = service.getCurrentPasswordHash();
        
        // Call service to validate and change
        ValidationResult result = service.validateAndChange(
            currentHash, 
            request.newPasswordPlain, 
            request.confirmationPlain
        );

        // Prepare response
        String message = getMessageForResult(result);
        ChangeResponse response = new ChangeResponse(result, message);

        // Handle error notifications via output port
        if (result != ValidationResult.SUCCESS) {
            NotificationContext context = new NotificationContext(message, NotificationType.ERROR);
            notificationPort.notify(context);
        } else {
            // Success notification
            NotificationContext context = new NotificationContext(message, NotificationType.SUCCESS);
            notificationPort.notify(context);
        }

        return response;
    }

    private String getMessageForResult(ValidationResult result) {
        switch (result) {
            case CONFIRMATION_MISMATCH:
                return "Passwords do not match. Please try again.";
            case WEAK_PASSWORD:
                return "Password is too weak. Must be at least 8 characters.";
            case SAME_AS_CURRENT:
                return "New password cannot be the same as current password.";
            case SUCCESS:
                return "Password updated successfully.";
            default:
                return "Unknown error occurred.";
        }
    }
}
