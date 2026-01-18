package com.example.usecases;

import com.example.core.PasswordValidationService;
import com.example.core.ValidationResult;
import com.example.commands.ErrorNotificationCommand;
import com.example.interfaces.NotificationService;

/**
 * Use case for handling password modification requests.
 */
public class HandlePasswordModificationUseCase {
    
    private PasswordValidationService validationService;
    private NotifyPasswordErrorUseCase notifyErrorUseCase;
    
    public HandlePasswordModificationUseCase(PasswordValidationService validationService, 
                                             NotifyPasswordErrorUseCase notifyErrorUseCase) {
        this.validationService = validationService;
        this.notifyErrorUseCase = notifyErrorUseCase;
    }
    
    /**
     * Handles the password modification request.
     * @param providedPassword the provided password
     * @param confirmedPassword the confirmed password
     */
    public void handleModificationRequest(String providedPassword, String confirmedPassword) {
        ValidationResult result = validationService.validatePasswordMatch(providedPassword, confirmedPassword);
        
        if (result == ValidationResult.PASSWORDS_DO_NOT_MATCH || result == ValidationResult.PASSWORD_EMPTY) {
            // Create command as per sequence diagram
            NotificationService notificationService = notifyErrorUseCase.getNotificationService();
            String errorMessage = result == ValidationResult.PASSWORDS_DO_NOT_MATCH ? 
                                  "Password confirmation failed." : "Password cannot be empty.";
            ErrorNotificationCommand command = new ErrorNotificationCommand(notificationService, errorMessage);
            command.execute();
        }
        // If passwords match, proceed normally (not shown in diagram)
    }
    
    /**
     * Handles request - additional method to match class diagram
     * @param request the request string
     */
    public void handleRequest(String request) {
        // This is a general request handler, though the primary method is handleModificationRequest
        // For compatibility with abstract class requirement
    }
}