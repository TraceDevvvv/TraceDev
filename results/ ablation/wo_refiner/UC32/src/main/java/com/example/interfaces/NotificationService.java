package com.example.interfaces;

/**
 * Interface for notification serv.
 */
public interface NotificationService {
    
    /**
     * Shows an error message.
     * @param message the error message to display
     */
    void showError(String message);
    
    /**
     * Checks if the error has been acknowledged by the user.
     * @return true if acknowledged, false otherwise
     */
    boolean isAcknowledged();
}