package com.system.pac;

import com.system.adapter.UserInterfaceAdapter;
import com.system.application.NotificationContext;
import com.system.domain.NotificationType;
import com.system.observer.Observer;

/**
 * Presentation component in PAC pattern, handles UI display.
 */
public class PAC_Presentation implements Observer {
    private PAC_Control control;
    private UserInterfaceAdapter notificationAdapter;

    public PAC_Presentation(PAC_Control control, UserInterfaceAdapter notificationAdapter) {
        this.control = control;
        this.notificationAdapter = notificationAdapter;
        if (control != null) {
            control.setPresentation(this);
        }
    }

    /**
     * Shows error message to user.
     */
    public void showError(String message) {
        System.out.println("[ERROR] " + message);
        // In real UI: display error dialog
    }

    /**
     * Shows success message to user.
     */
    public void showSuccess(String message) {
        System.out.println("[SUCCESS] " + message);
        // In real UI: display success notification
    }

    /**
     * Shows password change screen.
     */
    public void showPasswordChangeScreen() {
        System.out.println("[UI] Displaying password change screen.");
        // In real UI: navigate to password change interface
    }

    /**
     * Displays password change interface again.
     */
    public void displayPasswordChangeInterfaceAgain() {
        System.out.println("[UI] Displaying password change interface again.");
        showPasswordChangeScreen();
    }

    /**
     * Navigates to success screen.
     */
    public void navigateToSuccessScreen() {
        System.out.println("[UI] Navigating to success screen.");
        // In real UI: perform navigation
    }

    /**
     * Observer update method, called when notification context changes.
     */
    @Override
    public void update(NotificationContext context) {
        if (context.getType() == NotificationType.ERROR) {
            showError(context.getMessage());
        } else if (context.getType() == NotificationType.SUCCESS) {
            showSuccess(context.getMessage());
            navigateToSuccessScreen();
        }
    }
}