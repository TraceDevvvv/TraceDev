package com.example.presentation;

/**
 * View for displaying general notifications (success, error, info).
 */
public class NotificationView implements IView {

    @Override
    public void display(MenuViewModel viewModel) {
        System.out.println("--- Notification ---");
        System.out.println("Message: " + viewModel.getMessage());
    }

    @Override
    public void displayError(String message) {
        System.err.println("--- ERROR Notification ---");
        System.err.println("Error: " + message);
    }

    @Override
    public void displaySuccess(String message) {
        System.out.println("--- SUCCESS Notification ---");
        System.out.println("Success: " + message);
    }

    @Override
    public void displayMessage(String message) {
        System.out.println("--- GENERAL Message Notification ---");
        System.out.println("Message: " + message);
    }
}