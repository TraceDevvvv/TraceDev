package com.example.service;

import com.example.dto.DeletionConfirmationDTO;
import com.example.model.NewsEntity;
import com.example.repository.NewsRepository;
import java.util.List;

/**
 * Service layer for news operations.
 */
public class NewsService {
    private NewsRepository newsRepository;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    // Added to satisfy requirement REQ-007
    public List<NewsEntity> getAllNews() {
        return newsRepository.findAll();
    }

    // Modified return type from audit report REQ-009
    public DeletionConfirmationDTO confirmDeletion(int newsId) {
        // Business logic validation (Note m24)
        System.out.println("Business logic validation");
        NewsEntity news = newsRepository.findById(newsId);
        if (news != null) {
            return new DeletionConfirmationDTO(newsId, true, "News found and ready for deletion");
        } else {
            return new DeletionConfirmationDTO(newsId, false, "News not found");
        }
    }

    public void deleteNews(int newsId) {
        // This method can throw ServiceException if repository throws DatabaseException
        try {
            newsRepository.delete(newsId);
        } catch (RuntimeException e) {
            throw new ServiceException("ServiceException: " + e.getMessage(), e);
        }
    }

    // Custom exception for service layer
    public static class ServiceException extends RuntimeException {
        public ServiceException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}