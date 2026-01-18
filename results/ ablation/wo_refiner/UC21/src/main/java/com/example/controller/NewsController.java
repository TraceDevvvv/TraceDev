
package com.example.controller;

import com.example.boundary.NewsForm;
import com.example.request.InsertNewsRequest;
import com.example.response.InsertNewsResponse;
import com.example.command.InsertNewsCommand;
import com.example.service.NewsService;
import com.example.service.AuthenticationService;

import java.util.Map;

/**
 * Controller that orchestrates the news insertion flow.
 */
public class NewsController {

    private NewsService newsService;
    private AuthenticationService authenticationService;
    private NewsForm newsForm;

    public NewsController(NewsService newsService, AuthenticationService authenticationService) {
        this.newsService = newsService;
        this.authenticationService = authenticationService;
        this.newsForm = new NewsForm(this);
    }

    /**
     * Main method to insert news. Called by the boundary (NewsForm).
     */
    public InsertNewsResponse insertNews(InsertNewsRequest request) {
        // Verify login status (Entry Condition)
        if (!authenticationService.verifyLogin()) {
            return new InsertNewsResponse(false, "Authentication failed.");
        }

        InsertNewsCommand command = createFromRequest(request);
        boolean success = newsService.processInsertNews(command);
        String message = success ? "News inserted successfully." : "Failed to insert news.";
        return new InsertNewsResponse(success, message);
    }

    /**
     * Creates an InsertNewsCommand from the request data.
     * @param request The incoming request.
     * @return The constructed command.
     */
    public InsertNewsCommand createFromRequest(InsertNewsRequest request) {
        // In a real scenario, more mapping might be needed.
        return new InsertNewsCommand(request.getTitle(), request.getContent(), request.getAuthor());
    }

    /**
     * Alternative method that accepts a Map for compatibility with boundary.
     */
    public InsertNewsCommand createFromRequest(Map<String, String> formData) {
        return new InsertNewsCommand(
                formData.get("title"),
                formData.get("content"),
                formData.get("author")
        );
    }

    /**
     * Cancels the ongoing operation.
     * @param cancellationRequest The cancellation request.
     * @return true if cancellation was successful.
     */
    public boolean cancel(String cancellationRequest) {
        return newsService.cancelInsertion(cancellationRequest);
    }

    /**
     * Activates an errored use case, e.g., for error handling.
     * @param errorDetails Description of the error.
     */
    public void activateErroredUseCase(String errorDetails) {
        // This method would trigger error handling logic.
        // For simplicity, we just log.
        System.out.println("Errored use case activated: " + errorDetails);
    }

    // Getters for dependencies (if needed)
    public NewsForm getNewsForm() {
        return newsForm;
    }
}
