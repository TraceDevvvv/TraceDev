package com.example;

import java.util.Map;

/**
 * UI layer for user interactions.
 */
public class UserInterface {
    private UserControllerImpl controller;

    public UserInterface(UserControllerImpl controller) {
        this.controller = controller;
    }

    public void displayUserDetails(User user) {
        System.out.println("Displaying user details: " + user.getId() + " - " + user.getName());
    }

    public void onEditButtonClicked(String userId, Map<String, Object> changes) {
        System.out.println("Edit button clicked for user: " + userId);
        // First verify admin access
        if (!controller.verifyAdminAccess()) {
            showErrorMessage(java.util.Collections.singletonList("Admin access required"));
            return;
        }

        EditUserRequest request = new EditUserRequest(userId, changes);
        EditUserResponse response = controller.handleEditUserRequest(request);
        if (response.isSuccess()) {
            showSuccessMessage(response.getMessage());
        } else {
            showErrorMessage(response.getErrors());
        }
    }

    public void showSuccessMessage(String message) {
        System.out.println("SUCCESS: " + message);
    }

    public void showErrorMessage(java.util.List<String> errors) {
        System.out.println("ERROR:");
        for (String err : errors) {
            System.out.println(" - " + err);
        }
    }

    /**
     * Cancels the ongoing operation.
     * Added to satisfy Exit-Interruption requirement.
     */
    public void cancelOperation() {
        controller.cancelOperation();
        System.out.println("UI: Operation cancelled.");
    }
}