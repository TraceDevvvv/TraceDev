package com.example.account;

import java.util.HashMap;
import java.util.Map;

/**
 * The Use Case interactor for registering a new account.
 * Implements the core business logic and orchestrates interactions with
 * repositories, factories, validators, and the output port.
 */
public class RegisterAccountUseCase implements IRegisterAccountInputPort {
    private final IRegisterAccountOutputPort outputPort;
    private final IAccountRepository accountRepository;
    private final AccountFactory accountFactory;
    private final AccountValidator accountValidator;
    private final AuditingService auditingService; // REQ-016
    private final ErrorHandlingService errorHandlingService; // REQ-016

    private RegistrationRequestDTO pendingRegistrationRequest; // Stores request during confirmation flow

    /**
     * Constructor for RegisterAccountUseCase.
     *
     * @param outputPort The output port to notify about success, error, or confirmation requests.
     * @param accountRepository The repository for account data persistence.
     * @param accountFactory The factory for creating Account objects.
     * @param accountValidator The validator for registration request data.
     * @param auditingService The service for auditing events (REQ-016).
     * @param errorHandlingService The service for handling exceptions (REQ-016).
     */
    public RegisterAccountUseCase(IRegisterAccountOutputPort outputPort,
                                  IAccountRepository accountRepository,
                                  AccountFactory accountFactory,
                                  AccountValidator accountValidator,
                                  AuditingService auditingService,
                                  ErrorHandlingService errorHandlingService) {
        this.outputPort = outputPort;
        this.accountRepository = accountRepository;
        this.accountFactory = accountFactory;
        this.accountValidator = accountValidator;
        this.auditingService = auditingService;
        this.errorHandlingService = errorHandlingService;
    }

    /**
     * Initiates the account registration process.
     * Validates the request and asks for confirmation if valid.
     *
     * @param request The registration request data.
     */
    @Override
    public void execute(RegistrationRequestDTO request) {
        // 1. Validate the request
        if (!accountValidator.validate(request)) {
            // If invalid, present errors immediately
            outputPort.presentError("Validation failed: " + String.join(", ", accountValidator.getErrors()));
            return;
        }

        // Check if username already exists (a common validation that might be in use case or service layer)
        if (accountRepository.findByUsername(request.getUsername()) != null) {
            outputPort.presentError("Registration failed: Username '" + request.getUsername() + "' already exists.");
            return;
        }

        // Store the request for later confirmation
        this.pendingRegistrationRequest = request;

        // REQ-016: Audit initiation
        Map<String, String> auditDetails = new HashMap<>();
        auditDetails.put("username", request.getUsername());
        auditingService.audit("Registration initiated", auditDetails);

        // Request confirmation from the user (as per sequence diagram)
        outputPort.presentConfirmationRequest("Confirm Account Creation for user '" + request.getUsername() + "'?");
    }

    /**
     * Confirms and finalizes the account registration process.
     * This method is called after the user has agreed to proceed.
     */
    @Override
    public void confirmExecute() {
        if (pendingRegistrationRequest == null) {
            outputPort.presentError("No pending registration request to confirm.");
            return;
        }

        try {
            // 2. Create the Account entity using the factory
            Account newAccount = accountFactory.createAccount(
                    pendingRegistrationRequest.getUsername(),
                    pendingRegistrationRequest.getEmail(),
                    pendingRegistrationRequest.getPassword()
            );

            // 3. Save the account via the repository
            Account savedAccount = accountRepository.save(newAccount);

            // REQ-016: Audit successful creation
            Map<String, String> auditDetails = new HashMap<>();
            auditDetails.put("accountId", savedAccount.getId());
            auditDetails.put("username", savedAccount.getUsername());
            auditingService.audit("Account creation successful", auditDetails);

            // 4. Present success
            RegistrationResponseDTO response = new RegistrationResponseDTO(
                    true,
                    "Account '" + savedAccount.getUsername() + "' created successfully!",
                    savedAccount.getId()
            );
            outputPort.presentSuccess(response);

        } catch (DatabaseException e) { // REQ-016: Handle specific database exceptions
            errorHandlingService.handleException(e);
            outputPort.presentError("Database operation failed due to connection issue: " + e.getMessage());
        } catch (Exception e) { // Catch any other unexpected errors
            errorHandlingService.handleException(e);
            outputPort.presentError("An unexpected error occurred during registration: " + e.getMessage());
        } finally {
            // Clear the pending request regardless of success or failure
            this.pendingRegistrationRequest = null;
        }
    }

    /**
     * Allows for external cancellation of a pending registration request.
     * Clears the stored request and informs the output port if necessary.
     */
    public void cancelPendingRegistration() {
        if (this.pendingRegistrationRequest != null) {
            this.pendingRegistrationRequest = null;
            // Optionally, notify the output port that the operation was cancelled
            // outputPort.presentError("Registration cancelled by user.");
        }
    }
}