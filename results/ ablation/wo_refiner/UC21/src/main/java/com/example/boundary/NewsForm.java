package com.example.boundary;

import com.example.controller.NewsController;
import com.example.request.InsertNewsRequest;
import com.example.response.InsertNewsResponse;

import java.util.Map;
import java.util.UUID;

/**
 * Boundary class representing the UI form for inserting news.
 * Communicates with the NewsController.
 */
public class NewsForm {

    private NewsController controller;

    public NewsForm(NewsController controller) {
        this.controller = controller;
    }

    /**
     * Displays the news insertion form to the user.
     * System-initiated display as per requirement REQ-007.
     */
    public void displayForm() {
        System.out.println("News Form displayed.");
        // In a real application, this would render the UI.
    }

    /**
     * Submits the filled form data to the controller.
     * @param formData Map containing title, content, and author.
     */
    public void submit(Map<String, String> formData) {
        InsertNewsRequest request = new InsertNewsRequest(
                formData.get("title"),
                formData.get("content"),
                formData.get("author")
        );
        InsertNewsResponse response = controller.insertNews(request);
        // Display result to user (simplified)
        if (response.isSuccess()) {
            System.out.println("Operation succeeded: " + response.getMessage());
        } else {
            System.out.println("Operation failed: " + response.getMessage());
        }
    }

    /**
     * Shows a confirmation dialog to the user and returns the user's choice.
     * @return true if user confirms, false if user cancels.
     */
    public boolean showConfirmationDialog() {
        System.out.println("Confirmation dialog displayed. (Simulating user confirmation)");
        // For simplicity, we assume the user always confirms.
        return true;
    }

    /**
     * Cancels the current operation.
     */
    public void cancel() {
        boolean cancelled = controller.cancel(UUID.randomUUID().toString());
        if (cancelled) {
            System.out.println("Operation cancelled.");
        }
    }

    /**
     * Shows an error form to the user.
     */
    public void showErrorForm() {
        System.out.println("Error form displayed.");
        // In a real application, this would render an error UI.
    }
}