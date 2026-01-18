package com.newsagency.usecase;

import com.newsagency.dto.NewsDTO;
import com.newsagency.model.News;
import com.newsagency.service.NewsService;
import com.newsagency.util.OperationResult;
import com.newsagency.util.ValidationResult;
import java.util.Date;

/**
 * Use Case Controller for the News Insertion use case.
 * Coordinates the flow of events according to the sequence diagram.
 */
public class NewsInsertionUseCaseController {
    private NewsService newsService;
    
    public NewsInsertionUseCaseController(NewsService newsService) {
        this.newsService = newsService;
    }
    
    public NewsService getNewsService() {
        return newsService;
    }
    
    public void setNewsService(NewsService newsService) {
        this.newsService = newsService;
    }
    
    /**
     * Execute the insert news flow as per sequence diagram.
     * @param newsDTO the news data transfer object
     * @return OperationResult indicating success or failure
     */
    public OperationResult executeInsertNewsFlow(NewsDTO newsDTO) {
        OperationResult result = new OperationResult();
        
        // Step 1: Verify data
        ValidationResult verificationResult = verifyData(newsDTO);
        if (!verificationResult.isValid()) {
            result.setSuccess(false);
            result.setMessage("Data verification failed: " + verificationResult.getMessage());
            return result;
        }
        
        // Step 2: Request confirmation
        boolean confirmed = requestConfirmation();
        if (!confirmed) {
            result.setSuccess(false);
            result.setMessage("Operation cancelled by user");
            return result;
        }
        
        // Step 3: Store news
        boolean storeSuccess = storeNews(newsDTO);
        if (storeSuccess) {
            result.setSuccess(true);
            result.setMessage("News successfully inserted");
        } else {
            result.setSuccess(false);
            result.setMessage("Failed to store news");
        }
        
        return result;
    }
    
    /**
     * Verify data from the sequence diagram message m26.
     * @param newsDTO the news data to verify
     * @return ValidationResult with verification status
     */
    public ValidationResult verifyData(NewsDTO newsDTO) {
        if (newsService == null) {
            ValidationResult result = new ValidationResult();
            result.setValid(false);
            result.addError("NewsService not available");
            return result;
        }
        
        // Delegate verification to NewsService (sequence message m20)
        ValidationResult serviceResult = newsService.verifyNewsData(newsDTO);
        return serviceResult;
    }
    
    /**
     * Request confirmation from the user.
     * @return true if confirmed, false if cancelled
     */
    public boolean requestConfirmation() {
        // In a real implementation, this would trigger a UI confirmation dialog
        // For simulation, return true (confirmed)
        return true;
    }
    
    /**
     * Store news data (sequence diagram step).
     * @param newsDTO the news data to store
     * @return true if storage successful, false otherwise
     */
    public boolean storeNews(NewsDTO newsDTO) {
        if (newsService == null) {
            return false;
        }
        
        try {
            // Delegate to NewsService (sequence message m39)
            News news = newsService.insertNews(newsDTO);
            if (news != null) {
                // Additional storage step (sequence message m40)
                boolean stored = newsService.storeNewsData(news);
                return stored;
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error storing news: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Handle operation cancellation (sequence message m34).
     * @return OperationResult for cancellation
     */
    public OperationResult operationCancelled() {
        OperationResult result = new OperationResult();
        result.setSuccess(false);
        result.setMessage("Operation cancelled");
        return result;
    }
}