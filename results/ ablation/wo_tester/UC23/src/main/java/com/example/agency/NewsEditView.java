package com.example.agency;

import java.util.List;
import java.util.Scanner;

/**
 * View for editing news articles.
 */
public class NewsEditView {
    private EditNewsController controller;
    private Scanner scanner;

    public NewsEditView(EditNewsController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
        controller.setNewsEditView(this);
    }

    // Step 5 & 6: Display form with news data
    public void displayForm(NewsFormData newsData) {
        if (newsData != null) {
            System.out.println("\n=== Edit News ===");
            System.out.println("Current Title: " + newsData.getTitle());
            System.out.println("Current Content: " + newsData.getContent());
            System.out.println("Current Author: " + newsData.getAuthor());
            System.out.println("==================\n");
        } else {
            System.out.println("No news data to display.");
        }
    }

    // Step 7: change data in form
    public void changeDataInForm() {
        System.out.println("Changing data in the form.");
    }

    // Step 8: submit form (simulated)
    public void submitForm(NewsFormData editedData) {
        System.out.println("Submitting form...");
        boolean success = controller.submitEdits(editedData);
        if (success) {
            System.out.println("Form submitted successfully.");
        } else {
            System.out.println("Form submission failed.");
        }
    }

    // Step 10: Ask for confirmation
    public boolean showConfirmation() {
        System.out.print("Confirm changes? (yes/no): ");
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }

    // Step 12: Show success message
    public void showSuccess() {
        System.out.println("News article updated successfully!");
    }

    // Step 12: notify successful amendment
    public void notifySuccessfulAmendment() {
        System.out.println("News article amended successfully.");
    }

    // Show error messages
    public void showError(List<String> errors) {
        System.out.println("Errors found:");
        for (String error : errors) {
            System.out.println("- " + error);
        }
    }

    // display error messages
    public void displayErrorMessages() {
        System.out.println("Error messages displayed.");
    }

    // cancel editing
    public void cancelEditing() {
        System.out.println("Editing cancelled.");
        controller.cancelOperation();
    }

    // operation cancelled
    public void operationCancelled() {
        System.out.println("Operation cancelled.");
    }

    // display connection error
    public void displayConnectionError() {
        System.out.println("Connection lost to server ETOUR.");
    }

    public void closeForm() {
        System.out.println("Form closed.");
    }

    public void showConnectionError() {
        System.out.println("Connection to server lost.");
    }
}