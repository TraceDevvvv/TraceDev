package com.example.view;

import com.example.controller.NewsController;
import com.example.dto.NewsDTO;
import com.example.model.OperationResult;

/**
 * Boundary class representing the edit form UI.
 */
public class NewsEditForm {
    private NewsController controller;
    private NewsDTO currentNews;

    public NewsEditForm(NewsController controller) {
        this.controller = controller;
    }

    public void populateForm(NewsDTO newsData) {
        this.currentNews = newsData;
        System.out.println("Form populated with news: " + newsData.getTitle());
    }

    public NewsDTO getFormData() {
        // In a real UI, this would collect data from input fields.
        // Here we just return the current DTO.
        return currentNews;
    }

    public boolean displayConfirmationPrompt() {
        System.out.println("Displaying confirmation prompt: Are you sure you want to save changes?");
        return true; // Simulates user confirmation
    }

    public boolean confirmOperation() {
        System.out.println("Confirming operation...");
        return displayConfirmationPrompt();
    }

    public void displaySuccessMessage() {
        System.out.println("Success: News was updated successfully.");
    }

    public void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    public void cancel() {
        System.out.println("Cancelling operation...");
        controller.cancelEdit();
    }
}