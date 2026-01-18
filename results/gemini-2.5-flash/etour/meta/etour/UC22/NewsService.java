package com.example.newsapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Service class to manage news articles.
 * Provides functionalities to add, retrieve, and delete news.
 */
public class NewsService {
    // Using ConcurrentHashMap for thread-safe storage of news articles,
    // mapping news ID to News object.
    private final Map<String, News> newsStore;
    // Simple counter for generating unique IDs for new news articles.
    private final AtomicInteger idCounter;

    /**
     * Constructs a NewsService with an empty news store.
     */
    public NewsService() {
        this.newsStore = new ConcurrentHashMap<>();
        this.idCounter = new AtomicInteger(0);
        // Initialize with some dummy data for demonstration
        addNews("Breaking News: Java 21 Released", "Oracle announced the general availability of Java 21.");
        addNews("Tech Trends: AI Integration", "Artificial Intelligence is rapidly integrating into various industries.");
        addNews("Local Update: City Marathon", "Annual city marathon registration opens next month.");
    }

    /**
     * Adds a new news article to the system.
     * Generates a unique ID for the news.
     *
     * @param title The title of the news.
     * @param content The content of the news.
     * @return The newly created News object, or null if title/content is invalid.
     */
    public News addNews(String title, String content) {
        try {
            // Generate a simple unique ID. In a real application, this would be more robust.
            String newId = "NEWS-" + idCounter.incrementAndGet();
            News news = new News(newId, title, content);
            newsStore.put(newId, news);
            return news;
        } catch (IllegalArgumentException e) {
            System.err.println("Error adding news: " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves a news article by its ID.
     *
     * @param newsId The ID of the news article to retrieve.
     * @return An Optional containing the News object if found, or an empty Optional if not found.
     */
    public Optional<News> getNewsById(String newsId) {
        return Optional.ofNullable(newsStore.get(newsId));
    }

    /**
     * Retrieves an unmodifiable list of all news articles currently in the system.
     *
     * @return A List of News objects.
     */
    public List<News> getAllNews() {
        // Return an unmodifiable list to prevent external modification of the internal store.
        return Collections.unmodifiableList(new ArrayList<>(newsStore.values()));
    }

    /**
     * Deletes a news article from the system based on its ID.
     *
     * @param newsId The ID of the news article to delete.
     * @return true if the news was successfully deleted, false otherwise (e.g., news not found).
     */
    public boolean deleteNews(String newsId) {
        if (newsId == null || newsId.trim().isEmpty()) {
            System.err.println("Error: News ID for deletion cannot be null or empty.");
            return false;
        }
        // remove() returns the previous value associated with key, or null if there was no mapping.
        // So, if it returns non-null, the news was found and removed.
        return newsStore.remove(newsId) != null;
    }

    /**
     * Checks if the news store is empty.
     * @return true if there are no news articles, false otherwise.
     */
    public boolean isEmpty() {
        return newsStore.isEmpty();
    }
}