
package com.example.newsapp.presentation;

import com.example.newsapp.application.usecase.DeleteNewsUseCase;
import com.example.newsapp.domain.model.News;
import com.example.newsapp.infrastructure.exception.NetworkException;
import com.example.newsapp.infrastructure.service.SystemNotificationService;

import java.util.List;
import java.util.Scanner; // For simulating user input

// Assuming there's a test-specific interface or class for DeleteNewsUseCase
// that exposes the setSimulateNetworkFailure method for testing/simulation purposes.
// This is a common pattern for allowing test-specific configuration on dependencies.
// The exact package for TestDeleteNewsUseCase might vary, but a sibling or sub-package of the
// original DeleteNewsUseCase is a reasonable assumption.
// FIX: Changed import to reflect TestDeleteNewsUseCase as a nested type of DeleteNewsUseCase.
import com.example.newsapp.application.usecase.DeleteNewsUseCase.TestDeleteNewsUseCase;

/**
 * Presentation layer controller for managing news operations, specifically deletion.
 * Acts as the "News UI" participant in the sequence diagram.
 * Delegates business logic to DeleteNewsUseCase and notifications to SystemNotificationService.
 */
public class NewsController {
    private DeleteNewsUseCase deleteNewsUseCase;
    private SystemNotificationService systemNotificationService; // Supports REQ-EX-1, REQ-EX-2, REQ-EX-3

    private String selectedNewsId; // State to hold the news selected for deletion

    /**
     * Constructs a NewsController with necessary dependencies.
     * @param deleteNewsUseCase The use case for deleting news.
     * @param systemNotificationService The service for displaying system notifications.
     */
    public NewsController(DeleteNewsUseCase deleteNewsUseCase, SystemNotificationService systemNotificationService) {
        this.deleteNewsUseCase = deleteNewsUseCase;
        this.systemNotificationService = systemNotificationService;
    }

    /**
     * Initiates the delete news process. This is the entry point for the "Agency Operator".
     * Supports REQ-FE-1.
     */
    public void startDeleteNewsProcess() {
        System.out.println("\n--- Starting Delete News Process ---");
        // Step 1: Agency Operator activates deletion function (simulated by calling this method)
        System.out.println("UI: Agency Operator activated deletion function.");
        displayNewsList(); // Step 2: System requests and displays all news
    }

    /**
     * Retrieves and displays the list of news articles.
     * @return A list of News objects.
     */
    public List<News> displayNewsList() {
        System.out.println("UI: Requesting news list for display...");
        List<News> newsList = deleteNewsUseCase.getNewsList(); // UI -> DNU: getNewsList()
        System.out.println("\n--- Current News List ---");
        if (newsList.isEmpty()) {
            System.out.println("No news articles available.");
        } else {
            for (News news : newsList) {
                System.out.println("  ID: " + news.getId() + ", Title: " + news.getTitle());
            }
        }
        System.out.println("-------------------------");
        return newsList;
    }

    /**
     * Selects a news article by its ID for potential deletion.
     * @param newsId The ID of the news article to select.
     */
    public void selectNews(String newsId) {
        // Step 3: Agency Operator selects a news from the list
        this.selectedNewsId = newsId;
        System.out.println("UI: News with ID '" + newsId + "' selected.");
    }

    /**
     * Submits the selected news for deletion, prompting for confirmation.
     * @param newsId The ID of the news article to be deleted.
     */
    public void submitDeletion(String newsId) {
        // Step 4: Agency Operator submits the form
        System.out.println("UI: Deletion request submitted for news ID: " + newsId);
        requestConfirmation(newsId); // UI --> AO: requestConfirmation(newsId)
    }

    /**
     * Confirms the deletion of a news article.
     * @param newsId The ID of the news article to confirm deletion for.
     */
    public void confirmDeletion(String newsId) {
        // Step 6: Agency Operator confirms the deletion
        System.out.println("UI: Deletion confirmed by Agency Operator for news ID: " + newsId);
        try {
            // UI -> DNU: deleteNews(newsId)
            boolean success = deleteNewsUseCase.deleteNews(newsId);
            if (success) {
                // Deletion successful
                notifySuccess("News '" + newsId + "' deleted successfully."); // UI --> AO: notifySuccess(...)
                System.out.println("UI: News list updated after deletion (re-displaying for context):");
                displayNewsList(); // Re-displaying list after deletion for user feedback
            } else {
                // Deletion failed for other reasons (e.g., news not found)
                notifyError("Deletion of news '" + newsId + "' failed. News might not exist.");
            }
        } catch (NetworkException e) {
            // Network connection interrupted (Supports REQ-EX-3)
            notifyError("Connection to server interrupted. Please try again. Details: " + e.getMessage()); // UI --> AO: notifyError(...)
        } finally {
            this.selectedNewsId = null; // Clear selected news after operation
        }
    }

    /**
     * Cancels the current operation.
     */
    public void cancelOperation() {
        // Agency Operator cancels operation
        System.out.println("UI: Agency Operator cancelled the operation.");
        notifyCancellation("Deletion cancelled."); // UI --> AO: notifyCancellation(...)
        this.selectedNewsId = null; // Clear selected news
    }

    /**
     * Delegates success notification to the SystemNotificationService.
     * @param message The success message.
     */
    public void notifySuccess(String message) {
        systemNotificationService.notifySuccess(message); // Delegates to systemNotificationService
    }

    /**
     * Delegates cancellation notification to the SystemNotificationService.
     * @param message The cancellation message.
     */
    public void notifyCancellation(String message) {
        systemNotificationService.notifyCancellation(message); // Delegates to systemNotificationService
    }

    /**
     * Delegates error notification to the SystemNotificationService.
     * Supports REQ-EX-3, delegates to systemNotificationService.
     * @param message The error message.
     */
    public void notifyError(String message) {
        systemNotificationService.notifyError(message); // Delegates to systemNotificationService
    }

    /**
     * Requests confirmation from the Agency Operator for a deletion.
     * @param newsId The ID of the news article requiring confirmation.
     */
    public void requestConfirmation(String newsId) {
        // Step 5: System asks for confirmation of the transaction
        System.out.println("\nUI: Requesting confirmation for deleting news ID: " + newsId + ".");
        System.out.println("UI: Do you really want to delete this news? (yes/no)");
    }

    /**
     * Simulates user interaction for the delete news process.
     * This method acts as the "Agency Operator" interacting with the UI (NewsController).
     * @param scanner A Scanner object for user input.
     * @param simulateNetworkFailure A flag to introduce a network exception.
     */
    public void simulateUserInteraction(Scanner scanner, boolean simulateNetworkFailure) {
        // This part acts as the "Agency Operator" (AO) in the sequence diagram.
        // Precondition: Agency Operator is logged in (simulated by directly calling this method).

        startDeleteNewsProcess(); // AO -> UI : startDeleteNewsProcess()

        List<News> newsList = displayNewsList(); // UI -> AO : displayNewsList(newsList)

        if (newsList.isEmpty()) {
            System.out.println("No news to delete. Exiting simulation.");
            return;
        }

        System.out.print("AO: Enter the ID of the news to delete (e.g., news1, news2, news3): ");
        String selectedId = scanner.nextLine();
        selectNews(selectedId); // AO -> UI : selectNews(newsId)

        submitDeletion(selectedId); // AO -> UI : submitDeletion(newsId)

        // UI --> AO : requestConfirmation(newsId) (printed by requestConfirmation method)
        System.out.print("AO: Confirm deletion? (yes/no/cancel): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if ("yes".equals(confirmation)) {
            // Introduce network failure here if requested
            // The DeleteNewsUseCase needs to expose a public method to set this flag on its internal repository.
            // FIX: Cast deleteNewsUseCase to TestDeleteNewsUseCase (or equivalent) if it supports simulation.
            // Corrected type reference to DeleteNewsUseCase.TestDeleteNewsUseCase.
            if (deleteNewsUseCase instanceof DeleteNewsUseCase.TestDeleteNewsUseCase) {
                ((DeleteNewsUseCase.TestDeleteNewsUseCase) deleteNewsUseCase).setSimulateNetworkFailure(simulateNetworkFailure);
            } else {
                // If the injected DeleteNewsUseCase does not support simulation, print a warning.
                System.err.println("Warning: The provided DeleteNewsUseCase does not implement TestDeleteNewsUseCase " +
                                   "and thus cannot simulate network failure for this interaction.");
            }
            confirmDeletion(selectedId); // AO -> UI : confirmDeletion(newsId)
        } else if ("no".equals(confirmation) || "cancel".equals(confirmation)) {
            cancelOperation(); // AO -> UI : cancelOperation()
        } else {
            notifyError("Invalid confirmation input. Operation aborted.");
            this.selectedNewsId = null;
        }

        System.out.println("\n--- Delete News Process Finished ---");
    }
}
