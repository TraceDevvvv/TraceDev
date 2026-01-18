package com.example;

import com.example.core.PasswordValidationService;
import com.example.ui.ModifyPasswordView;
import com.example.controllers.PasswordModificationController;
import com.example.usecases.HandlePasswordModificationUseCase;
import com.example.usecases.NotifyPasswordErrorUseCase;
import com.example.adapters.UIConfirmationService;

/**
 * Main application class to demonstrate the flow.
 */
public class Main {
    
    public static void main(String[] args) {
        // Create serv
        PasswordValidationService validationService = new PasswordValidationService();
        
        // Create view first (circular dependency resolved via constructor)
        ModifyPasswordView view = null;
        
        // Create UI confirmation service (will be linked to view)
        UIConfirmationService confirmationService = new UIConfirmationService(null);
        
        // Create use cases
        NotifyPasswordErrorUseCase notifyErrorUseCase = new NotifyPasswordErrorUseCase(confirmationService);
        HandlePasswordModificationUseCase handleUseCase = 
            new HandlePasswordModificationUseCase(validationService, notifyErrorUseCase);
        
        // Create controller
        PasswordModificationController controller = new PasswordModificationController(handleUseCase);
        
        // Now create view with controller and service
        view = new ModifyPasswordView(controller, confirmationService);
        confirmationService = new UIConfirmationService(view); // Recreate with proper view reference
        
        // Update the notification service in the use case
        notifyErrorUseCase = new NotifyPasswordErrorUseCase(confirmationService);
        handleUseCase = new HandlePasswordModificationUseCase(validationService, notifyErrorUseCase);
        controller = new PasswordModificationController(handleUseCase);
        
        // Final view setup
        view = new ModifyPasswordView(controller, confirmationService);
        
        System.out.println("=== Password Modification System ===");
        System.out.println("1. Displaying password form...");
        view.displayPasswordForm();
        
        System.out.println("\n2. User presses confirm button...");
        view.onConfirmButtonPressed();
        
        System.out.println("\n3. Simulating user acknowledgment...");
        view.simulateUserAcknowledgment();
        
        System.out.println("\n=== Flow Completed ===");
    }
}