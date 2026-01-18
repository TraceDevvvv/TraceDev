package com.example.presenter;

/**
 * Interface for presenting password change results.
 */
public interface PasswordPresenter {
    void displayError(String message);
    void displaySuccess();
}