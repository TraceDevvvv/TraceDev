package com.agency.application;

import com.agency.presentation.ChangePasswordForm;
import com.agency.domain.AgencyOperator;
import com.agency.domain.IPasswordStrategy;
import com.agency.infrastructure.IAgencyRepository;
import com.agency.infrastructure.PasswordValidator;
import java.util.Optional;

/**
 * Use Case Controller for the Change Password use case.
 * Orchestrates the flow between presentation, domain, and infrastructure layers.
 */
public class ChangePasswordController {
    private ChangePasswordForm form;
    private IPasswordStrategy passwordStrategy;
    private PasswordValidator passwordValidator;
    private IAgencyRepository agencyRepository;
    private String currentUsername; // Simulates the logged-in user's username

    public ChangePasswordController(ChangePasswordForm form, IPasswordStrategy passwordStrategy,
                                    PasswordValidator passwordValidator, IAgencyRepository agencyRepository) {
        this.form = form;
        this.passwordStrategy = passwordStrategy;
        this.passwordValidator = passwordValidator;
        this.agencyRepository = agencyRepository;
        // In a real system, the current username would be obtained from session/context.
        this.currentUsername = "loggedInUser";
    }

    /**
     * Entry condition check: verifies that the Agency Operator is logged in.
     * @return true if logged in, false otherwise.
     */
    public boolean checkLoginStatus() {
        // Simulate checking login status. In reality, this would check session/token.
        // For this example, we assume the user is logged in if currentUsername is not null.
        return currentUsername != null && !currentUsername.isEmpty();
    }

    /**
     * Main method handling the password change process.
     * Implements the flow described in the sequence diagram.
     * @param oldPass The old password entered by the user.
     * @param newPass The new password entered by the user.
     * @param confirmPass The confirmation password entered by the user.
     * @return true if password change succeeded, false otherwise.
     */
    public boolean handlePasswordChange(String oldPass, String newPass, String confirmPass) {
        // Step: check login status (entry condition)
        if (!checkLoginStatus()) {
            form.showError("Please log in first");
            return false;
        }

        // Step: validate input (basic non─null/empty checks)
        if (!validateInput(oldPass, newPass, confirmPass)) {
            form.showError("All fields are required");
            return false;
        }

        // Step: validate password confirmation
        if (!passwordValidator.validateConfirmation(newPass, confirmPass)) {
            form.showError("Passwords do not match");
            return false;
        }

        // Step: validate password strength
        if (!passwordValidator.validateStrength(newPass)) {
            form.showError("Password doesn't meet requirements");
            return false;
        }

        // Step: retrieve the AgencyOperator entity from repository
        Optional<AgencyOperator> operatorOpt = agencyRepository.findByUsername(currentUsername);
        if (!operatorOpt.isPresent()) {
            form.showError("User account not found");
            return false;
        }
        AgencyOperator operator = operatorOpt.get();

        // Step: verify old password
        if (!verifyOldPassword(oldPass, operator, passwordStrategy)) {
            form.showError("Old password is incorrect");
            return false;
        }

        // Step: hash the new password
        String newHashedPassword = passwordStrategy.hashPassword(newPass);

        // Step: validate password history (prevent reuse)
        if (!passwordValidator.validateHistory(operator.getHashedPassword(), newHashedPassword)) {
            form.showError("Cannot reuse recent passwords");
            return false;
        }

        // Step: update the operator's password
        boolean changeSuccess = executePasswordChange(oldPass, newPass);
        if (!changeSuccess) {
            form.showError("Failed to update password");
            return false;
        }

        // Step: persist the operator with fallback handling
        boolean saveSuccess = agencyRepository.saveWithFallback(operator);
        if (saveSuccess) {
            form.showSuccess();
            return true;
        } else {
            // Connection interrupted (Exit Condition 2)
            form.showConnectionError();
            return false;
        }
    }

    /**
     * Validates that input strings are not null or empty.
     * @param oldPass Old password.
     * @param newPass New password.
     * @param confirmPass Confirmation password.
     * @return true if all inputs are valid, false otherwise.
     */
    private boolean validateInput(String oldPass, String newPass, String confirmPass) {
        return oldPass != null && !oldPass.trim().isEmpty()
                && newPass != null && !newPass.trim().isEmpty()
                && confirmPass != null && !confirmPass.trim().isEmpty();
    }

    /**
     * Verifies the old password against the stored hashed password using the given strategy.
     * @param oldPass The plain old password entered by the user.
     * @param operator The AgencyOperator entity.
     * @param strategy The password strategy (e.g., bcrypt).
     * @return true if verification succeeds, false otherwise.
     */
    private boolean verifyOldPassword(String oldPass, AgencyOperator operator, IPasswordStrategy strategy) {
        return operator.verifyPassword(oldPass, strategy);
    }

    /**
     * Executes the password change by calling the operator's changePassword method.
     * Corresponds to method in class diagram.
     * @param oldPass The old password (unused in this implementation).
     * @param newPass The new password.
     * @return true if password change succeeded, false otherwise.
     */
    private boolean executePasswordChange(String oldPass, String newPass) {
        Optional<AgencyOperator> operatorOpt = agencyRepository.findByUsername(currentUsername);
        if (!operatorOpt.isPresent()) {
            return false;
        }
        AgencyOperator operator = operatorOpt.get();
        String newHashedPassword = passwordStrategy.hashPassword(newPass);
        return operator.changePassword(newHashedPassword);
    }

    /**
     * Sets the current username (for simulation purposes).
     */
    public void setCurrentUsername(String username) {
        this.currentUsername = username;
    }
}