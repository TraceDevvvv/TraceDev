package com.example.service;

import com.example.model.News;
import com.example.repository.NewsRepository;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for news operations.
 */
public class NewsService {
    private NewsRepository newsRepository;

    public NewsService(NewsRepository repository) {
        this.newsRepository = repository;
    }

    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    public Optional<News> getNewsById(String id) {
        return newsRepository.findById(id);
    }

    /**
     * Validates news data.
     * @param news The news to validate.
     * @return true if valid, false otherwise.
     */
    public boolean validateNewsData(News news) {
        // Simple validation: title and content must not be empty.
        return news != null &&
                news.getTitle() != null && !news.getTitle().trim().isEmpty() &&
                news.getContent() != null && !news.getContent().trim().isEmpty();
    }

    /**
     * Updates an existing news article.
     * @param id The ID of the news to update.
     * @param updatedNews The updated news data.
     * @return The updated news entity.
     * @throws IllegalArgumentException if news not found or validation fails.
     */
    public News updateNews(String id, News updatedNews) {
        // Check if news exists.
        Optional<News> existingOpt = newsRepository.findById(id);
        if (!existingOpt.isPresent()) {
            throw new IllegalArgumentException("News with ID " + id + " not found.");
        }
        // Validate the updated news.
        if (!validateNewsData(updatedNews)) {
            throw new IllegalArgumentException("Invalid news data.");
        }
        // Update the existing news fields.
        News existing = existingOpt.get();
        existing.setTitle(updatedNews.getTitle());
        existing.setContent(updatedNews.getContent());
        existing.setAuthor(updatedNews.getAuthor());
        // Save and return.
        return newsRepository.save(existing);
    }
}