package com.example.password;

/**
 * Represents the user interface for changing a password.
 * This class simulates UI interactions and delegates actions to the controller.
 */
public class ChangePasswordForm {
    private ChangePasswordController controller; // Dependency on Controller
    private String newPasswordInput;
    private String confirmPasswordInput;

    /**
     * Sets the controller that this form will use to handle password change requests.
     * This method is used to inject the dependency and avoid circular constructor dependencies.
     * @param controller The ChangePasswordController instance.
     */
    public void setController(ChangePasswordController controller) {
        this.controller = controller;
    }

    /**
     * Simulates displaying the change password form to the user.
     */
    public void displayForm() {
        System.out.println("\n--- Change Password Form ---");
        System.out.println("Please enter your new password and confirm it.");
        // In a real UI, this would render input fields.
    }

    /**
     * Simulates getting the new password input from the user.
     * For this example, it's hardcoded, but would come from a UI element.
     * @return The new password entered by the user.
     */
    public String getNewPassword() {
        // This method is called by the Form itself, typically after user input.
        // For simulation, we assume user has entered it into 'newPasswordInput'.
        return newPasswordInput;
    }

    /**
     * Simulates getting the confirmed password input from the user.
     * For this example, it's hardcoded, but would come from a UI element.
     * @return The confirmed password entered by the user.
     */
    public String getConfirmedPassword() {
        // This method is called by the Form itself, typically after user input.
        // For simulation, we assume user has entered it into 'confirmPasswordInput'.
        return confirmPasswordInput;
    }

    /**
     * Displays a success message to the user.
     * @param message The success message to display.
     */
    public void showSuccessMessage(String message) {
        System.out.println("\nSUCCESS: " + message);
    }

    /**
     * Displays an error message to the user.
     * @param message The error message to display.
     */
    public void showErrorMessage(String message) {
        System.err.println("\nERROR: " + message);
    }

    /**
     * Simulates the "Agency Operator" initiating a password change request.
     * This would trigger the form display and subsequent submission.
     * @param accountId The ID of the account for which password is to be changed.
     * @param newPassword The new password to be submitted.
     * @param confirmPassword The confirmation of the new password.
     */
    public void requestChangePassword(String accountId, String newPassword, String confirmPassword) {
        System.out.println("DEBUG: User 'Agency Operator' requests password change for account: " + accountId);
        displayForm();
        // Simulate user input for the new password and confirmation
        this.newPasswordInput = newPassword;
        this.confirmPasswordInput = confirmPassword;
        // User then submits the form
        submitChangePassword(accountId, getNewPassword(), getConfirmedPassword());
    }

    /**
     * Simulates the user submitting the change password form.
     * It delegates the actual request handling to the ChangePasswordController.
     * @param accountId The ID of the account.
     * @param newPassword The new password entered by the user.
     * @param confirmPassword The confirmed password entered by the user.
     */
    public void submitChangePassword(String accountId, String newPassword, String confirmPassword) {
        System.out.println("DEBUG: Form submitted with new password: " + newPassword + ", confirm: " + confirmPassword);
        if (controller == null) {
            showErrorMessage("Form is not properly initialized: Controller missing.");
            return;
        }
        // Delegate to the controller
        controller.handleChangePasswordRequest(accountId, newPassword, confirmPassword);
    }
}