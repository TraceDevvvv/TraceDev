package com.modify_news.repository;

import com.modify_news.model.News;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Repository class for managing News objects.
 * This class provides an abstraction layer over the data storage,
 * allowing for CRUD operations on News entities.
 * For simplicity, an in-memory map is used as the data store.
 */
public class NewsRepository {
    // Using a ConcurrentHashMap to simulate a data store for thread-safe operations
    // The key is the news ID, and the value is the News object.
    private final Map<String, News> newsStore = new ConcurrentHashMap<>();

    /**
     * Constructor for NewsRepository.
     * Initializes the repository with some sample news data.
     */
    public NewsRepository() {
        // Populate with some initial data for demonstration purposes
        newsStore.put("N001", new News("N001", "Breaking News: AI Advances", "Recent breakthroughs in AI technology are set to revolutionize various industries.", "John Doe", LocalDateTime.of(2023, 10, 26, 10, 0)));
        newsStore.put("N002", new News("N002", "Global Economy Outlook", "Analysts predict a period of cautious growth for the global economy in the coming year.", "Jane Smith", LocalDateTime.of(2023, 10, 25, 14, 30)));
        newsStore.put("N003", new News("N003", "Local Sports Highlights", "Our city's basketball team secured a dramatic win in last night's championship game.", "Mike Johnson", LocalDateTime.of(2023, 10, 24, 9, 15)));
    }

    /**
     * Finds a news article by its unique ID.
     *
     * @param id The ID of the news article to find.
     * @return An Optional containing the News object if found, or an empty Optional if not found.
     */
    public Optional<News> findById(String id) {
        return Optional.ofNullable(newsStore.get(id));
    }

    /**
     * Retrieves all news articles currently stored in the repository.
     *
     * @return A list of all News objects.
     */
    public List<News> findAll() {
        return new ArrayList<>(newsStore.values());
    }

    /**
     * Saves a news article to the repository.
     * If a news article with the same ID already exists, it will be updated.
     * Otherwise, it will be added as a new entry.
     *
     * @param news The News object to save.
     * @return The saved News object.
     */
    public News save(News news) {
        if (news == null || news.getId() == null || news.getId().isEmpty()) {
            throw new IllegalArgumentException("News and its ID cannot be null or empty.");
        }
        newsStore.put(news.getId(), news);
        return news;
    }

    /**
     * Deletes a news article from the repository by its ID.
     *
     * @param id The ID of the news article to delete.
     * @return true if the news article was found and removed, false otherwise.
     */
    public boolean deleteById(String id) {
        return newsStore.remove(id) != null;
    }

    /**
     * Checks if a news article with the given ID exists in the repository.
     *
     * @param id The ID of the news article to check.
     * @return true if a news article with the ID exists, false otherwise.
     */
    public boolean existsById(String id) {
        return newsStore.containsKey(id);
    }
}