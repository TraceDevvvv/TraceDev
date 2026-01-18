package com.news.management.system.dao;

import com.news.management.system.model.NewsArticle;
import com.news.management.system.exception.DatabaseOperationException;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Implementation of NewsArticleDAO using a simple in-memory store.
 * This class simulates database operations for NewsArticle objects.
 * In a real application, this would interact with a JDBC connection to a database.
 */
public class NewsArticleDAOImpl implements NewsArticleDAO {

    // Using a ConcurrentHashMap to simulate a thread-safe in-memory database.
    // Key: NewsArticle ID, Value: NewsArticle object
    private final ConcurrentHashMap<String, NewsArticle> inMemoryStore;
    private final Connection connection; // Placeholder for a real database connection

    /**
     * Constructs a NewsArticleDAOImpl.
     *
     * @param connection A placeholder for a database connection. Not actively used in this in-memory implementation,
     *                   but included to adhere to the system design's constructor signature.
     */
    public NewsArticleDAOImpl(Connection connection) {
        this.inMemoryStore = new ConcurrentHashMap<>();
        this.connection = connection; // In a real scenario, this connection would be used for JDBC operations.
        // Populate with some dummy data for testing purposes
        if (inMemoryStore.isEmpty()) {
            NewsArticle article1 = new NewsArticle("id1", "First News Title", "This is the content of the first news article.", "Author One", new Date(), List.of("Politics", "Local"), List.of("election", "city"));
            NewsArticle article2 = new NewsArticle("id2", "Second News Story", "Content for the second news story, covering various topics.", "Author Two", new Date(), List.of("Sports"), List.of("football", "championship"));
            inMemoryStore.put(article1.getId(), article1);
            inMemoryStore.put(article2.getId(), article2);
        }
    }

    /**
     * Saves a new news article to the in-memory store.
     *
     * @param newsArticle The NewsArticle object to be saved.
     * @throws DatabaseOperationException if the news article ID already exists (simulating a primary key violation).
     */
    @Override
    public void save(NewsArticle newsArticle) throws DatabaseOperationException {
        if (newsArticle == null || newsArticle.getId() == null) {
            throw new DatabaseOperationException("NewsArticle or its ID cannot be null for saving.", null);
        }
        if (inMemoryStore.containsKey(newsArticle.getId())) {
            throw new DatabaseOperationException("News article with ID " + newsArticle.getId() + " already exists.", null);
        }
        inMemoryStore.put(newsArticle.getId(), newsArticle);
        System.out.println("DEBUG: Saved news article with ID: " + newsArticle.getId());
    }

    /**
     * Finds a news article by its unique identifier from the in-memory store.
     *
     * @param id The unique ID of the news article to find.
     * @return An Optional containing the NewsArticle if found, or an empty Optional if not found.
     * @throws DatabaseOperationException This implementation does not throw this exception for findById,
     *                                    but it's kept in the signature as per interface design.
     */
    @Override
    public Optional<NewsArticle> findById(String id) throws DatabaseOperationException {
        if (id == null) {
            return Optional.empty();
        }
        return Optional.ofNullable(inMemoryStore.get(id));
    }

    /**
     * Retrieves all news articles from the in-memory store.
     *
     * @return A list of all NewsArticle objects.
     * @throws DatabaseOperationException This implementation does not throw this exception for findAll,
     *                                    but it's kept in the signature as per interface design.
     */
    @Override
    public List<NewsArticle> findAll() throws DatabaseOperationException {
        return new ArrayList<>(inMemoryStore.values());
    }

    /**
     * Updates an existing news article in the in-memory store.
     *
     * @param newsArticle The NewsArticle object with updated information.
     * @throws DatabaseOperationException if the news article to update does not exist.
     */
    @Override
    public void update(NewsArticle newsArticle) throws DatabaseOperationException {
        if (newsArticle == null || newsArticle.getId() == null) {
            throw new DatabaseOperationException("NewsArticle or its ID cannot be null for updating.", null);
        }
        if (!inMemoryStore.containsKey(newsArticle.getId())) {
            throw new DatabaseOperationException("News article with ID " + newsArticle.getId() + " not found for update.", null);
        }
        inMemoryStore.put(newsArticle.getId(), newsArticle);
        System.out.println("DEBUG: Updated news article with ID: " + newsArticle.getId());
    }

    /**
     * Deletes a news article from the in-memory store by its unique identifier.
     *
     * @param id The unique ID of the news article to delete.
     * @throws DatabaseOperationException if the news article to delete does not exist.
     */
    @Override
    public void delete(String id) throws DatabaseOperationException {
        if (id == null) {
            throw new DatabaseOperationException("ID cannot be null for deletion.", null);
        }
        if (inMemoryStore.remove(id) == null) {
            throw new DatabaseOperationException("News article with ID " + id + " not found for deletion.", null);
        }
        System.out.println("DEBUG: Deleted news article with ID: " + id);
    }
}