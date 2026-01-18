package com.newsagency.system.boundary;

import com.newsagency.system.dto.NewsFormDTO;
import com.newsagency.system.dto.ResponseDTO;
import com.newsagency.system.controller.InsertNewsController;

/**
 * Boundary class representing the UI form for inserting news.
 * This handles user interactions and displays results.
 */
public class InsertNewsForm {
    private InsertNewsController controller;

    public InsertNewsForm() {
        // In a real application, dependency injection would be used.
        this.controller = new InsertNewsController();
    }

    public InsertNewsForm(InsertNewsController controller) {
        this.controller = controller;
    }

    /**
     * Displays the form to the user.
     * In a real UI, this would render the form.
     */
    public void showForm() {
        System.out.println("Displaying Insert News Form...");
        // UI rendering logic would go here.
    }

    /**
     * Submits the form data to the controller.
     * @param newsData the data from the form
     * @return response from the system
     */
    public ResponseDTO submitForm(NewsFormDTO newsData) {
        System.out.println("Form submitted with title: " + newsData.getTitle());
        ResponseDTO response = controller.submitNewsForm(newsData);
        if (response.isSuccess()) {
            displaySuccess("News properly placed");
        } else {
            displayError("Form submission failed: " + response.getErrors());
        }
        return response;
    }

    /**
     * Displays a success message to the user.
     * @param message the success message
     */
    public void displaySuccess(String message) {
        System.out.println("SUCCESS: " + message);
        // In a real UI, show a success notification.
    }

    /**
     * Displays an error message to the user.
     * @param message the error message
     */
    public void displayError(String message) {
        System.out.println("ERROR: " + message);
        // In a real UI, show an error notification.
    }
}