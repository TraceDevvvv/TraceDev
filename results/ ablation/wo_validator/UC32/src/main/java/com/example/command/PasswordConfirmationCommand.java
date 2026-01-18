package com.example.command;

/**
 * Command object containing password change information.
 */
public class PasswordConfirmationCommand {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;

    public PasswordConfirmationCommand(String currentPassword, String newPassword, String confirmPassword) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmPassword = confirmPassword;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    /**
     * Executes the command (typically delegated to an interactor).
     */
    public void execute() {
        // This method is a placeholder; actual execution is handled by the interactor
        throw new UnsupportedOperationException("Command execution should be delegated to PasswordChangeInteractor");
    }
}