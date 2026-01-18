package com.example;

/**
 * Concrete implementation of INotificationService.
 * This class provides a simple console-based notification mechanism for demonstration.
 * In a real application, this might integrate with a UI framework,
 * logging system, or an email/SMS service.
 */
public class UserNotificationService implements INotificationService {

    /**
     * Notifies the user of a successful operation by printing to console.
     * @param message The success message to display.
     */
    @Override
    public void notifySuccess(String message) {
        System.out.println("[SUCCESS] " + message);
    }

    /**
     * Notifies the user of an error or failed operation by printing to console.
     * @param message The error message to display.
     */
    @Override
    public void notifyError(String message) {
        System.err.println("[ERROR] " + message);
    }
}