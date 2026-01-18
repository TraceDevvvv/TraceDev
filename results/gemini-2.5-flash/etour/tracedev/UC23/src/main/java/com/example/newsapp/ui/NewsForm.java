
package com.example.newsapp.ui;

import com.example.newsapp.dto.NewsDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.util.Date;

/**
 * Represents the News Form, acting as a View/ViewModel in the Presentation Layer.
 * It's responsible for displaying information to the user and capturing user input.
 * All user interaction methods are simulated using console I/O for this example.
 */
public class NewsForm {
    private NewsController newsController;
    public NewsDTO newsData; // Stores the news data currently being edited/displayed
    public String statusMessage; // Stores messages to be displayed to the user
    private final Scanner scanner;

    /**
     * Constructor for NewsForm.
     *
     * @param scanner A Scanner instance for console input.
     */
    public NewsForm(Scanner scanner) {
        this.scanner = scanner;
        this.newsData = new NewsDTO(); // Initialize with an empty DTO
        this.statusMessage = "";
    }

    /**
     * Sets the NewsController for this form.
     * This is typically done during application startup (e.g., in a main method).
     *
     * @param newsController The controller managing this form.
     */
    public void setNewsController(NewsController newsController) {
        this.newsController = newsController;
    }

    /**
     * Displays a list of news items to the Agency Operator.
     * Corresponds to REQ: Flow of Events 2.
     *
     * @param newsList A list of NewsDTOs to display.
     */
    public void displayNewsList(List<NewsDTO> newsList) {
        System.out.println("\n--- Available News Items ---");
        if (newsList == null || newsList.isEmpty()) {
            System.out.println("No news items found.");
            return;
        }
        for (NewsDTO news : newsList) {
            System.out.printf("ID: %s | Title: %s | Author: %s | Status: %s%n",
                    news.getId(), news.getTitle(), news.getAuthor(), news.getStatus());
        }
        System.out.println("--------------------------");
    }

    /**
     * Displays a specific news item's details for editing.
     * Corresponds to REQ: Flow of Events 6.
     *
     * @param news The NewsDTO to display for editing.
     */
    public void displayNewsForEdit(NewsDTO news) {
        this.newsData = news; // Store the news being edited
        System.out.println("\n--- Editing News Item (ID: " + news.getId() + ") ---");
        System.out.println("Current Title: " + news.getTitle());
        System.out.println("Current Content: " + news.getContent());
        System.out.println("Current Publication Date: " + (news.getPublicationDate() != null ? new SimpleDateFormat("yyyy-MM-dd").format(news.getPublicationDate()) : "N/A"));
        System.out.println("Current Author: " + news.getAuthor());
        System.out.println("Current Status: " + news.getStatus());
        System.out.println("-----------------------------------");
        System.out.println("You can now modify the data. After modification, select 'Submit'.");
    }

    /**
     * Simulates getting modified news data from the form.
     * In a real UI, this would read from input fields. Here, we prompt the user.
     * Corresponds to REQ: Flow of Events 7 (Agency Operator changes data).
     *
     * @return A NewsDTO with potentially modified data.
     */
    public NewsDTO getEditedNewsData() {
        if (this.newsData == null) {
            System.err.println("Error: No news item loaded for editing.");
            return null;
        }

        NewsDTO modifiedNews = new NewsDTO(
            this.newsData.getId(),
            this.newsData.getTitle(),
            this.newsData.getContent(),
            this.newsData.getPublicationDate(),
            this.newsData.getAuthor(),
            this.newsData.getStatus()
        );

        System.out.println("\nEnter new values (leave blank to keep current):");

        System.out.print("New Title [" + modifiedNews.getTitle() + "]: ");
        String newTitle = scanner.nextLine();
        if (!newTitle.trim().isEmpty()) {
            modifiedNews.setTitle(newTitle);
        }

        System.out.print("New Content [" + modifiedNews.getContent() + "]: ");
        String newContent = scanner.nextLine();
        if (!newContent.trim().isEmpty()) {
            modifiedNews.setContent(newContent);
        }

        System.out.print("New Publication Date (yyyy-MM-dd) [" + (modifiedNews.getPublicationDate() != null ? new SimpleDateFormat("yyyy-MM-dd").format(modifiedNews.getPublicationDate()) : "N/A") + "]: ");
        String newDateStr = scanner.nextLine();
        if (!newDateStr.trim().isEmpty()) {
            try {
                modifiedNews.setPublicationDate(new SimpleDateFormat("yyyy-MM-dd").parse(newDateStr));
            } catch (ParseException e) {
                System.err.println("Invalid date format. Keeping current date.");
            }
        }

        System.out.print("New Author [" + modifiedNews.getAuthor() + "]: ");
        String newAuthor = scanner.nextLine();
        if (!newAuthor.trim().isEmpty()) {
            modifiedNews.setAuthor(newAuthor);
        }

        System.out.print("New Status (Published/Draft/Archived) [" + modifiedNews.getStatus() + "]: ");
        String newStatus = scanner.nextLine();
        if (!newStatus.trim().isEmpty()) {
            modifiedNews.setStatus(newStatus);
        }

        this.newsData = modifiedNews; // Update the form's internal data
        return modifiedNews;
    }

    /**
     * Displays an error message to the user.
     * Corresponds to REQ: Flow of Events 10 (activates Errored).
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        this.statusMessage = "ERROR: " + message;
        System.err.println(this.statusMessage);
    }

    /**
     * Displays a success message to the user.
     * Corresponds to REQ: Exit Conditions: Successful amendment.
     *
     * @param message The success message to display.
     */
    public void showSuccess(String message) {
        this.statusMessage = "SUCCESS: " + message;
        System.out.println(this.statusMessage);
        this.newsData = null; // Clear form data after successful operation
    }

    /**
     * Asks the Agency Operator for confirmation of an operation.
     * Corresponds to REQ: Flow of Events 10.
     *
     * @param message The confirmation message to display.
     */
    public void askConfirmation(String message) {
        System.out.println("\nCONFIRMATION: " + message);
        // This method now only displays the message.
        // The actual user input for confirmation (yes/no) is handled by the Application class
        // which then calls onConfirmOperation() or onCancelOperation().
    }

    /**
     * Handler for activating the editing process.
     * Corresponds to REQ: Flow of Events 1.
     * Calls the controller to view all news.
     */
    public void onActivateEditing() {
        System.out.println("\n[Form] Activating News Editing Mode...");
        if (newsController != null) {
            newsController.viewAllNews();
        } else {
            System.err.println("Error: NewsController not set in NewsForm.");
        }
    }

    /**
     * Handler for when a news item is visually selected (e.g., clicked in a list).
     * This method primarily logs the selection for this simulation.
     * Corresponds to REQ: Flow of Events 3.
     * @param newsId The ID of the selected news item.
     */
    public void onNewsSelected(String newsId) {
        System.out.println("[Form] News selected visually: " + newsId);
        // In a real UI, this might highlight the item, but for console it's a log.
    }

    /**
     * Handler for submitting the selection of a news item.
     * Corresponds to REQ: Flow of Events 4.
     * Calls the controller to initiate editing for the selected news.
     * @param newsId The ID of the news item to submit for editing.
     */
    public void onSubmitNewsSelection(String newsId) {
        System.out.println("[Form] Submitting news selection for editing: " + newsId);
        if (newsController != null) {
            newsController.editNews(newsId);
        } else {
            System.err.println("Error: NewsController not set.");
        }
    }

    /**
     * Handler for submitting modified news data.
     * Corresponds to REQ: Flow of Events 8.
     * Retrieves the edited data and passes it to the controller for validation and update.
     */
    public void onSubmitModifiedData() {
        System.out.println("[Form] Submitting modified news data.");
        if (newsController != null) {
            NewsDTO editedNewsData = getEditedNewsData(); // Get data from 'form'
            if (editedNewsData != null) {
                newsController.submitNewsEdit(editedNewsData);
            }
        } else {
            System.err.println("Error: NewsController not set.");
        }
    }

    /**
     * Handler for confirming an operation.
     * Corresponds to REQ: Flow of Events 11.
     * Calls the controller to proceed with the confirmed update.
     */
    public void onConfirmOperation() {
        System.out.println("[Form] Confirmation received. Proceeding with operation.");
        if (newsController != null) {
            newsController.confirmNewsUpdate();
        } else {
            System.err.println("Error: NewsController not set.");
        }
    }

    /**
     * Handler for canceling an operation.
     * Corresponds to REQ: Exit Conditions: The Agency Operator cancels the operation.
     * Calls the controller to handle the cancellation.
     */
    public void onCancelOperation() {
        System.out.println("[Form] Operation cancelled by Agency Operator.");
        if (newsController != null) {
            newsController.cancelNewsEdit();
        } else {
            System.err.println("Error: NewsController not set.");
        }
        this.newsData = null; // Clear any pending data
        this.statusMessage = "Operation cancelled.";
    }

    // Helper to clear the form's internal newsData
    public void clearForm() {
        this.newsData = new NewsDTO();
        this.statusMessage = "";
    }
}
