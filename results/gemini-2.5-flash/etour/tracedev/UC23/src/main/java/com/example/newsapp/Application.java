package com.example.newsapp;

import com.example.newsapp.repo.INewsRepository;
import com.example.newsapp.repo.NewsRepositoryImpl;
import com.example.newsapp.service.NewsService;
import com.example.newsapp.service.NewsValidator;
import com.example.newsapp.ui.NewsController;
import com.example.newsapp.ui.NewsForm;

import java.util.Scanner;

/**
 * Main application class to bootstrap and run the News Management System.
 * This class sets up the dependencies and simulates the user interaction.
 * It represents the entry point for the "Agency Operator" interacting with the system.
 */
public class Application {
    public static void main(String[] args) {
        // --- Dependency Injection / Setup ---
        Scanner scanner = new Scanner(System.in);

        // Data Access Layer
        INewsRepository newsRepository = new NewsRepositoryImpl();

        // Application Layer
        NewsValidator newsValidator = new NewsValidator();
        NewsService newsService = new NewsService(newsRepository, newsValidator);

        // Presentation Layer
        NewsForm newsForm = new NewsForm(scanner);
        NewsController newsController = new NewsController(newsService, newsForm);

        // --- Simulate User Interaction ---
        System.out.println("News Management System - Agency Operator Mode");
        System.out.println("Precondition: Agency Operator is logged in."); // Corresponds to m1

        while (true) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Activate News Editing (View All News)");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    // REQ: Flow of Events 1: AO -> Form : onActivateEditing() (Corresponds to m2)
                    newsForm.onActivateEditing();

                    // After displaying all news, prompt for selection
                    System.out.print("\nEnter ID of news to edit (or 'cancel' to return to menu): ");
                    String newsIdToEdit = scanner.nextLine();

                    if ("cancel".equalsIgnoreCase(newsIdToEdit)) {
                        newsForm.onCancelOperation(); // Simulates cancelling initial view
                        break;
                    }

                    // REQ: Flow of Events 3 & 4: AO -> Form : onNewsSelected() / onSubmitNewsSelection() (Corresponds to m12)
                    newsForm.onNewsSelected(newsIdToEdit); // Simulate selection
                    newsForm.onSubmitNewsSelection(newsIdToEdit); // Simulate submission for editing

                    // After displaying for edit, allow modification and submission
                    // newsForm.newsData will be set by newsController.editNews() -> newsForm.displayNewsForEdit()
                    if (newsForm.newsData != null && newsForm.newsData.getId() != null && newsForm.newsData.getId().equals(newsIdToEdit)) {
                        System.out.println("-----------------------------------");
                        System.out.println("Options for news ID " + newsIdToEdit + ":");
                        System.out.println("  a. Submit Modified Data");
                        System.out.println("  b. Cancel Operation");
                        System.out.print("Enter choice (a/b): ");
                        String editChoice = scanner.nextLine();

                        if ("a".equalsIgnoreCase(editChoice)) {
                            // REQ: Flow of Events 8: AO -> Form : onSubmitModifiedData() (Corresponds to m24)
                            newsForm.onSubmitModifiedData();

                            // After onSubmitModifiedData(), NewsController.submitNewsEdit() was called.
                            // If validation succeeded, NewsController.pendingNewsUpdate will be set
                            // and NewsForm.askConfirmation() would have been called (just printing the message, corresponds to m32).
                            // Now, Application.java (simulating AO) needs to provide confirmation.
                            if (newsController.getPendingNewsUpdate() != null) {
                                System.out.print("Confirm changes? (yes/no): ");
                                String confirmChoice = scanner.nextLine();
                                if ("yes".equalsIgnoreCase(confirmChoice.trim())) {
                                    // REQ: Flow of Events 11: AO -> Form : onConfirmOperation() (Corresponds to m33)
                                    newsForm.onConfirmOperation();
                                } else {
                                    // REQ: Exit Conditions: The Agency Operator cancels the operation.
                                    newsForm.onCancelOperation();
                                }
                            } else {
                                // If pendingNewsUpdate is null, it means validation failed, or no data was submitted.
                                // An error message should have already been displayed by NewsForm.showError() (Corresponds to m31, activates Errored).
                                System.out.println("No confirmation prompt, likely due to validation errors or internal state.");
                            }
                        } else { // Choice "b" or any other input
                            // REQ: Exit Conditions: The Agency Operator cancels the operation.
                            newsForm.onCancelOperation();
                        }
                    } else {
                        // This case handles if newsIdToEdit was not found or displayNewsForEdit failed
                        System.out.println("No news item loaded for further action or previous error occurred.");
                        newsForm.onCancelOperation(); // Clean up state if anything was pending
                    }
                    break;
                case "2":
                    System.out.println("Exiting News Management System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}