package com.newsapp.boundary;

import com.newsapp.dto.NewsFormData;
import com.newsapp.controller.InsertNewsController;
import java.util.Date;
import java.util.Scanner;

/**
 * Boundary class representing the news entry form.
 * Corresponds to the NewsForm boundary in the class diagram.
 * For demonstration, this is a console‑based simulation.
 */
public class NewsForm {
    private InsertNewsController controller;
    private NewsFormData currentFormData;
    private Scanner scanner = new Scanner(System.in);

    // Constructor with dependency injection
    public NewsForm(InsertNewsController controller) {
        this.controller = controller;
    }

    /**
     * Displays the form (simulated via console).
     * As per sequence diagram step 2.
     */
    public void displayForm() {
        System.out.println("=== Insert News Form ===\n");
        System.out.println("Please fill in the following fields:");
    }

    /**
     * Simulates user filling the form (UI interaction).
     * This method is called by the main program to simulate the actor's input.
     */
    public void fillForm() {
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Content: ");
        String content = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        // For simplicity, publication date is set to current date
        Date pubDate = new Date();
        System.out.println("Publication date set to current date: " + pubDate);

        currentFormData = new NewsFormData(title, content, author, pubDate);
        System.out.println("Form data stored internally.");
    }

    /**
     * Returns the currently stored form data.
     * As per sequence diagram step: getFormData()
     * @return the form data
     */
    public NewsFormData getFormData() {
        return currentFormData;
    }

    /**
     * Simulates form submission (as per sequence diagram step 4).
     */
    public void submitForm() {
        System.out.println("\n[Form submitted]");
        if (currentFormData == null) {
            System.out.println("No form data available.");
            return;
        }
        // Call the controller's execute method
        controller.execute(currentFormData);
    }

    /**
     * Shows a confirmation prompt to the user.
     * As per sequence diagram step: showConfirmationPrompt(...)
     * @param message the confirmation message
     * @return true if user confirms, false otherwise
     */
    public boolean showConfirmationPrompt(String message) {
        System.out.print(message + " (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

    /**
     * Shows a success notification.
     * @param message the success message
     */
    public void showSuccessNotification(String message) {
        System.out.println("[SUCCESS] " + message);
    }

    /**
     * Shows an error notification.
     * @param message the error message
     */
    public void showErrorNotification(String message) {
        System.out.println("[ERROR] " + message);
    }
}