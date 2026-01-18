package com.example.news.controller;

import com.example.news.dto.ApiResponse;
import com.example.news.dto.NewsFormRequest;
import com.example.news.service.NewsService;
import com.example.news.view.NewsView;
import com.example.news.util.ValidationResult;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for handling news-related requests, particularly for inserting news.
 * It orchestrates interactions between the View and the Service layers.
 */
public class NewsController {
    private NewsService newsService;
    private NewsView newsView; // The controller orchestrates the view

    public NewsController(NewsService newsService, NewsView newsView) {
        this.newsService = newsService;
        this.newsView = newsView;
    }

    /**
     * Initiates the process of showing the news insertion form.
     * Part of the `AO -> View -> Controller: showInsertNewsForm()` flow.
     * The controller then tells the View to display the form.
     */
    public void showInsertNewsForm() {
        System.out.println("[Controller] showInsertNewsForm() called.");
        // No specific model data needed for an initial empty form, just an empty map
        newsView.displayForm(new HashMap<>());
    }

    /**
     * Handles the submission of the news form by the Agency Operator.
     * Part of the `View -> Controller: submitNewsForm(newsFormRequest)` flow.
     * Step 4: Operator submits form.
     *
     * @param request The NewsFormRequest DTO containing the submitted data.
     */
    public void submitNewsForm(NewsFormRequest request) {
        System.out.println("[Controller] Step 4: Operator submits form.");

        // Controller delegates validation to NewsService
        ValidationResult validationResult = newsService.validateNewsData(request);

        if (!validationResult.isValid()) {
            // Data is invalid or insufficient
            // Controller prepares ApiResponse for errors and tells View to display error
            ApiResponse errorResponse = ApiResponse.error("Validation failed.", validationResult.getErrors());
            newsView.displayError(String.join("\n", errorResponse.getErrors()));
            // Note: The SD implies returning an ApiResponse with success=false.
            // For a console app, the controller directly interacts with the view.
            // In a web app, it would return the ApiResponse object.
            return; // Exit the flow if validation fails
        }

        // Data is valid - Step 6: System asks for confirmation
        System.out.println("[Controller] Step 6: System asks for confirmation.");
        // Controller tells View to display confirmation
        newsView.displayConfirmation(request);
        // At this point, the flow waits for AO's confirmation or cancellation from the View
    }

    /**
     * Finalizes the news insertion process after the Agency Operator's confirmation.
     * Part of the `View -> Controller: finalizeNewsInsertion(validatedData)` flow.
     * Step 7: Operator confirms.
     *
     * @param newsData The validated NewsFormRequest DTO to be stored.
     */
    public void finalizeNewsInsertion(NewsFormRequest newsData) {
        System.out.println("[Controller] Step 7: Operator confirms.");

        // Controller delegates storage to NewsService
        ApiResponse apiResponse = newsService.storeNews(newsData);

        if (apiResponse.isSuccess()) {
            // Successful Save
            newsView.displaySuccess(apiResponse.getMessage());
        } else {
            // Connection Interruption (ETOUR) or other storage error
            newsView.displayError(apiResponse.getMessage());
        }
    }

    /**
     * Handles the cancellation of news insertion by the Agency Operator.
     * Part of the `View -> Controller: cancelNewsInsertion()` flow.
     */
    public void cancelNewsInsertion() {
        System.out.println("[Controller] News insertion cancelled.");
        newsView.notifyCancellation();
    }
}