package com.example.presenter;

/**
 * Output port for presenting results of edit daily menu use case.
 */
public interface EditDailyMenuOutputPort {
    void presentSuccess(String message);
    void presentError(String errorMessage);
    boolean presentConfirmation(String message); // Returns true if confirmed, false if cancelled
}