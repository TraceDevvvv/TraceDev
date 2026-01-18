package com.example;

/**
 * Defines the contract for sending user notifications.
 * This is part of the Notification Layer.
 */
public interface INotificationService {

    /**
     * Notifies the user of a successful operation.
     * @param message The success message to display.
     */
    void notifySuccess(String message);

    /**
     * Notifies the user of an error or failed operation.
     * @param message The error message to display.
     */
    void notifyError(String message);
}