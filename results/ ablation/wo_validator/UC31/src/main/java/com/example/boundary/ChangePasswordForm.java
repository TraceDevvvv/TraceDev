package com.example.boundary;

import com.example.controller.ChangePasswordController;
import com.example.dto.PasswordChangeDTO;
import com.example.value.OperationResult;
import java.util.Scanner;

/**
 * Boundary class representing the password change form.
 * Simulates user interaction via console for demonstration.
 */
public class ChangePasswordForm {
    private ChangePasswordController controller;

    public ChangePasswordForm(ChangePasswordController controller) {
        this.controller = controller;
    }

    /**
     * Displays the password change form (simulated).
     */
    public void displayForm() {
        System.out.println("=== Password Change Form ===");
        System.out.println("Please enter your details:");
    }

    /**
     * Gets password data from user input.
     * @return PasswordChangeDTO populated with user input
     */
    public PasswordChangeDTO getPasswordData() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Old Password: ");
        String oldPassword = scanner.nextLine();
        System.out.print("New Password: ");
        String newPassword = scanner.nextLine();
        System.out.print("Confirm New Password: ");
        String confirmPassword = scanner.nextLine();

        return new PasswordChangeDTO(username, oldPassword, newPassword, confirmPassword);
    }

    /**
     * Shows success message.
     */
    public void showSuccess() {
        System.out.println("SUCCESS: Password changed successfully.");
    }

    /**
     * Shows failure message.
     * @param message the error message
     */
    public void showFailure(String message) {
        System.out.println("ERROR: " + message);
    }

    /**
     * Simulates the password change process as per sequence diagram.
     * This method orchestrates the interaction between user, form, and controller.
     */
    public void runPasswordChangeProcess() {
        // Step 1: User presses change password button -> display form
        displayForm();

        // Step 2: User uploads form with password data
        PasswordChangeDTO dto = getPasswordData();

        // Step 3-5: Call controller to change password
        OperationResult result = controller.changePassword(dto);

        // Step 6: Display result
        if (result.isSuccess()) {
            showSuccess();
        } else {
            showFailure(result.getMessage());
        }
    }
}