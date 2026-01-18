package com.newsagency.service;

import com.newsagency.dto.NewsDTO;
import com.newsagency.util.ValidationResult;
import java.util.Map;

/**
 * Service for verifying news content and metadata.
 */
public class VerificationService {
    
    public VerificationService() {
    }
    
    /**
     * Verify content of news.
     * @param content the content to verify
     * @return true if content is valid, false otherwise
     */
    public boolean verifyContent(String content) {
        if (content == null || content.trim().isEmpty()) {
            return false;
        }
        
        // Basic content validation
        // In real implementation, this could check for inappropriate content, length, etc.
        return content.length() > 10 && content.length() < 10000;
    }
    
    /**
     * Verify metadata of news.
     * @param metadata the metadata to verify
     * @return true if metadata is valid, false otherwise
     */
    public boolean verifyMetadata(Map<String, Object> metadata) {
        if (metadata == null || metadata.isEmpty()) {
            return false;
        }
        
        // Basic metadata validation
        // Check for required metadata fields
        return metadata.containsKey("title") && metadata.containsKey("author");
    }
    
    /**
     * Perform complete verification (sequence message m21).
     * @param newsDTO the news data to verify
     * @return ValidationResult with verification status
     */
    public ValidationResult performCompleteVerification(NewsDTO newsDTO) {
        ValidationResult result = new ValidationResult();
        
        if (newsDTO == null) {
            result.setValid(false);
            result.addError("NewsDTO is null");
            return result;
        }
        
        // Verify content
        boolean contentValid = verifyContent(newsDTO.getContent());
        if (!contentValid) {
            result.setValid(false);
            result.addError("Content verification failed");
        }
        
        // Verify metadata
        java.util.Map<String, Object> metadata = new java.util.HashMap<>();
        metadata.put("title", newsDTO.getTitle());
        metadata.put("author", newsDTO.getAuthor());
        metadata.put("category", newsDTO.getCategory());
        
        boolean metadataValid = verifyMetadata(metadata);
        if (!metadataValid) {
            result.setValid(false);
            result.addError("Metadata verification failed");
        }
        
        // Validate status (sequence message m22)
        boolean statusValid = validateStatus(newsDTO.getStatus());
        if (!statusValid) {
            result.setValid(false);
            result.addError("Status validation failed");
        }
        
        if (contentValid && metadataValid && statusValid) {
            result.setValid(true);
            result.setMessage("Complete verification successful");
        } else {
            result.setValid(false);
            if (result.getMessage() == null) {
                result.setMessage("Verification failed");
            }
        }
        
        return result;
    }
    
    /**
     * Validate status (sequence message m22).
     * @param status the status to validate
     * @return true if status is valid, false otherwise
     */
    public boolean validateStatus(String status) {
        if (status == null) {
            return false;
        }
        
        // Valid statuses
        return status.equals("DRAFT") || status.equals("PUBLISHED") || status.equals("ARCHIVED");
    }
}