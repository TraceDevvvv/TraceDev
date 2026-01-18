package com.example.tagmanagement;

/**
 * Orchestrates the handling of an error when an attempt is made to add an existing tag.
 * This use case confirms the error has been acknowledged and triggers system state recovery.
 */
public class AddExistingTagErrorHandlerUseCase {
    private final SystemStateRecoveryService systemStateRecoveryService; // Dependency for state recovery
    // The use case needs to call back to the controller after handling the error.
    private final TagManagementController controllerCallback; // Callback reference for errorHandled()

    /**
     * Constructs an AddExistingTagErrorHandlerUseCase.
     *
     * @param systemStateRecoveryService The service responsible for recovering the system state.
     * @param controllerCallback The controller to notify when the error handling is complete.
     */
    public AddExistingTagErrorHandlerUseCase(SystemStateRecoveryService systemStateRecoveryService,
                                             TagManagementController controllerCallback) {
        this.systemStateRecoveryService = systemStateRecoveryService;
        this.controllerCallback = controllerCallback;
    }

    /**
     * Confirms that the error notification has been read and initiates system recovery.
     * This method is called by the controller.
     */
    public void confirmErrorRead() {
        // Note 3: System confirms the reading of the notification.
        System.out.println("[ErrorHandlerUC] Error reading confirmed. Initiating state recovery.");

        // Delegate to triggerStateRecovery to recover the previous state.
        triggerStateRecovery();

        // After state recovery, notify the controller that the error has been handled.
        // This is the interaction: ErrorHandlerUC --> Controller : errorHandled()
        controllerCallback.errorHandled();
        System.out.println("[ErrorHandlerUC] Error handling process completed.");
    }

    /**
     * Triggers the system state recovery process.
     */
    public void triggerStateRecovery() {
        // Delegate to SystemStateRecoveryService to recover the previous state.
        // This is the interaction: ErrorHandlerUC -> RecoveryService : recoverPreviousState()
        systemStateRecoveryService.recoverPreviousState();
        // Note 4: System recovers the previous state.
    }
}