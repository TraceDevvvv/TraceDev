package com.example;

/**
 * Service responsible for notifying administrators of errors and activating specific error handling use cases.
 */
public class ErrorNotificationService {
    private ErrodatiUseCaseHandler errodatiUseCaseHandler;

    /**
     * Constructor for ErrorNotificationService.
     * @param errodatiUseCaseHandler The handler for the Errodati use case.
     */
    public ErrorNotificationService(ErrodatiUseCaseHandler errodatiUseCaseHandler) {
        this.errodatiUseCaseHandler = errodatiUseCaseHandler;
    }

    /**
     * Notifies an administrator of a specific error message.
     *
     * @param message The error message to be conveyed to the administrator.
     */
    public void notifyAdminOfError(String message) {
        System.out.println("ErrorNotificationService: Notifying administrator of error: \"" + message + "\"");
        // In a real application, this might involve sending emails, SMS,
        // or displaying a critical alert in an admin dashboard.
    }

    /**
     * Activates the specific "Errodati" use case, delegating to its handler (R12).
     */
    public void activateErrodatiUseCase() {
        System.out.println("ErrorNotificationService: Delegating to ErrodatiUseCaseHandler to activate Errodati use case.");
        errodatiUseCaseHandler.handleErrodatiActivation();
        System.out.println("ErrorNotificationService: Errodati use case activation initiated.");
    }
}