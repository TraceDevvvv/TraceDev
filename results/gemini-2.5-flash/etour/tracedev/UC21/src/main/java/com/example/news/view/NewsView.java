package com.example.news.view;

import com.example.news.dto.NewsFormRequest;

import java.util.Map;
import java.util.Scanner;

/**
 * Handles the user interface for news-related operations.
 * Simulates display and user input via console.
 */
public class NewsView {

    private Scanner scanner;

    public NewsView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays an empty form or a form pre-filled with data from a view model.
     *
     * @param viewModel A map containing data to display in the form (e.g., initial values, error messages).
     */
    public void displayForm(Map<String, Object> viewModel) {
        System.out.println("\n--- Insert New News Article ---");
        System.out.println("Please fill in the details:");

        String title = (String) viewModel.getOrDefault("title", "");
        String content = (String) viewModel.getOrDefault("content", "");
        String authorId = (String) viewModel.getOrDefault("authorId", "");
        String errorMessage = (String) viewModel.getOrDefault("errorMessage", "");

        if (!errorMessage.isEmpty()) {
            System.out.println("\n[ERROR]: " + errorMessage);
        }

        System.out.println("Title: (current: " + title + ")");
        System.out.println("Content: (current: " + content + ")");
        System.out.println("Author ID: (current: " + authorId + ")");
        System.out.println("---------------------------------");
    }

    /**
     * Simulates the Agency Operator filling out the form.
     * This method blocks and waits for user input.
     *
     * @return A NewsFormRequest DTO populated with user input.
     */
    public NewsFormRequest fillsForm() {
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Content: ");
        String content = scanner.nextLine();
        System.out.print("Enter Author ID: ");
        String authorId = scanner.nextLine();

        return new NewsFormRequest(title, content, authorId);
    }

    /**
     * Simulates the Agency Operator submitting the form.
     * In a real UI, this would be a button click. Here it's a prompt.
     */
    public void submitsForm() {
        // In a real UI, this would be a button click.
        // For console, we can just say "submitting..." or prompt for confirmation.
        System.out.println("\nSubmitting news form...");
    }

    /**
     * Displays a confirmation prompt with the news data to be inserted.
     *
     * @param newsData The NewsFormRequest containing the data to confirm.
     */
    public void displayConfirmation(NewsFormRequest newsData) {
        System.out.println("\n--- Confirm News Insertion ---");
        System.out.println("Title: " + newsData.getTitle());
        System.out.println("Content: " + newsData.getContent().substring(0, Math.min(newsData.getContent().length(), 50)) + "...");
        System.out.println("Author ID: " + newsData.getAuthorId());
        System.out.println("\nDo you want to finalize this news insertion? (yes/no)");
    }

    /**
     * Simulates the Agency Operator confirming the insertion.
     * This method blocks and waits for user input.
     *
     * @return true if the operator confirms, false otherwise.
     */
    public boolean confirmInsertion() {
        String response = scanner.nextLine();
        return response.equalsIgnoreCase("yes");
    }

    /**
     * Simulates the Agency Operator canceling the operation.
     * This method blocks and waits for user input.
     *
     * @return true if the operator cancels, false otherwise.
     */
    public boolean cancelOperation() {
        String response = scanner.nextLine(); // Assuming a "cancel" prompt was shown before this call
        return response.equalsIgnoreCase("no");
    }

    /**
     * Displays a success message to the operator.
     *
     * @param message The success message.
     */
    public void displaySuccess(String message) {
        System.out.println("\n[SUCCESS]: " + message);
        System.out.println("---------------------------------");
    }

    /**
     * Displays an error message to the operator.
     *
     * @param errorMessage The error message.
     */
    public void displayError(String errorMessage) {
        System.err.println("\n[ERROR]: " + errorMessage);
        System.out.println("---------------------------------");
    }

    /**
     * Notifies the operator about cancellation.
     */
    public void notifyCancellation() {
        System.out.println("\nOperation cancelled by operator.");
        System.out.println("---------------------------------");
    }

    /**
     * Simulates activating the insert news feature.
     */
    public void activatesInsertNews() {
        System.out.println("Agency Operator activates 'Insert News' feature.");
    }
}