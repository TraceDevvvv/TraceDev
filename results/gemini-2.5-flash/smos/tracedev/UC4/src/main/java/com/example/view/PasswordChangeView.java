package com.example.view;

import java.util.Scanner;

/**
 * Represents the user interface for password change operations.
 * It handles displaying forms, messages, and capturing user input.
 */
public class PasswordChangeView {

    private Scanner scanner;

    public PasswordChangeView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the password change form to the user.
     */
    public void displayForm() {
        System.out.println("\n--- Change Password ---");
        System.out.println("Please enter your current password, new password, and confirm new password.");
    }

    /**
     * Prompts the user for password input and returns it as a PasswordChangeForm object.
     * @return A PasswordChangeForm containing the user's input.
     */
    public PasswordChangeForm getFormData() {
        System.out.print("Old password: ");
        String oldPwd = scanner.nextLine();
        System.out.print("New password: ");
        String newPwd = scanner.nextLine();
        System.out.print("Confirm new password: ");
        String confirmNewPwd = scanner.nextLine();
        return new PasswordChangeForm(oldPwd, newPwd, confirmNewPwd);
    }

    /**
     * Displays a success message to the user.
     * @param message The success message to display.
     */
    public void displaySuccessMessage(String message) {
        System.out.println("SUCCESS: " + message);
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("ERROR: " + message);
    }

    /**
     * Notifies that the user has cancelled the password change operation.
     */
    public void cancelPasswordChange() {
        // In a real UI, this might close the form or navigate away.
        System.out.println("ACTION: User cancelled password change process.");
    }
    
    /**
     * Closes the scanner when the view is no longer needed.
     */
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}