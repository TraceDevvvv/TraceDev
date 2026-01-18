package com.newsagency.service;

import com.newsagency.dto.NewsDTO;
import com.newsagency.model.News;
import com.newsagency.repository.NewsRepository;
import com.newsagency.util.ValidationResult;
import java.util.Date;

/**
 * Service layer for news operations.
 * Coordinates between controllers and repositories.
 */
public class NewsService {
    private NewsRepository newsRepository;
    private VerificationService verificationService;
    
    public NewsService() {
        this.verificationService = new VerificationService();
        // Note: NewsRepository implementation would be injected in real scenario
    }
    
    public NewsService(NewsRepository newsRepository, VerificationService verificationService) {
        this.newsRepository = newsRepository;
        this.verificationService = verificationService;
    }
    
    public NewsRepository getNewsRepository() {
        return newsRepository;
    }
    
    public void setNewsRepository(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }
    
    public VerificationService getVerificationService() {
        return verificationService;
    }
    
    public void setVerificationService(VerificationService verificationService) {
        this.verificationService = verificationService;
    }
    
    /**
     * Verify news data (sequence message m20).
     * @param newsDTO the news data to verify
     * @return ValidationResult with verification status
     */
    public ValidationResult verifyNewsData(NewsDTO newsDTO) {
        if (verificationService == null) {
            ValidationResult result = new ValidationResult();
            result.setValid(false);
            result.addError("VerificationService not available");
            return result;
        }
        
        // Delegate to VerificationService (sequence message m21)
        ValidationResult verificationResult = verificationService.performCompleteVerification(newsDTO);
        return verificationResult;
    }
    
    /**
     * Insert news (sequence message m39).
     * @param newsDTO the news data to insert
     * @return News entity after insertion
     */
    public News insertNews(NewsDTO newsDTO) {
        if (newsDTO == null) {
            return null;
        }
        
        // Convert DTO to entity
        News news = newsDTO.toNews();
        
        // Validate news before insertion
        boolean isValid = validateNews(newsDTO);
        if (!isValid) {
            return null;
        }
        
        // In real implementation, this would persist through repository
        // For simulation, return the News entity
        return news;
    }
    
    /**
     * Validate news data.
     * @param newsDTO the news data to validate
     * @return true if valid, false otherwise
     */
    public boolean validateNews(NewsDTO newsDTO) {
        if (newsDTO == null) {
            return false;
        }
        
        // Basic validation
        if (newsDTO.getTitle() == null || newsDTO.getTitle().trim().isEmpty()) {
            return false;
        }
        
        if (newsDTO.getContent() == null || newsDTO.getContent().trim().isEmpty()) {
            return false;
        }
        
        if (newsDTO.getAuthor() == null || newsDTO.getAuthor().trim().isEmpty()) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Store news data (sequence message m40).
     * @param news the news entity to store
     * @return true if storage successful, false otherwise
     */
    public boolean storeNewsData(News news) {
        if (news == null || newsRepository == null) {
            return false;
        }
        
        try {
            // Delegate to repository (sequence message m41)
            News savedNews = newsRepository.save(news);
            return savedNews != null;
        } catch (Exception e) {
            System.err.println("Error storing news data: " + e.getMessage());
            return false;
        }
    }
}