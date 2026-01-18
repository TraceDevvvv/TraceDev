package com.example.presentation;

/**
 * View for displaying the day selection form.
 */
public class DailyMenuFormView implements IView {

    @Override
    public void display(MenuViewModel viewModel) {
        System.out.println("--- Daily Menu Selection Form ---");
        System.out.println("Please select a day to edit the menu.");
        System.out.println("Current message: " + (viewModel.getMessage() != null ? viewModel.getMessage() : "N/A"));
        // In a real GUI, this would populate a dropdown or list of days.
    }

    @Override
    public void displayError(String message) {
        System.err.println("ERROR in DailyMenuFormView: " + message);
    }

    @Override
    public void displaySuccess(String message) {
        System.out.println("SUCCESS in DailyMenuFormView: " + message);
    }

    @Override
    public void displayMessage(String message) {
        System.out.println("MESSAGE in DailyMenuFormView: " + message);
    }
}