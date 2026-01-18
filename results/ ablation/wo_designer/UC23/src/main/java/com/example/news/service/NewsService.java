package com.example.news.service;

import com.example.news.entity.News;
import com.example.news.repository.NewsRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for news operations.
 * Contains business logic and validation.
 */
public class NewsService {
    private final NewsRepository newsRepository;
    private boolean isLoggedIn = false;

    public NewsService(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    /**
     * Simulate login of Agency Operator.
     */
    public void login() {
        isLoggedIn = true;
    }

    /**
     * Check if Agency Operator is logged in.
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    /**
     * Get all news for display.
     */
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    /**
     * Load news data by ID.
     */
    public Optional<News> loadNews(Long id) {
        return newsRepository.findById(id);
    }

    /**
     * Validate news data before update.
     * Returns error message if invalid, empty string if valid.
     */
    public String validateNews(News news) {
        if (news.getTitle() == null || news.getTitle().trim().isEmpty()) {
            return "Title cannot be empty.";
        }
        if (news.getContent() == null || news.getContent().trim().isEmpty()) {
            return "Content cannot be empty.";
        }
        if (news.getAuthor() == null || news.getAuthor().trim().isEmpty()) {
            return "Author cannot be empty.";
        }
        if (news.getTitle().length() > 200) {
            return "Title must be less than 200 characters.";
        }
        if (news.getContent().length() > 5000) {
            return "Content must be less than 5000 characters.";
        }
        return "";
    }

    /**
     * Update news article after validation and confirmation.
     * Returns status message.
     */
    public String updateNews(News originalNews, News updatedData, boolean confirmed) {
        // Check if user is logged in
        if (!isLoggedIn) {
            return "Error: Agency Operator must be logged in.";
        }

        // Check server connection
        if (!newsRepository.isConnectionAvailable()) {
            return "Error: Connection to server interrupted. Operation cancelled.";
        }

        // If not confirmed, cancel operation
        if (!confirmed) {
            return "Operation cancelled by Agency Operator.";
        }

        // Validate updated data
        String validationError = validateNews(updatedData);
        if (!validationError.isEmpty()) {
            return "Validation Error: " + validationError;
        }

        // Set last modified timestamp
        updatedData.setLastModified(LocalDateTime.now());
        // Preserve original publish date if not being changed
        if (updatedData.getPublishDate() == null) {
            updatedData.setPublishDate(originalNews.getPublishDate());
        }

        // Perform update
        boolean success = newsRepository.update(updatedData);
        if (success) {
            return "News updated successfully!";
        } else {
            return "Error: News not found.";
        }
    }
}