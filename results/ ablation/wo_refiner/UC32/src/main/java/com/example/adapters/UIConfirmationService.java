package com.example.adapters;

import com.example.interfaces.NotificationService;
import com.example.ui.ModifyPasswordView;

/**
 * UI implementation of NotificationService that interacts with the view.
 */
public class UIConfirmationService implements NotificationService {
    
    private ModifyPasswordView view;
    private boolean acknowledged = false;
    
    public UIConfirmationService(ModifyPasswordView view) {
        this.view = view;
    }
    
    @Override
    public void showError(String message) {
        view.displayErrorMessage(message);
        // Reset acknowledgment flag when new error is shown
        acknowledged = false;
    }
    
    @Override
    public boolean isAcknowledged() {
        // This would typically check with the view if user has acknowledged
        // For simplicity, we return the stored flag
        return acknowledged;
    }
    
    /**
     * Method to be called by the view when user acknowledges the error.
     */
    public void setAcknowledged(boolean acknowledged) {
        this.acknowledged = acknowledged;
    }
}