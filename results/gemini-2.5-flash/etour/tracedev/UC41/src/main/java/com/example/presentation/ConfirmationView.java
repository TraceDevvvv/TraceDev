package com.example.presentation;

/**
 * View for displaying a confirmation prompt.
 */
public class ConfirmationView implements IView {

    @Override
    public void display(MenuViewModel viewModel) {
        System.out.println("--- Confirmation Required ---");
        System.out.println("Are you sure you want to save the following menu for " + viewModel.getSelectedDay() + "?");
        viewModel.getMenuItems().forEach(item -> System.out.println("  - " + item.getName() + " (" + item.getPrice() + ")"));
        System.out.println("Current message: " + (viewModel.getMessage() != null ? viewModel.getMessage() : "N/A"));
        System.out.println("Please confirm or cancel.");
    }

    @Override
    public void displayError(String message) {
        System.err.println("ERROR in ConfirmationView: " + message);
    }

    @Override
    public void displaySuccess(String message) {
        System.out.println("SUCCESS in ConfirmationView: " + message);
    }

    @Override
    public void displayMessage(String message) {
        System.out.println("MESSAGE in ConfirmationView: " + message);
    }
}