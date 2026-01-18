package com.example.newsapp.data.repository;

import com.example.newsapp.domain.model.News;
import com.example.newsapp.infrastructure.exception.NetworkException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Concrete implementation of INewsRepository, simulating data storage.
 * This repository uses an in-memory map to store news data.
 * It also simulates a 'DatabaseConnection' via an Object field and can
 * simulate NetworkException for testing purposes.
 */
public class NewsRepository implements INewsRepository {
    // Example: Database connection or ORM context
    private Object dataSource;
    private final Map<String, News> newsStore;

    // A flag to simulate network exceptions for testing (REQ-EX-3)
    private boolean simulateNetworkFailure = false;

    /**
     * Constructor for NewsRepository.
     * Initializes the in-memory data store with some sample news.
     * @param dataSource A placeholder for a database connection object.
     */
    public NewsRepository(Object dataSource) {
        this.dataSource = dataSource;
        this.newsStore = new ConcurrentHashMap<>();
        // Populate with some initial data
        newsStore.put("news1", new News("news1", "Breaking News: Java 21 Released", "Java 21 brings new features like Virtual Threads and Pattern Matching.", new Date()));
        newsStore.put("news2", new News("news2", "Sports Update: Local Team Wins Championship", "Our local team secured the championship title with a thrilling victory.", new Date(System.currentTimeMillis() - 86400000))); // 1 day ago
        newsStore.put("news3", new News("news3", "Tech Review: Latest Smartphone Revealed", "The new smartphone boasts a 108MP camera and a 120Hz display.", new Date(System.currentTimeMillis() - 172800000))); // 2 days ago
    }

    /**
     * Sets the flag to simulate network failure for testing.
     * @param simulateNetworkFailure true to simulate failure, false otherwise.
     */
    public void setSimulateNetworkFailure(boolean simulateNetworkFailure) {
        this.simulateNetworkFailure = simulateNetworkFailure;
    }

    /**
     * Retrieves all available news articles.
     * @return A list of all News objects.
     */
    @Override
    public List<News> findAll() {
        System.out.println("NewsRepository: Fetching all news from data source (using " + dataSource.getClass().getSimpleName() + ")");
        return new ArrayList<>(newsStore.values());
    }

    /**
     * Finds a news article by its unique identifier.
     * @param id The ID of the news article to find.
     * @return The News object if found, otherwise null.
     */
    @Override
    public News findById(String id) {
        System.out.println("NewsRepository: Finding news by ID: " + id);
        return newsStore.get(id);
    }

    /**
     * Deletes a news article by its unique identifier.
     * Simulates network exception based on `simulateNetworkFailure` flag.
     * @param newsId The ID of the news article to delete.
     * @return true if the news was successfully deleted, false otherwise.
     * @throws NetworkException if a network connection error occurs during deletion (Supports REQ-EX-3).
     */
    @Override
    public boolean delete(String newsId) throws NetworkException {
        System.out.println("NewsRepository: Attempting to delete news with ID: " + newsId);
        // Simulate network failure if the flag is set
        if (simulateNetworkFailure) {
            System.out.println("NewsRepository: Simulating network failure...");
            throw new NetworkException("Failed to connect to the news database.");
        }

        // Simulate database transaction/validation (REQ-QR-1)
        if (!newsStore.containsKey(newsId)) {
            System.out.println("NewsRepository: News with ID " + newsId + " not found for deletion.");
            return false; // News not found
        }

        News removedNews = newsStore.remove(newsId);
        if (removedNews != null) {
            System.out.println("NewsRepository: Successfully deleted news with ID: " + newsId);
            return true;
        }
        System.out.println("NewsRepository: Failed to delete news with ID: " + newsId + " (unknown reason).");
        return false;
    }
}