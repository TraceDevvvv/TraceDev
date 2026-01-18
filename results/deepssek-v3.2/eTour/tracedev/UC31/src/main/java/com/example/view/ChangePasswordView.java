package com.example.view;

import com.example.session.Session;
import com.example.controller.ChangePasswordControllerImpl;
import com.example.dto.ChangePasswordRequest;
import com.example.dto.ChangePasswordResult;
import com.example.exception.NetworkException;

/**
 * UI boundary for change password. Simulates user interface interactions.
 */
public class ChangePasswordView {
    private ChangePasswordForm currentForm;
    private ChangePasswordControllerImpl controller;
    private Session session;

    public ChangePasswordView(ChangePasswordControllerImpl controller, Session session) {
        this.controller = controller;
        this.session = session;
    }

    /**
     * Renders the change password form (step 2).
     */
    public void renderForm() {
        System.out.println("=== Change Password Form ===");
        System.out.println("Please enter current password, new password, and confirmation.");
    }

    /**
     * Displays an error message.
     */
    public void displayError(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Displays a success message.
     */
    public void displaySuccess(String message) {
        System.out.println("SUCCESS: " + message);
    }

    /**
     * Submits the form (step 3-6). Simulates user submitting the form.
     */
    public ChangePasswordRequest submitForm(String agencyId) {
        // In real UI, form fields are collected from user input.
        // Here we simulate with dummy values.
        currentForm = new ChangePasswordForm("oldPass", "newPass", "newPass");
        return currentForm.toRequest(agencyId);
    }

    /**
     * Simulates detecting connection loss (exit condition).
     */
    public void detectConnectionLoss() {
        // Simulate connection loss detection
        throw new NetworkException("Connection to server lost", 1000);
    }

    /**
     * Main UI flow simulating the sequence diagram.
     */
    public void startChangePasswordFlow() {
        // Step: Validate session (entry condition)
        if (!session.isValid()) {
            displayError("User not logged in.");
            return;
        }
        String agencyId = session.getAgencyId();
        System.out.println("Agency ID from session: " + agencyId);

        // Step 1-2: User presses button, UI displays form
        renderForm();

        // Step 3: User fills and submits form (simulated)
        ChangePasswordRequest request = submitForm(agencyId);

        // Simulate potential connection loss (alternative flow)
        try {
            // Step 6: UI calls controller
            ChangePasswordResult result = controller.handleChangePassword(request);

            // Step: Display result
            if (result.isSuccess()) {
                displaySuccess(result.getMessage());
            } else {
                displayError(result.getMessage());
            }
        } catch (NetworkException e) {
            displayError("Connection lost: " + e.getDetails());
        }
    }
}