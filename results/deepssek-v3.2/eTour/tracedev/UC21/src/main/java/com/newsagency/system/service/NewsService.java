package com.newsagency.system.service;

import com.newsagency.system.dto.NewsFormDTO;
import com.newsagency.system.dto.ResponseDTO;
import com.newsagency.system.dto.NewsDTO;
import com.newsagency.system.entity.News;
import com.newsagency.system.repository.NewsRepository;
import com.newsagency.system.repository.NewsRepositoryImpl;
import com.newsagency.system.utility.ValidationService;
import com.newsagency.system.valueobject.ValidationResult;
import com.newsagency.system.exception.ServerUnavailableException;

import java.util.ArrayList;
import java.util.List;

/**
 * Application service that encapsulates the business logic for news insertion.
 */
public class NewsService {
    private NewsRepository newsRepository;
    private ValidationService validationService;

    public NewsService() {
        // For simplicity, instantiate concrete repository and validation service.
        this.newsRepository = new NewsRepositoryImpl();
        this.validationService = new ValidationService();
    }

    public NewsService(NewsRepository newsRepository, ValidationService validationService) {
        this.newsRepository = newsRepository;
        this.validationService = validationService;
    }

    /**
     * Inserts a new news item into the system.
     * @param newsData the data from the form
     * @return response with success or error
     * @throws ServerUnavailableException if connection to repository fails
     */
    public ResponseDTO insertNews(NewsFormDTO newsData) throws ServerUnavailableException {
        System.out.println("NewsService: inserting news.");
        // Step 5d: additional business validation
        ValidationResult businessValidation = validateNewsData(newsData);
        if (!businessValidation.isValid()) {
            System.out.println("NewsService: business validation failed.");
            // Create error response
            ResponseDTO errorResponse = new ResponseDTO();
            errorResponse.setSuccess(false);
            errorResponse.setErrors(businessValidation.getErrors());
            return errorResponse;
        }

        // Step 6: confirm transaction (e.g., logging)
        confirmTransaction();

        // Step 7a: create News entity
        News newsEntity = new News(newsData.getTitle(), newsData.getContent(), newsData.getAuthor());

        // Step 7b: save entity via repository
        News savedNews;
        try {
            savedNews = newsRepository.save(newsEntity);
        } catch (RuntimeException e) {
            // Assumption: any runtime exception from repository is a connection error.
            System.out.println("NewsService: repository threw exception - " + e.getMessage());
            handleConnectionError();
            throw new ServerUnavailableException("Server connection interrupted");
        }

        // Step 7d: create NewsDTO from saved entity
        NewsDTO newsDTO = new NewsDTO(savedNews);

        // Step 7e: create success response
        ResponseDTO successResponse = new ResponseDTO();
        successResponse.setSuccess(true);
        successResponse.setData(newsDTO);
        successResponse.setErrors(new ArrayList<>()); // no errors

        System.out.println("NewsService: news inserted successfully.");
        return successResponse;
    }

    /**
     * Validates news data from a business perspective.
     * @param newsData the form data
     * @return validation result
     */
    public ValidationResult validateNewsData(NewsFormDTO newsData) {
        // Delegates to ValidationService; could add additional business rules.
        return validationService.validateNewsForm(newsData);
    }

    /**
     * Confirms the transaction (e.g., logging).
     */
    public void confirmTransaction() {
        System.out.println("NewsService: Transaction confirmed.");
        // Could log the transaction, notify other serv, etc.
    }

    /**
     * Handles connection errors (e.g., logging, cleanup).
     */
    public void handleConnectionError() {
        System.out.println("NewsService: Handling connection error.");
        // Could log the error, alert administrators, etc.
    }
}