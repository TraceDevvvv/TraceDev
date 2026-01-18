package com.example.controller;

import com.example.dto.DeletionConfirmationDTO;
import com.example.dto.ErrorDTO;
import com.example.exception.ExceptionHandler;
import com.example.model.AgencyOperator;
import com.example.model.NewsEntity;
import com.example.security.SessionManager;
import com.example.service.NewsService;
import java.util.List;

/**
 * Controller for managing news deletion operations.
 * Implements all behaviors from the sequence diagram.
 */
public class NewsDeletionController {
    private SessionManager sessionManager;
    private NewsService newsService;
    private ExceptionHandler exceptionHandler;
    private AgencyOperator currentOperator;

    public NewsDeletionController(SessionManager sessionManager, NewsService newsService, 
                                  ExceptionHandler exceptionHandler, AgencyOperator operator) {
        this.sessionManager = sessionManager;
        this.newsService = newsService;
        this.exceptionHandler = exceptionHandler;
        this.currentOperator = operator;
    }

    // Added to satisfy requirement REQ-006
    public void activateDeleteNewsFunction() {
        System.out.println("Delete news function activated.");
        
        // Step 1: Function activation (Note m4)
        System.out.println("Step 1: Function activation");
        
        // Step 1: Check authentication (REQ-004)
        boolean isLoggedIn = sessionManager.isLoggedIn(currentOperator);
        if (!isLoggedIn) {
            System.out.println("Operator is not logged in. Access denied.");
            return;
        }
        
        // Step 2: Ask for confirmation to view news (Note m6)
        System.out.println("Step 2: Ask for confirmation to view news");
        boolean viewConfirmed = requestConfirmation();
        if (!viewConfirmed) {
            cancelOperation();
            return;
        }
        
        // Modified to use separate method for view confirmation REQ-010
        boolean viewRequestConfirmed = confirmViewRequest();
        if (!viewRequestConfirmed) {
            cancelOperation();
            return;
        }
        
        // Get all news (REQ-007)
        List<NewsEntity> newsList = getAllNews();
        displayNewsList();
        
        // For demonstration, assume news with ID 1 is selected
        int selectedNewsId = 1; // This would come from user input in real application
        
        if (selectedNewsId > 0) {
            submitDeleteForm(selectedNewsId);
        } else {
            showNoSelectionMessage();
        }
    }

    // Added to satisfy requirement REQ-007
    public List<NewsEntity> getAllNews() {
        return newsService.getAllNews();
    }

    public void displayNewsList() {
        List<NewsEntity> newsList = getAllNews();
        System.out.println("=== News List ===");
        for (NewsEntity news : newsList) {
            System.out.println("ID: " + news.getId() + ", Title: " + news.getTitle());
        }
        System.out.println("=================");
    }

    // Added missing method call REQ-008
    public void submitDeleteForm(int newsId) {
        System.out.println("Delete form submitted for news ID: " + newsId);
        
        // Step 3: Select news from list and submit form (Note m16)
        System.out.println("Step 3: Select news from list and submit form");
        
        // Step 4: Ask for deletion confirmation (Note m19)
        System.out.println("Step 4: Ask for deletion confirmation");
        boolean deleteConfirmed = requestConfirmation();
        if (!deleteConfirmed) {
            cancelOperation();
            return;
        }
        
        // Show specific confirmation dialog for deletion
        showConfirmationDialog(newsId);
        
        // Step 5: Confirm deletion (Note m22)
        System.out.println("Step 5: Confirm deletion");
        // For demonstration, assume user confirms deletion
        boolean userConfirmsDeletion = true; // This would come from user input
        
        if (userConfirmsDeletion) {
            DeletionConfirmationDTO confirmation = confirmNewsDeletion(newsId);
            if (confirmation.isSuccess()) {
                executeDeletion(newsId);
            } else {
                System.out.println("Deletion confirmation failed: " + confirmation.getMessage());
            }
        } else {
            cancelOperation();
        }
    }

    // Added to satisfy requirement REQ-009
    public boolean requestConfirmation() {
        // In a real application, this would show a UI dialog
        // For this example, we return true to continue the flow
        System.out.println("Requesting confirmation from user...");
        return true;
    }

    public void showConfirmationDialog(int newsId) {
        System.out.println("Confirmation dialog shown for deleting news ID: " + newsId);
        System.out.println("Are you sure you want to delete this news article?");
    }

    // Added to satisfy requirement REQ-010
    public boolean confirmViewRequest() {
        System.out.println("Confirming view request...");
        return true;
    }

    public DeletionConfirmationDTO confirmNewsDeletion(int newsId) {
        System.out.println("Confirming news deletion for ID: " + newsId);
        return newsService.confirmDeletion(newsId);
    }

    // Added to satisfy requirement REQ-011, modified to include newsId
    public void executeDeletion(int newsId) {
        System.out.println("Executing deletion for news ID: " + newsId);
        
        try {
            // Step 6: Delete the news data (Note m31)
            System.out.println("Step 6: Delete the news data");
            
            newsService.deleteNews(newsId);
            
            // Added missing method call and enhanced notification REQ-012
            DeletionConfirmationDTO successResponse = createSuccessResponse();
            showSuccessNotification(successResponse);
            
        } catch (Exception e) {
            // Enhanced error handling for REQ-015
            handleException(e);
        }
    }

    // Added from audit report
    public DeletionConfirmationDTO createSuccessResponse() {
        return new DeletionConfirmationDTO(0, true, "News deleted successfully!");
    }

    // Modified to satisfy requirement REQ-012
    public void showSuccessNotification(DeletionConfirmationDTO confirmation) {
        System.out.println("SUCCESS: " + confirmation.getMessage());
        // Exit condition: Notify successful elimination (Note m40)
        System.out.println("Exit condition: Notify successful elimination");
    }

    // Added to satisfy requirement REQ-013
    public void cancelOperation() {
        System.out.println("Operation cancelled by user.");
        // Alternative Flow: User cancels (Note m42)
        System.out.println("Alternative Flow: User cancels");
        showCancellationMessage();
    }

    public void showCancellationMessage() {
        System.out.println("Operation has been cancelled.");
    }

    public void showNoSelectionMessage() {
        System.out.println("No news was selected for deletion.");
    }

    // Added from audit report
    public void handleException(Exception exception) {
        System.out.println("Handling exception: " + exception.getMessage());
        ErrorDTO error = exceptionHandler.handleException(exception);
        System.out.println("Error Code: " + error.getErrorCode());
        System.out.println("Error Message: " + error.getErrorMessage());
        
        // Show connection error if it's a database connection issue
        if (error.getErrorCode().equals("DB_CONNECTION_ERROR")) {
            showConnectionError();
        }
    }

    // Added to satisfy requirement REQ-014
    public void showConnectionError() {
        // Exception flow: Connection to server ETOUR interrupted (Note m48)
        System.out.println("Exception flow: Connection to server ETOUR interrupted");
        System.out.println("Show \"Interruption of the connection to the server ETOUR\" error (Note m60)");
        System.out.println("ERROR: Interruption of the connection to the server ETOUR");
    }
}