package com.system.pac;

import java.util.HashMap;
import java.util.Map;

import com.system.controller.FrontController;
import com.system.application.NotificationContext;
import com.system.dto.ChangeResponse;

/**
 * Control component in PAC pattern, coordinates between presentation and abstraction.
 */
public class PAC_Control {
    private FrontController frontController;
    private PAC_Presentation presentation;

    public PAC_Control(FrontController frontController) {
        this.frontController = frontController;
    }

    public void setPresentation(PAC_Presentation presentation) {
        this.presentation = presentation;
    }

    /**
     * Called when user presses confirm on password change screen.
     */
    public ChangeResponse onPasswordConfirmPressed(String userId, String currentPassword, 
                                                   String newPassword, String confirmation) {
        Map<String, String> data = new HashMap<>();
        data.put("userId", userId);
        data.put("currentPassword", currentPassword);
        data.put("newPassword", newPassword);
        data.put("confirmation", confirmation);
        
        return frontController.handleRequest("PASSWORD_CONFIRM", data);
    }

    /**
     * Called when user confirms an error dialog.
     */
    public void onErrorConfirmed() {
        // Notify presentation to show password change screen again
        // This is handled via observer pattern in real implementation
        if (presentation != null) {
            presentation.showPasswordChangeScreen();
        }
    }

    /**
     * Navigates to success screen.
     */
    public void navigateToSuccessScreen() {
        if (presentation != null) {
            // In a real implementation, this would trigger UI navigation
            System.out.println("[PAC_Control] Navigating to success screen.");
        }
    }
}