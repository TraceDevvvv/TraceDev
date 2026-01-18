package com.example;

/**
 * Controller for handling registration requests.
 */
public class RegistrationController {
    private RegistrationService registrationService;
    private RegistrationListViewController listViewController;

    // Reference to external server connection for interruption scenario (REQ-EXIT-001)
    private SMOSServerConnection serverConnection;

    public RegistrationController(RegistrationService registrationService,
                                  RegistrationListViewController listViewController,
                                  SMOSServerConnection serverConnection) {
        this.registrationService = registrationService;
        this.listViewController = listViewController;
        this.serverConnection = serverConnection;
    }

    /**
     * Handles a request to accept a registration.
     * Must complete within 5 seconds (performance requirement REQ-PERF-001).
     * @param requestId the registration request ID
     */
    public void handleAcceptRequest(String requestId) {
        // Check for connection interruption (REQ-EXIT-001)
        if (serverConnection != null && !serverConnection.isConnected()) {
            System.err.println("Connection lost. Process terminated.");
            // In a real scenario, we might throw an exception or notify the caller.
            return;
        }

        // Perform the acceptance flow
        registrationService.acceptRegistration(requestId);

        // Normal flow: refresh the list view
        try {
            listViewController.refreshView();
            System.out.println("Registration accepted. List updated.");
        } catch (Exception e) {
            // If refresh fails, we still consider the registration accepted,
            // but log the error.
            System.err.println("Registration accepted but failed to refresh view: " + e.getMessage());
        }
    }
}