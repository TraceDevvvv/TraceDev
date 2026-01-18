package com.example.error;

import java.util.ArrayList;
import java.util.List;

/**
 * Subject in the Observer pattern for notifying about errors.
 */
public class ErrorNotificationSubject {
    private List<ErrorObserver> observers = new ArrayList<>();
    private String errorMessage;

    /**
     * Attaches an observer to the subject.
     * @param observer the observer to attach
     */
    public void attach(ErrorObserver observer) {
        observers.add(observer);
    }

    /**
     * Detaches an observer from the subject.
     * @param observer the observer to detach
     */
    public void detach(ErrorObserver observer) {
        observers.remove(observer);
    }

    /**
     * Notifies all attached observers about a state change.
     */
    public void notifyObservers() {
        for (ErrorObserver observer : observers) {
            observer.update(this);
        }
    }

    /**
     * Sets the error message and notifies observers.
     * @param message the new error message
     */
    public void setErrorMessage(String message) {
        this.errorMessage = message;
        // Notification is done separately as per sequence diagram.
    }

    /**
     * Gets the current error message.
     * @return the error message
     */
    public String getErrorMessage() {
        return errorMessage;
    }
}