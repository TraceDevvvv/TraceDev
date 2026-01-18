package com.system;

import com.system.adapter.PasswordChangeAdapter;
import com.system.adapter.UserInterfaceAdapter;
import com.system.application.PasswordChangeService;
import com.system.controller.FrontController;
import com.system.domain.PasswordEntity;
import com.system.dto.ChangeRequest;
import com.system.dto.ChangeResponse;
import com.system.pac.PAC_Control;
import com.system.pac.PAC_Presentation;

/**
 * Main class demonstrating the password change flow with confirmation error scenario.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Password Change System Startup ===");
        
        // Setup domain objects
        PasswordEntity entity = new PasswordEntity("user123", "HASHED_oldPass123");
        PasswordChangeService service = new PasswordChangeService(entity);
        
        // Setup adapters
        UserInterfaceAdapter uiAdapter = new UserInterfaceAdapter();
        PasswordChangeAdapter changeAdapter = new PasswordChangeAdapter(service, uiAdapter);
        
        // Setup controllers and PAC components
        FrontController frontController = new FrontController(changeAdapter);
        PAC_Control control = new PAC_Control(frontController);
        PAC_Presentation presentation = new PAC_Presentation(control, uiAdapter);
        
        // Register presentation as observer
        frontController.registerObserver(presentation);
        
        // Simulate password change with confirmation mismatch (error scenario)
        System.out.println("\n--- Scenario 1: Confirmation Mismatch ---");
        ChangeResponse response1 = control.onPasswordConfirmPressed(
            "user123",
            "oldPass123",           // current password
            "newPass456",           // new password
            "differentConfirmation" // mismatched confirmation
        );
        System.out.println("Response result: " + response1.result);
        System.out.println("Response message: " + response1.message);
        
        // Simulate user clicking OK on error dialog
        System.out.println("\nUser clicks OK on error dialog...");
        control.onErrorConfirmed();
        presentation.showPasswordChangeScreen();
        
        // Simulate successful password change
        System.out.println("\n--- Scenario 2: Successful Password Change ---");
        ChangeResponse response2 = control.onPasswordConfirmPressed(
            "user123",
            "oldPass123",           // current password
            "newStrongPass123",     // new password (>= 8 chars)
            "newStrongPass123"      // matching confirmation
        );
        System.out.println("Response result: " + response2.result);
        System.out.println("Response message: " + response2.message);
        
        System.out.println("\n=== System Shutdown ===");
    }
}