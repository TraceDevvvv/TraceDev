package com.etour.registration.application;

import com.etour.registration.domain.*;
import com.etour.registration.ports.AccountRepository;
import com.etour.registration.ports.NotificationPort;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the CreateAccountUseCase, orchestrating the registration flow.
 */
public class CreateAccountUseCaseController implements CreateAccountUseCase {
    private RegistrationDataValidator validator;
    private AccountRepository accountRepository;
    private NotificationPort notificationService;

    public CreateAccountUseCaseController(RegistrationDataValidator validator,
                                          AccountRepository accountRepository,
                                          NotificationPort notificationService) {
        this.validator = validator;
        this.accountRepository = accountRepository;
        this.notificationService = notificationService;
    }

    @Override
    public RegistrationResult execute(CreateAccountCommand command) {
        ValidationResult validationResult = validator.validate(command);
        if (!validationResult.isValid()) {
            activateUseCaseErrored(validationResult.getErrors());
            return new RegistrationResult(false, null, "Validation failed");
        }

        // Ask for confirmation (simulated)
        boolean confirmed = askForConfirmation();
        if (!confirmed) {
            return new RegistrationResult(false, null, "Operation cancelled by user");
        }

        // Proceed with creation
        return executeConfirmed(command);
    }

    /**
     * Executes the confirmed registration (called after user confirmation).
     */
    public RegistrationResult executeConfirmed(CreateAccountCommand command) {
        try {
            Account account = createAccount(command);
            Account savedAccount = accountRepository.save(account);
            boolean notificationSent = notificationService.sendConfirmation(savedAccount.getId(), savedAccount.getEmail());
            if (notificationSent) {
                return new RegistrationResult(true, savedAccount.getId(), "Account created successfully. Confirmation email sent.");
            } else {
                // Notification failed, but account created
                return new RegistrationResult(true, savedAccount.getId(), "Account created but confirmation email failed.");
            }
        } catch (Exception e) {
            return new RegistrationResult(false, null, "Registration failed: " + e.getMessage());
        }
    }

    /**
     * Quality requirement: handle errors.
     */
    public void activateUseCaseErrored(List<String> errors) {
        // Log errors or perform error handling logic
        System.err.println("UseCase errors: " + errors);
    }

    /**
     * Creates the domain Account object from the command.
     */
    private Account createAccount(CreateAccountCommand command) {
        // In a real scenario, password would be hashed.
        String hashedPassword = "hashed_" + command.getPassword(); // simplified
        return new Account(command.getUsername(), command.getEmail(), hashedPassword);
    }

    /**
     * Simulates asking the user for confirmation.
     * In a real application, this might involve a UI interaction.
     */
    private boolean askForConfirmation() {
        // Simulate confirmation logic – assume true for success scenario
        return true;
    }

    // New methods for sequence diagram traceability
    public RegistrationResult executeConfirmedForSequence(CreateAccountCommand command) {
        return executeConfirmed(command);
    }

    public void askForConfirmationPublic() {
        askForConfirmation();
    }

    public String displayConfirmationPromptPublic() {
        return "Please confirm your registration. This is an internal confirmation prompt.";
    }

    public Account createAccountForSequence(CreateAccountCommand command) {
        return createAccount(command);
    }
}