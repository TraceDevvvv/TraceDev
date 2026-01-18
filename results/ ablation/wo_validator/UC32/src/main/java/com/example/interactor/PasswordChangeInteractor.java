package com.example.interactor;

import com.example.command.PasswordConfirmationCommand;
import com.example.presenter.PasswordPresenter;
import com.example.validator.PasswordValidator;
import com.example.builder.ErrorMessageBuilder;

/**
 * Interactor handling password change logic.
 */
public class PasswordChangeInteractor {
    private PasswordPresenter presenter;
    private PasswordValidator validator;
    private ErrorMessageBuilder errorBuilder;

    public PasswordChangeInteractor(PasswordPresenter presenter, PasswordValidator validator, ErrorMessageBuilder errorBuilder) {
        this.presenter = presenter;
        this.validator = validator;
        this.errorBuilder = errorBuilder;
    }

    /**
     * Execute password change command.
     * @param command the password confirmation command
     */
    public void execute(PasswordConfirmationCommand command) {
        // Step 1: Validate password confirmation
        boolean isConfirmationValid = validator.validateConfirmation(
                command.getNewPassword(), 
                command.getConfirmPassword()
        );
        
        if (!isConfirmationValid) {
            // Confirmation mismatch - build error and notify presenter
            String errorMessage = errorBuilder.buildConfirmationError();
            presenter.displayError(errorMessage);
            return; // Terminate process as per sequence diagram
        }
        
        // Step 2: If confirmation valid, continue with full password validation
        boolean isPasswordChangeValid = validator.validatePasswordChange(
                command.getCurrentPassword(),
                command.getNewPassword(),
                command.getConfirmPassword()
        );
        
        if (isPasswordChangeValid) {
            presenter.displaySuccess();
        } else {
            // Handle other validation errors (e.g., password mismatch)
            String errorMessage = errorBuilder.buildPasswordMismatchError();
            presenter.displayError(errorMessage);
        }
    }
}