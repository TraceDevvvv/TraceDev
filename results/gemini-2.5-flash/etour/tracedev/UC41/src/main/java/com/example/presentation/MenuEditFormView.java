package com.example.presentation;

/**
 * View for displaying and editing the daily menu.
 */
public class MenuEditFormView implements IView {

    @Override
    public void display(MenuViewModel viewModel) {
        System.out.println("--- Menu Editing Form for " + viewModel.getSelectedDay() + " ---");
        if (viewModel.getMenuItems().isEmpty()) {
            System.out.println("No menu items for this day. Add new ones!");
        } else {
            System.out.println("Current Menu Items:");
            viewModel.getMenuItems().forEach(item -> System.out.println("  - " + item.getName() + " (" + item.getPrice() + ")"));
        }
        System.out.println("Current message: " + (viewModel.getMessage() != null ? viewModel.getMessage() : "N/A"));
        // In a real GUI, this would populate form fields for menu items.
    }

    @Override
    public void displayError(String message) {
        System.err.println("ERROR in MenuEditFormView: " + message);
    }

    @Override
    public void displaySuccess(String message) {
        System.out.println("SUCCESS in MenuEditFormView: " + message);
    }

    @Override
    public void displayMessage(String message) {
        System.out.println("MESSAGE in MenuEditFormView: " + message);
    }
}