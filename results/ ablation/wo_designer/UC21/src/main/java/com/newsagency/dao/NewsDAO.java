package com.newsagency.dao;

import com.newsagency.model.News;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Data Access Object (DAO) for News.
 * Simulates database operations with an in-memory list.
 */
public class NewsDAO {
    // Simulating a database with an in-memory list and an atomic ID generator.
    private List<News> newsList = new ArrayList<>();
    private AtomicInteger idCounter = new AtomicInteger(1);

    /**
     * Inserts a new news item.
     * @param news the news object to insert (without ID)
     * @return the inserted news with generated ID
     */
    public News insert(News news) {
        if (news == null) {
            throw new IllegalArgumentException("News cannot be null");
        }
        int newId = idCounter.getAndIncrement();
        news.setId(newId);
        newsList.add(news);
        return news;
    }

    /**
     * Retrieves all news items (for validation and demonstration).
     * @return a list of all news
     */
    public List<News> getAllNews() {
        return new ArrayList<>(newsList);
    }

    /**
     * Helper method to verify if the server connection is active.
     * Simulates a connection check.
     * @return true if connection is active, false otherwise
     */
    public boolean isConnectionActive() {
        // Simulating a server connection check.
        // In a real system, this would check network/database connectivity.
        return true; // Assume connection is active for this simulation.
    }
}