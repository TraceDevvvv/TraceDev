package com.example.account;

import java.util.Scanner;

/**
 * Initializes the entire system, wiring up all dependencies and
 * serving as the application's entry point to simulate the sequence diagram flow.
 * (Added to satisfy REQ-004, acts as the "main" driver for the demo)
 */
public class SystemInitializer {
    private LoggingService loggingService;

    /**
     * Configures the logging service. (Added to satisfy REQ-004)
     *
     * @param loggingService The logging service to initialize.
     */
    public void initializeLogging(LoggingService loggingService) {
        this.loggingService = loggingService;
        this.loggingService.setInitialized(true);
        this.loggingService.log("SystemInitializer: Logging service configured.");
    }

    /**
     * Main method to run the application and demonstrate the registration flow.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // --- 1. Instantiate Core Serv and Infrastructure ---
        SystemInitializer sysInit = new SystemInitializer();
        LoggingService logSvc = new LoggingService(); // REQ-004
        AuditingService auditSvc = new AuditingService(); // REQ-016
        ErrorHandlingService errHdlSvc = new ErrorHandlingService(); // REQ-016
        DatabaseAdapter dbAdapter = new DatabaseAdapter();
        IAccountRepository accountRepository = new AccountRepositoryImpl(dbAdapter);
        AccountFactory accountFactory = new AccountFactory();
        AccountValidator accountValidator = new AccountValidator();

        // --- 2. Instantiate UI/Presentation Components ---
        RegistrationForm form = new RegistrationForm();
        IRegisterAccountOutputPort presenter = new RegistrationPresenter(form);

        // --- 3. Instantiate Use Case ---
        IRegisterAccountInputPort useCase = new RegisterAccountUseCase(
                presenter, accountRepository, accountFactory, accountValidator, auditSvc, errHdlSvc);

        // --- 4. Instantiate Controller and wire it to form ---
        RegistrationController controller = new RegistrationController(useCase, sysInit, logSvc); // REQ-004
        form.setController(controller); // Allow form to call controller

        System.out.println("\n--- Application Started ---");
        logSvc.log("User accessed registration form (simulated by SystemInitializer)"); // REQ-004

        // --- Simulate Happy Path Registration Flow ---
        System.out.println("\n--- Scenario 1: Successful Registration ---");
        form.display();
        RegistrationRequestDTO happyPathRequest = new RegistrationRequestDTO("testuser1", "test1@example.com", "password123");
        System.out.println("Simulating form data submission for: " + happyPathRequest.getUsername());
        controller.registerAccount(happyPathRequest); // Form submits to Controller, which triggers confirmation flow

        // The presenter calls form.askConfirmation(), which prompts the user.
        // SystemInitializer then gets the result and calls the controller.
        if (form.askConfirmation("Do you confirm account creation for " + happyPathRequest.getUsername() + "? (yes/no)")) {
            controller.confirmRegistration(); // Form calls Controller to confirm (simulated by SystemInitializer)
        } else {
            System.out.println("User declined confirmation. Registration aborted.");
            controller.cancelRegistration(); // Form calls Controller to cancel (simulated by SystemInitializer)
        }

        // --- Simulate Registration with Validation Errors ---
        System.out.println("\n--- Scenario 2: Registration with Validation Errors ---");
        RegistrationRequestDTO invalidRequest = new RegistrationRequestDTO("a", "bad_email", "short");
        System.out.println("Simulating form data submission for invalid user: " + invalidRequest.getUsername());
        controller.registerAccount(invalidRequest); // Form submits invalid data

        // --- Simulate Duplicate Username ---
        System.out.println("\n--- Scenario 3: Registration with Duplicate Username ---");
        RegistrationRequestDTO duplicateRequest = new RegistrationRequestDTO("testuser1", "testduplicate@example.com", "duplicatepass");
        System.out.println("Simulating form data submission for duplicate username: " + duplicateRequest.getUsername());
        controller.registerAccount(duplicateRequest); // Should fail due to existing username
        // No confirmation needed as validation/uniqueness check happens before confirmation request

        // --- Simulate Database Error (REQ-016) ---
        System.out.println("\n--- Scenario 4: Registration with Simulated Database Error ---");
        dbAdapter.setSimulateError(true); // Enable error simulation
        RegistrationRequestDTO dbErrorRequest = new RegistrationRequestDTO("dberroruser", "db@error.com", "dbpassword");
        System.out.println("Simulating form data submission for: " + dbErrorRequest.getUsername());
        controller.registerAccount(dbErrorRequest);

        // Expect confirmation request, then confirm to trigger DB error path
        if (form.askConfirmation("Confirm account creation for " + dbErrorRequest.getUsername() + " (expecting DB error)? (yes/no)")) {
            controller.confirmRegistration(); // This should trigger the DatabaseException in RepoImpl
        } else {
            System.out.println("User declined confirmation for DB error scenario. Registration aborted.");
            controller.cancelRegistration();
        }
        dbAdapter.setSimulateError(false); // Disable error simulation

        // --- Simulate User Cancellation (REQ-014) ---
        System.out.println("\n--- Scenario 5: User Cancels Registration ---");
        RegistrationRequestDTO cancelRequest = new RegistrationRequestDTO("canceluser", "cancel@example.com", "cancelpass");
        System.out.println("Simulating form data submission for: " + cancelRequest.getUsername());
        controller.registerAccount(cancelRequest); // Initiate registration, which will lead to a confirmation prompt.

        // This scenario simulates the user explicitly cancelling *before* responding to the confirmation prompt.
        System.out.println("User explicitly cancels the registration process (simulated, overriding confirmation prompt).");
        controller.cancelRegistration(); // REQ-014: User calls cancelRegistration
        form.notifyCancellation(); // REQ-014: Form notifies the user

        System.out.println("\n--- Application Finished ---");
    }
}