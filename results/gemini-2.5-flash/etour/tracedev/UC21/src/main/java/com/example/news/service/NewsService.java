package com.example.news.service;

import com.example.news.dto.ApiResponse;
import com.example.news.dto.NewsFormRequest;
import com.example.news.model.News;
import com.example.news.repository.ConnectionException;
import com.example.news.repository.NewsRepository;
import com.example.news.util.ValidationResult;

import java.util.Arrays;

/**
 * Application Service layer for News-related business logic.
 * Orchestrates interactions between controller, validator, and repository.
 */
public class NewsService {
    private NewsRepository newsRepository;
    private NewsValidator newsValidator;

    public NewsService(NewsRepository newsRepository, NewsValidator newsValidator) {
        this.newsRepository = newsRepository;
        this.newsValidator = newsValidator;
    }

    /**
     * Validates the incoming news data from the form request.
     * Step 5: System verifies data.
     *
     * @param request The NewsFormRequest DTO containing news data.
     * @return A ValidationResult indicating validity and any errors.
     */
    public ValidationResult validateNewsData(NewsFormRequest request) {
        System.out.println("[Service] Step 5: System verifies data.");
        // Service delegates validation to NewsValidator
        return newsValidator.validate(request);
    }

    /**
     * Stores the news data after successful validation.
     * Step 8: System stores data.
     *
     * @param newsData The validated NewsFormRequest DTO.
     * @return An ApiResponse indicating the success or failure of the storage operation.
     */
    public ApiResponse storeNews(NewsFormRequest newsData) {
        System.out.println("[Service] Step 8: System stores data.");
        try {
            // Step 8: Service creates News object from validated data
            // Updated to align with Class Diagram (REQ-FE8) and Sequence Diagram
            News newsToSave = News.create(newsData);
            newsToSave.status = "published"; // Access public field directly

            // Service calls Repository to save the News object
            News savedNews = newsRepository.save(newsToSave);

            // Successful Save
            return ApiResponse.success("News '" + savedNews.title + "' successfully inserted.", savedNews); // Access public field directly
        } catch (ConnectionException e) {
            // Connection Interruption (ETOUR)
            return ApiResponse.error("Connection interrupted. Please try again. Details: " + e.getMessage(),
                                     Arrays.asList(e.getMessage()));
        } catch (Exception e) {
            // General unexpected error
            return ApiResponse.error("An unexpected error occurred while storing news. Details: " + e.getMessage(),
                                     Arrays.asList(e.getMessage()));
        }
    }
}