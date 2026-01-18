package com.newsagency.controller;

import com.newsagency.dto.NewsDTO;
import com.newsagency.model.Operator;
import com.newsagency.service.NewsService;
import com.newsagency.ui.NewsInsertionView;
import com.newsagency.usecase.NewsInsertionUseCaseController;
import com.newsagency.util.ValidationResult;
import java.util.Map;

/**
 * Controller component in MVC pattern.
 * Orchestrates interactions between view and use case controller.
 */
public class NewsController {
    private NewsInsertionView view;
    private NewsInsertionUseCaseController useCaseController;
    private NewsService newsService;
    private Operator operator;
    
    public NewsController() {
        this.operator = new Operator();
        this.newsService = new NewsService();
        this.useCaseController = new NewsInsertionUseCaseController(newsService);
    }
    
    public void setView(NewsInsertionView view) {
        this.view = view;
    }
    
    public void setUseCaseController(NewsInsertionUseCaseController useCaseController) {
        this.useCaseController = useCaseController;
    }
    
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
    
    /**
     * Handles insert news request from view.
     */
    public void handleInsertNewsRequest() {
        System.out.println("Handling insert news request...");
        requestFormSubmission();
    }
    
    /**
     * Validates form data (Flow of Events 5).
     * @param data form data to validate
     * @return ValidationResult containing validation status
     */
    public ValidationResult validateFormData(Map<String, Object> data) {
        System.out.println("Validating form data...");
        
        ValidationResult result = new ValidationResult();
        
        if (data == null || data.isEmpty()) {
            result.setValid(false);
            result.addError("Form data is empty");
            return result;
        }
        
        // Basic validation
        if (!data.containsKey("title") || ((String) data.get("title")).trim().isEmpty()) {
            result.setValid(false);
            result.addError("Title is required");
        }
        
        if (!data.containsKey("content") || ((String) data.get("content")).trim().isEmpty()) {
            result.setValid(false);
            result.addError("Content is required");
        }
        
        if (!data.containsKey("author") || ((String) data.get("author")).trim().isEmpty()) {
            result.setValid(false);
            result.addError("Author is required");
        }
        
        if (result.isValid()) {
            result.setValid(true);
            result.setMessage("Form data validation successful");
        }
        
        return result;
    }
    
    /**
     * Processes form submission from view.
     */
    public void processFormSubmission() {
        if (view == null) {
            System.out.println("ERROR: View not set in controller");
            return;
        }
        
        System.out.println("Processing form submission...");
        
        // Collect form data from view
        Map<String, Object> formData = view.collectFormData();
        
        // Validate form data
        ValidationResult validationResult = validateFormData(formData);
        
        if (validationResult.isValid()) {
            // Create NewsDTO from form data
            NewsDTO newsDTO = view.createNewsDTO();
            
            // Validate NewsDTO (Flow of Events 5)
            ValidationResult dtoValidation = validateData(newsDTO);
            
            if (dtoValidation.isValid()) {
                // Execute use case flow
                com.newsagency.util.OperationResult operationResult = 
                    useCaseController.executeInsertNewsFlow(newsDTO);
                
                if (operationResult.isSuccess()) {
                    view.displaySuccessMessage();
                } else {
                    view.displayErrorMessage();
                }
            } else {
                System.out.println("NewsDTO validation failed: " + dtoValidation.getMessage());
                view.displayErrorMessage();
            }
        } else {
            System.out.println("Form validation failed: " + validationResult.getMessage());
            view.displayErrorMessage();
        }
        
        // Notify view that operation is complete
        if (view != null) {
            System.out.println("Operation complete.");
        }
    }
    
    /**
     * Handles cancellation of operation (Exit Conditions).
     */
    public void handleCancelOperation() {
        System.out.println("Operation cancelled by Agency Operator.");
        if (view != null) {
            view.displayErrorMessage();
            view.cleanupForm();
        }
    }
    
    /**
     * Validates login status (Entry Conditions).
     * @return true if operator is logged in, false otherwise
     */
    public boolean validateLogin() {
        return operator != null && operator.isLoggedIn();
    }
    
    /**
     * Handles connection loss (Exit Conditions).
     */
    public void handleConnectionLoss() {
        System.out.println("Controller handling connection loss...");
        if (view != null) {
            view.handleConnectionLoss();
        }
    }
    
    /**
     * Validates NewsDTO data (Flow of Events 5).
     * @param newsDTO NewsDTO to validate
     * @return ValidationResult containing validation status