package com.example.application.service;

/**
 * Handles various error conditions and displays error messages.
 */
public class ErrorHandler {
    public void handleExistingTagError(String tag) {
        System.out.println("Error: Tag '" + tag + "' already exists.");
    }

    public void handleGeneralError(Exception error) {
        System.out.println("General error occurred: " + error.getMessage());
    }

    public void handleValidationError(String message) {
        System.out.println("Validation error: " + message);
    }

    public void showLoginError() {
        System.out.println("User not logged in");
    }
}