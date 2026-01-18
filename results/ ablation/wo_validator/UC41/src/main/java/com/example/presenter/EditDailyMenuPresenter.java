package com.example.presenter;

/**
 * Presenter implementation for edit daily menu use case.
 * In a real application, this would interact with UI; here we simulate with console output.
 */
public class EditDailyMenuPresenter implements EditDailyMenuOutputPort {
    @Override
    public void presentSuccess(String message) {
        System.out.println("SUCCESS: " + message);
    }

    @Override
    public void presentError(String errorMessage) {
        System.out.println("ERROR: " + errorMessage);
    }

    @Override
    public boolean presentConfirmation(String message) {
        // Simulate confirmation; in real scenario, this would be a UI dialog.
        // For simplicity, we assume operator confirms by returning true.
        // To simulate cancellation, we could add logic based on input; here we always confirm.
        System.out.println("CONFIRMATION: " + message + " (Assuming Operator confirms)");
        return true;
        // To simulate cancellation, return false.
    }
}