package com.newsagency.system.controller;

import com.newsagency.system.dto.NewsFormDTO;
import com.newsagency.system.dto.ResponseDTO;
import com.newsagency.system.service.NewsService;
import com.newsagency.system.utility.ValidationService;
import com.newsagency.system.valueobject.ValidationResult;

import java.util.List;
import java.util.ArrayList;

/**
 * Controller that orchestrates the news insertion process.
 * Acts as a mediator between the boundary and service layers.
 */
public class InsertNewsController {
    private NewsService newsService;
    private ValidationService validationService;

    public InsertNewsController() {
        // For simplicity, we instantiate dependencies directly.
        // In a real application, use dependency injection.
        this.newsService = new NewsService();
        this.validationService = new ValidationService();
    }

    public InsertNewsController(NewsService newsService, ValidationService validationService) {
        this.newsService = newsService;
        this.validationService = validationService;
    }

    /**
     * Main method called by the form to submit news.
     * @param newsData the form data
     * @return response indicating success or failure
     */
    public ResponseDTO submitNewsForm(NewsFormDTO newsData) {
        System.out.println("Controller: received submit request.");
        // Step 5a: validate form data
        ValidationResult validationResult = validateNews(newsData);
        if (!validationResult.isValid()) {
            // Validation failed: create error response
            System.out.println("Controller: form validation failed.");
            return createValidationErrorResponse(validationResult.getErrors());
        }

        // Step 5c: call service to insert news
        try {
            ResponseDTO serviceResponse = newsService.insertNews(newsData);
            // Confirm transaction (could be logging, etc.)
            confirmTransaction();
            return serviceResponse;
        } catch (com.newsagency.system.exception.ServerUnavailableException e) {
            // Handle connection interruption
            System.out.println("Controller: Server unavailable exception caught.");
            List<String> errors = new ArrayList<>();
            errors.add("Server connection interrupted");
            return createServerErrorResponse("Server connection interrupted");
        }
    }

    /**
     * Validates the news form data.
     * @param newsData the form data
     * @return validation result
     */
    public ValidationResult validateNews(NewsFormDTO newsData) {
        // Delegates to ValidationService
        return validationService.validateNewsForm(newsData);
    }

    /**
     * Creates an error response DTO from a list of error messages.
     * @param errors list of error messages
     * @return error response
     */
    public ResponseDTO createErrorResponse(List<String> errors) {
        // This method creates a ResponseDTO with the given errors.
        // It could also create an ErrorDTO and embed it, but the sequence diagram
        // shows that the error is embedded in ResponseDTO directly.
        ResponseDTO response = new ResponseDTO();
        response.setSuccess(false);
        response.setErrors(errors);
        // For simplicity, we don't create a separate ErrorDTO here.
        return response;
    }

    /**
     * Creates a validation error response.
     * @param errors list of error messages
     * @return error response
     */
    public ResponseDTO createValidationErrorResponse(List<String> errors) {
        ResponseDTO response = new ResponseDTO();
        response.setSuccess(false);
        response.setErrors(errors);
        return response;
    }

    /**
     * Creates a server error response.
     * @param errorMessage the server error message
     * @return error response
     */
    public ResponseDTO createServerErrorResponse(String errorMessage) {
        ResponseDTO response = new ResponseDTO();
        response.setSuccess(false);
        List<String> errors = new ArrayList<>();
        errors.add(errorMessage);
        response.setErrors(errors);
        return response;
    }

    /**
     * Confirms the transaction (e.g., logging, notifications).
     */
    public void confirmTransaction() {
        System.out.println("Controller: Transaction confirmed.");
        // Could involve audit logging, sending notifications, etc.
    }
}