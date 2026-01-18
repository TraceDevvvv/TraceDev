package com.example.news.repository;

import com.example.news.entity.News;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In-memory repository for News entities.
 * Simulates database operations.
 */
public class NewsRepository {
    private final List<News> newsList = new ArrayList<>();
    private final AtomicLong idCounter = new AtomicLong(1);

    public NewsRepository() {
        // Initialize with some sample data
        newsList.add(new News(idCounter.getAndIncrement(), "Java 21 Released", "Java 21 includes new features...", "Oracle", LocalDateTime.now().minusDays(5), LocalDateTime.now().minusDays(5)));
        newsList.add(new News(idCounter.getAndIncrement(), "AI Advances", "Recent breakthroughs in AI...", "Tech News", LocalDateTime.now().minusDays(2), LocalDateTime.now().minusDays(2)));
    }

    /**
     * Retrieve all news articles.
     */
    public List<News> findAll() {
        return new ArrayList<>(newsList);
    }

    /**
     * Find a news article by ID.
     */
    public Optional<News> findById(Long id) {
        return newsList.stream().filter(news -> news.getId().equals(id)).findFirst();
    }

    /**
     * Update an existing news article.
     * Returns true if update successful, false if news not found.
     */
    public boolean update(News updatedNews) {
        for (int i = 0; i < newsList.size(); i++) {
            if (newsList.get(i).getId().equals(updatedNews.getId())) {
                newsList.set(i, updatedNews);
                return true;
            }
        }
        return false;
    }

    /**
     * Simulate server connection check.
     */
    public boolean isConnectionAvailable() {
        // In a real application, check actual server connectivity
        return true; // Assume connection is available for this demo
    }
}