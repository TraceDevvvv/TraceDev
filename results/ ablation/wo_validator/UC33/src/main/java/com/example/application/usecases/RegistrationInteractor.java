
package com.example.application.usecases;

import com.example.domain.Account;
import com.example.application.interfaces.IAccountRepository;
import com.example.application.interfaces.INotificationService;
import com.example.application.dto.RegistrationRequest;
import com.example.application.dto.RegistrationResponse;
import com.example.application.dto.ValidationResult;

/**
 * Use case interactor that orchestrates the registration flow.
 * Handles validation, account creation, notification, and error handling.
 */
public class RegistrationInteractor {
    private IAccountRepository accountRepository;
    private INotificationService notificationService;
    // Assumption: ErroredUseCase is injected for error handling as per diagram.
    private ErroredUseCase erroredUseCase;

    public RegistrationInteractor(IAccountRepository repo, INotificationService notifier) {
        this.accountRepository = repo;
        this.notificationService = notifier;
        // Assumption: ErroredUseCase created internally with default error handler.
        // In a real scenario, this would be injected.
        this.erroredUseCase = new ErroredUseCase(null);
    }

    // Overloaded constructor to allow setting erroredUseCase.
    public RegistrationInteractor(IAccountRepository repo, INotificationService notifier, ErroredUseCase erroredUseCase) {
        this.accountRepository = repo;
        this.notificationService = notifier;
        this.erroredUseCase = erroredUseCase;
    }

    /**
     * Executes the registration use case.
     * @param request The registration request data.
     * @return RegistrationResponse indicating success or failure.
     */
    public RegistrationResponse execute(RegistrationRequest request) {
        // Step 5: Validate the request.
        ValidationResult validationResult = validateRequest(request);
        if (!validationResult.isValid()) {
            // Handle error via error use case.
            handleError(validationResult.getErrorMessage());
            return new RegistrationResponse(false, validationResult.getErrorMessage(), null);
        }

        // Create account entity.
        Account account = new Account(request.getUsername(), request.getPassword(), request.getEmail());
        // Validate the account (domain validation).
        if (!account.validate()) {
            handleError("Account validation failed.");
            return new RegistrationResponse(false, "Account validation failed.", null);
        }

        try {
            // Save the account.
            String accountId = accountRepository.save(account);
            // Notify success.
            notificationService.notifySuccess("Account created");
            // Return success response.
            return new RegistrationResponse(true, "Registration successful.", accountId);
        } catch (Exception e) {
            // Handle persistence errors (e.g., connection lost).
            notificationService.notifyError("Connection lost");
            handleError("Connection error");
            return new RegistrationResponse(false, "Connection error", null);
        }
    }

    /**
     * Validates the registration request (application-level validation).
     * Assumption: checks for confirmData flag and duplicate username.
     * @param request The request to validate.
     * @return ValidationResult containing validity and error message.
     */
    private ValidationResult validateRequest(RegistrationRequest request) {
        if (request == null) {
            return new ValidationResult(false, "Request is null.");
        }
        if (request.getUsername() == null || request.getUsername().trim().isEmpty()) {
            return new ValidationResult(false, "Username is required.");
        }
        if (request.getPassword() == null || request.getPassword().trim().isEmpty()) {
            return new ValidationResult(false, "Password is required.");
        }
        if (request.getEmail() == null || request.getEmail().trim().isEmpty() || !request.getEmail().contains("@")) {
            return new ValidationResult(false, "Valid email is required.");
        }
        if (!request.isConfirmData()) {
            return new ValidationResult(false, "Data confirmation is required.");
        }
        // Check for duplicate username.
        Account existing = accountRepository.findByUsername(request.getUsername());
        if (existing != null) {
            return new ValidationResult(false, "Username already exists.");
        }
        return new ValidationResult(true, null);
    }

    /**
     * Delegates error handling to the errored use case.
     * @param errorMessage The error message.
     */
    private void handleError(String errorMessage) {
        ValidationResult error = new ValidationResult(false, errorMessage);
        erroredUseCase.execute(error);
    }
}
