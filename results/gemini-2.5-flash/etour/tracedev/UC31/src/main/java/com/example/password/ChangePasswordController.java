package com.example.password;

/**
 * Handles user requests related to changing passwords.
 * Acts as a mediator between the ChangePasswordForm and the ChangePasswordUseCase.
 */
public class ChangePasswordController {
    private ChangePasswordUseCase useCase;
    private ChangePasswordForm view;

    /**
     * Constructs a ChangePasswordController with its dependencies.
     * @param useCase The ChangePasswordUseCase to execute business logic.
     * @param view The ChangePasswordForm to interact with the user interface.
     */
    public ChangePasswordController(ChangePasswordUseCase useCase, ChangePasswordForm view) {
        this.useCase = useCase;
        this.view = view;
        System.out.println("DEBUG: ChangePasswordController initialized.");
    }

    /**
     * Handles a request to change a user's password.
     * This method orchestrates the call to the use case and handles its outcomes,
     * including displaying appropriate messages via the form.
     *
     * @param accountId The ID of the account whose password is to be changed.
     * @param newPassword The new password provided by the user.
     * @param confirmPassword The confirmed new password provided by the user.
     */
    public void handleChangePasswordRequest(String accountId, String newPassword, String confirmPassword) {
        System.out.println("DEBUG: Controller received password change request for account: " + accountId);
        try {
            // Delegate the business logic to the use case
            PasswordChangeResult result = useCase.execute(accountId, newPassword, confirmPassword);

            switch (result) {
                case SUCCESS:
                    view.showSuccessMessage("Password updated successfully.");
                    break;
                case INPUT_MISMATCH:
                    view.showErrorMessage("Passwords do not match or are empty.");
                    break;
                case POLICY_VIOLATION:
                    view.showErrorMessage("New password violates policy.");
                    break;
                case ACCOUNT_NOT_FOUND:
                    view.showErrorMessage("Account not found.");
                    break;
                default:
                    view.showErrorMessage("Password change failed due to an unknown error.");
                    break;
            }
        } catch (PersistenceException e) {
            // Catch persistence exceptions and display a user-friendly error message
            System.err.println("DEBUG: Controller caught PersistenceException: " + e.getMessage());
            view.showErrorMessage("A connection error occurred. Please try again.");
        }
    }
}