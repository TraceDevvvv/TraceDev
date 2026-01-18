package com.example.newsapp.ui;

import com.example.newsapp.common.ConnectionException;
import com.example.newsapp.dto.NewsDTO;
import com.example.newsapp.service.NewsService;

import java.util.List;

/**
 * NewsController acts as the intermediary between the NewsForm (View) and NewsService (Application Layer).
 * It handles user requests from the form, coordinates with the service layer, and updates the form.
 * It also manages the flow of events as defined in the sequence diagram.
 * Note: Requires authenticated user session (simulated).
 */
public class NewsController {
    private final NewsService newsService;
    private final NewsForm newsForm;

    // A temporary store for the NewsDTO being edited, to be used after confirmation.
    // This addresses the sequence diagram's flow where validation happens, then confirmation,
    // and then the actual update. The newsForm.newsData holds the data.
    private NewsDTO pendingNewsUpdate;

    /**
     * Constructor for NewsController.
     * Initializes with instances of NewsService and NewsForm.
     *
     * @param newsService The service layer component for news operations.
     * @param newsForm The presentation layer component for displaying news and capturing input.
     */
    public NewsController(NewsService newsService, NewsForm newsForm) {
        this.newsService = newsService;
        this.newsForm = newsForm;
        this.newsForm.setNewsController(this); // Inject controller into form
    }

    /**
     * Returns the NewsDTO that is currently pending an update confirmation.
     * This is used by the Application layer to determine if a confirmation prompt is needed.
     * @return The NewsDTO pending update, or null if no update is pending.
     */
    public NewsDTO getPendingNewsUpdate() {
        return pendingNewsUpdate;
    }

    /**
     * Handles the request to view all news items.
     * Corresponds to REQ: Flow of Events 2.
     *
     * The sequence:
     * NewsForm (onActivateEditing) -> NewsController (viewAllNews)
     * NewsController -> NewsService (getAllNews)
     * NewsService -> INewsRepository (findAll)
     * (Error handling for ConnectionException)
     * NewsService -> NewsController (newsListDTO)
     * NewsController -> NewsForm (displayNewsList)
     */
    public void viewAllNews() {
        System.out.println("\n[Controller] Receiving request to view all news.");
        try {
            List<NewsDTO> newsList = newsService.getAllNews();
            newsForm.displayNewsList(newsList);
        } catch (ConnectionException e) {
            newsForm.showError("Failed to retrieve news: " + e.getMessage());
        }
    }

    /**
     * Handles the request to edit a specific news item.
     * Corresponds to REQ: Flow of Events 5.
     *
     * The sequence:
     * NewsForm (onSubmitNewsSelection) -> NewsController (editNews)
     * NewsController -> NewsService (getNewsDetails)
     * NewsService -> INewsRepository (findById)
     * (Error handling for ConnectionException)
     * NewsService -> NewsController (newsDTO)
     * NewsController -> NewsForm (displayNewsForEdit)
     *
     * @param newsId The ID of the news item to edit.
     */
    public void editNews(String newsId) {
        System.out.println("\n[Controller] Receiving request to edit news with ID: " + newsId);
        try {
            NewsDTO newsToEdit = newsService.getNewsDetails(newsId);
            if (newsToEdit != null) {
                newsForm.displayNewsForEdit(newsToEdit);
                // The newsForm.newsData is implicitly updated by displayNewsForEdit
                // We don't store it in controller's pendingNewsUpdate yet, until submission.
            } else {
                newsForm.showError("News item with ID " + newsId + " not found.");
            }
        } catch (ConnectionException e) {
            newsForm.showError("Failed to load news details for edit: " + e.getMessage());
        }
    }

    /**
     * Handles the submission of modified news data from the form.
     * This method primarily performs validation and prompts for confirmation.
     * Corresponds to REQ: Flow of Events 8, 9, 10.
     *
     * The sequence:
     * NewsForm (onSubmitModifiedData) -> NewsController (submitNewsEdit)
     * NewsController -> NewsService (validateNewsData)
     * NewsService -> NewsValidator (isValid)
     * (If valid) NewsService -> NewsController (true)
     * NewsController -> NewsForm (askConfirmation)
     * (If invalid) NewsService -> NewsController (false)
     * NewsController -> NewsForm (showError)
     *
     * @param newsDTO The NewsDTO containing the modified data from the form.
     */
    public void submitNewsEdit(NewsDTO newsDTO) {
        System.out.println("\n[Controller] Receiving submitted modified news data for ID: " + newsDTO.getId());

        // Perform validation
        if (newsService.validateNewsData(newsDTO)) {
            // If validation is successful, store the DTO locally until confirmed.
            this.pendingNewsUpdate = newsDTO;
            // Ask for confirmation (NewsForm.askConfirmation now only displays the message)
            newsForm.askConfirmation("News data is valid. Confirm changes?");
            // The flow continues based on user's action (onConfirmOperation or onCancelOperation)
        } else {
            // If validation fails, show error. Corresponds to REQ: Flow of Events 10 (activates Errored)
            newsForm.showError("Invalid data. Please check the entered information.");
            this.pendingNewsUpdate = null; // Clear pending update as it's invalid
        }
    }

    /**
     * Handles the confirmation of a news update operation.
     * Corresponds to REQ: Flow of Events 11, 12 and successful amendment exit condition.
     *
     * The sequence:
     * NewsForm (onConfirmOperation) -> NewsController (confirmNewsUpdate)
     * NewsController -> NewsService (updateNews)
     * NewsService -> INewsRepository (update)
     * (Error handling for ConnectionException)
     * NewsService -> NewsController (true/false)
     * (If true) NewsController -> NewsForm (showSuccess)
     * (If false) NewsController -> NewsForm (showError)
     */
    public void confirmNewsUpdate() {
        System.out.println("\n[Controller] Confirmation received. Proceeding with actual news update.");
        if (pendingNewsUpdate != null) {
            try {
                boolean success = newsService.updateNews(pendingNewsUpdate);
                if (success) {
                    newsForm.showSuccess("News amended successfully.");
                    this.pendingNewsUpdate = null; // Clear pending update
                    newsForm.clearForm(); // Also clear the form's internal data
                } else {
                    newsForm.showError("Failed to update news: News item not found or unexpected error.");
                }
            } catch (ConnectionException e) {
                // REQ: Exit Conditions: The connection to the ETOUR server is interrupted.
                newsForm.showError("Failed to update news due to connection error: " + e.getMessage());
            }
        } else {
            newsForm.showError("No news data pending confirmation for update.");
        }
    }

    /**
     * Handles the cancellation of a news editing operation.
     * Corresponds to REQ: Exit Conditions: The Agency Operator cancels the operation.
     *
     * The sequence:
     * NewsForm (onCancelOperation) -> NewsController (cancelNewsEdit)
     * NewsController -> NewsForm (implicit, for state cleanup)
     */
    public void cancelNewsEdit() {
        System.out.println("\n[Controller] News editing cancelled.");
        newsForm.showError("Operation cancelled."); // Display a general cancellation message
        newsForm.clearForm(); // Clear the form's state
        this.pendingNewsUpdate = null; // Clear any pending updates
    }
}