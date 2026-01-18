package com.example;

/**
 * Represents an administrator actor.
 * This class is a placeholder; in a real system, it might be a UI or external system.
 */
public class Administrator {
    private RegistrationController registrationController;
    private SMOSServerConnection serverConnection;

    public Administrator(RegistrationController registrationController, SMOSServerConnection serverConnection) {
        this.registrationController = registrationController;
        this.serverConnection = serverConnection;
    }

    /**
     * Triggers the acceptance of a registration request.
     * @param requestId the request ID
     */
    public void triggerAcceptRequest(String requestId) {
        registrationController.handleAcceptRequest(requestId);
    }

    /**
     * Interrupts the SMOS server connection (simulating REQ-EXIT-001).
     */
    public void interruptConnection() {
        if (serverConnection != null) {
            serverConnection.disconnect();
        }
    }
}