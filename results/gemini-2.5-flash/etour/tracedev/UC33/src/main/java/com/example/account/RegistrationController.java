package com.example.account;

/**
 * The controller handles incoming requests from the UI (RegistrationForm)
 * and orchestrates the interaction with the use case.
 */
public class RegistrationController {
    private final IRegisterAccountInputPort inputPort;
    private final SystemInitializer systemInitializer; // REQ-004
    private final LoggingService loggingService; // REQ-004

    /**
     * Constructor for RegistrationController.
     *
     * @param inputPort The input port for the Register Account use case.
     * @param systemInitializer The SystemInitializer to configure logging (REQ-004).
     * @param loggingService The LoggingService to log activities (REQ-004).
     */
    public RegistrationController(IRegisterAccountInputPort inputPort,
                                  SystemInitializer systemInitializer,
                                  LoggingService loggingService) { // Modified to satisfy REQ-004
        this.inputPort = inputPort;
        this.systemInitializer = systemInitializer;
        this.loggingService = loggingService;
        // REQ-004: Initialize logging when controller is created (or earlier in application startup)
        this.systemInitializer.initializeLogging(this.loggingService);
        this.loggingService.log("RegistrationController initialized.");
    }

    /**
     * Handles a request to register a new account.
     * Delegates the actual registration logic to the input port of the use case.
     *
     * @param request The data transfer object containing registration details.
     */
    public void registerAccount(RegistrationRequestDTO request) {
        loggingService.log("User submitted registration form for username: " + request.getUsername()); // REQ-004
        inputPort.execute(request);
    }

    /**
     * Handles a request to confirm a pending registration.
     * This is typically called after the user has agreed to a confirmation prompt.
     */
    public void confirmRegistration() {
        loggingService.log("User confirmed registration."); // REQ-004
        inputPort.confirmExecute();
    }

    /**
     * Handles a request to cancel a pending registration.
     * (Added to satisfy REQ-014)
     * Notifies the use case that the operation should be aborted.
     */
    public void cancelRegistration() {
        loggingService.log("User cancelled registration."); // REQ-004
        if (inputPort instanceof RegisterAccountUseCase) { // Downcast to access specific cancellation method
            ((RegisterAccountUseCase) inputPort).cancelPendingRegistration();
        }
        // Optionally, notify the form or presenter about the cancellation
    }
}