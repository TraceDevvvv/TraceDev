package com.etour.registration.web;

import com.etour.registration.application.CreateAccountUseCase;
import com.etour.registration.domain.CreateAccountCommand;
import com.etour.registration.domain.RegistrationResult;

/**
 * Presentation layer controller handling HTTP requests for registration.
 * ResponseEntity is simulated with a simple string/object return.
 */
public class RegistrationController {
    private CreateAccountUseCase createAccountUseCase;

    public RegistrationController(CreateAccountUseCase createAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
    }

    /**
     * Displays the registration form (simulated).
     */
    public RegistrationFormDTO showRegistrationForm() {
        return new RegistrationFormDTO();
    }

    /**
     * Handles form submission.
     */
    public String submitRegistration(RegistrationFormDTO formData) {
        // Map DTO to command
        CreateAccountCommand command = new CreateAccountCommand(
                formData.getUsername(),
                formData.getEmail(),
                formData.getPassword()
        );

        // Execute use case
        RegistrationResult result = createAccountUseCase.execute(command);

        // Map result to response DTO
        RegistrationResponseDTO response = new RegistrationResponseDTO(
                result.isSuccess(),
                result.getMessage(),
                result.getAccountId()
        );

        if (response.isSuccess()) {
            return "Success page: " + response.getMessage();
        } else {
            return "Error page: " + response.getMessage();
        }
    }

    /**
     * Confirms the operation (called after user confirmation).
     */
    public String confirmOperation() {
        // In a real app, would retrieve the pending command from session
        System.out.println("Operation confirmed.");
        return "Confirmed";
    }

    /**
     * Cancels the operation.
     */
    public String cancelOperation() {
        displayCancellationMessage();
        return "Operation cancelled.";
    }

    private void displayCancellationMessage() {
        System.out.println("Registration cancelled by user.");
    }

    private void displayConnectionError() {
        System.out.println("Connection error occurred.");
    }

    // New methods for sequence diagram traceability
    public void enableLogging() {
        System.out.println("Logging enabled for registration endpoint.");
    }

    public RegistrationFormDTO GET_register() {
        // This method combines steps 1 and 3 from sequence diagram
        enableLogging();
        return showRegistrationForm();
    }

    public String displaySuccessPage() {
        return "Registration successful! Account created.";
    }

    public String displayErrorPage(java.util.List<String> errors) {
        return "Registration failed with errors: " + String.join(", ", errors);
    }

    public String displayConfirmationPrompt() {
        return "Please confirm your registration by clicking the link in your email.";
    }

    public void setCreateAccountUseCase(CreateAccountUseCase useCase) {
        this.createAccountUseCase = useCase;
    }
}