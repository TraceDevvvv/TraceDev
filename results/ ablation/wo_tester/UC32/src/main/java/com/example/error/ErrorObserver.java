package com.example.error;

/**
 * Interface for observers that need to be notified of error updates.
 */
public interface ErrorObserver {
    /**
     * Called when the observed subject's state changes.
     * @param subject the subject that triggered the update
     */
    void update(ErrorNotificationSubject subject);
}