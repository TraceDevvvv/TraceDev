package com.example.ui;

import com.example.controllers.PasswordModificationController;
import com.example.adapters.UIConfirmationService;

/**
 * View for modifying passwords.
 */
public class ModifyPasswordView {
    
    private PasswordModificationController controller;
    private UIConfirmationService confirmationService;
    
    public ModifyPasswordView(PasswordModificationController controller, UIConfirmationService confirmationService) {
        this.controller = controller;
        this.confirmationService = confirmationService;
    }
    
    /**
     * Displays the password form.
     */
    public void displayPasswordForm() {
        System.out.println("Displaying password modification form...");
    }
    
    /**
     * Called when the confirm button is pressed.
     */
    public void onConfirmButtonPressed() {
        // Simulate user input
        String providedPassword = "password123";
        String confirmedPassword = "password456"; // Mismatched for testing
        
        // Presses "Confirm Password Change" button - sequence message m1
        controller.handleRequest("modify_password", providedPassword, confirmedPassword);
    }
    
    /**
     * Displays an error message.
     * @param message the error message to display
     */
    public void displayErrorMessage(String message) {
        System.out.println("ERROR: " + message);
        // In a real application, this would update the UI
    }
    
    /**
     * Navigates back to the password form after error acknowledgment.
     */
    public void navigateBackToPasswordForm() {
        System.out.println("Navigating back to password form...");
        displayPasswordForm();
    }
    
    /**
     * Checks if the error has been acknowledged.
     * @return true if acknowledged, false otherwise
     */
    public boolean checkErrorAcknowledged() {
        // Simulate user acknowledgment
        confirmationService.setAcknowledged(true);
        return true;
    }
    
    /**
     * Simulates user acknowledging the error.
     */
    public void simulateUserAcknowledgment() {
        // Confirms reading error (e.g., clicks OK) - sequence message m20
        checkErrorAcknowledged();
        // Exit Condition: System returns control to user interaction - sequence note m23
        returnControlToUserInteraction();
        // Displays password change form again - sequence message m24
        displayPasswordForm();
    }
    
    /**
     * Method to handle pressing the confirm button - aligns with sequence diagram m1
     */
    public void Presses_Confirm_Password_Change_button() {
        this.onConfirmButtonPressed();
    }
    
    /**
     * Method to confirm reading error - aligns with sequence diagram m20
     */
    public void Confirms_reading_error() {
        simulateUserAcknowledgment();
    }
    
    /**
     * Method representing exit condition - aligns with sequence diagram m23
     */
    private void returnControlToUserInteraction() {
        System.out.println("Exit Condition: System returns control to user interaction");
    }
    
    /**
     * Method to display password form again - aligns with sequence diagram m24
     */
    public void Displays_password_change_form_again() {
        System.out.println("Displays password change form again");
        displayPasswordForm();
    }
}