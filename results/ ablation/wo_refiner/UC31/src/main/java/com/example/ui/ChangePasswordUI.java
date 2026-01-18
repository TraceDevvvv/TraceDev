package com.example.ui;

import com.example.controller.ModifyPasswordController;
import com.example.dto.ModifyPasswordRequest;
import com.example.dto.ModifyPasswordResponse;

/**
 * UI Boundary for password change interface.
 * Traceability: Satisfies R5, R6, R7 (UI boundary for password change)
 */
public class ChangePasswordUI {
    private ModifyPasswordController controller;
    
    public ChangePasswordUI(ModifyPasswordController controller) {
        this.controller = controller;
    }
    
    /**
     * Called when the change password button is clicked.
     * Implements the UI flow from the sequence diagram.
     */
    public void onChangePasswordButtonClicked() {
        // Simulate user input (in real UI, these would come from form fields)
        String agencyId = "agency123";
        String currentPassword = "oldPassword123";
        String newPassword = "newPassword456";
        String confirmPassword = "newPassword456";
        
        // Step 1-2: Create request from user input
        ModifyPasswordRequest request = new ModifyPasswordRequest(
            agencyId, currentPassword, newPassword, confirmPassword
        );
        
        // Step 5-10: Submit to controller
        ModifyPasswordResponse response = submit(request);
        
        // Step 11: Display result
        if (response.isSuccess()) {
            displayResponse(response);
        } else {
            displayErrorMessage(response.getMessage());
        }
    }
    
    /**
     * Press "Change Password" button - corresponds to message m3 in sequence diagram.
     * This method is called by AgencyOperator (AO) to initiate the password change.
     */
    public void pressChangePasswordButton() {
        // In the sequence diagram, message m3 is "5. Press \"Change Password\" button"
        // This is a synchronous message from AgencyOperator to UI.
        // We implement it as a method that calls onChangePasswordButtonClicked.
        onChangePasswordButtonClicked();
    }
    
    /**
     * Submit form - corresponds to message m30 in sequence diagram.
     * This method is called by AgencyOperator (AO) to submit the form.
     */
    public ModifyPasswordResponse submitForm(ModifyPasswordRequest request) {
        // Message m30: "Submit form" from AO to UI.
        // Delegates to the submit method.
        return submit(request);
    }
    
    /**
     * Submits the password change request to the controller.
     * @param request the password change request
     * @return the response from controller
     */
    public ModifyPasswordResponse submit(ModifyPasswordRequest request) {
        return controller.modifyPassword(request);
    }
    
    /**
     * Displays a successful response to the user.
     * @param response the successful response
     */
    public void displayResponse(ModifyPasswordResponse response) {
        System.out.println("Success: " + response.getMessage());
    }
    
    /**
     * Displays an error message to the user.
     * @param message the error message
     */
    public void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Display confirmation/error message - corresponds to message m29 in sequence diagram.
     * This is a return message from UI to AO.
     * It returns void, as it's a return of a previous call.
     * We'll implement it as a method that displays the message.
     */
    public void displayConfirmationErrorMessage(String message) {
        // This method can be used by the controller to display a message.
        // However, in the sequence diagram, m29 is a return from UI to AO.
        // In our implementation, the UI displays the message directly.
        // We'll keep this method for traceability.
        if (message != null) {
            System.out.println("UI Message: " + message);
        }
    }
}