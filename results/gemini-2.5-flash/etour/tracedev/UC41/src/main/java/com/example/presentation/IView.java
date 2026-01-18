package com.example.presentation;

/**
 * Interface for all views in the presentation layer.
 */
public interface IView {
    void display(MenuViewModel viewModel);
    void displayError(String message);
    void displaySuccess(String message);
    void displayMessage(String message);
}