package com.example.etour.views;

/**
 * View interface for displaying refreshment point information and interactions.
 */
public interface RefreshmentPointView {
    boolean showConfirmation(String message);
    void showSuccess(String message);
    void showError(String message);
    void showList();
    void onCancel();
    
    // New methods for sequence diagram traceability
    void displaySuccessNotification(String message);
    void displayRefreshedList();
    void displayCancellationMessage(String message);
    void displayErrorMessage(String message);
}