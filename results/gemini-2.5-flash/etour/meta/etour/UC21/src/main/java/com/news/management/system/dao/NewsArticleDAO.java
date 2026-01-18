package com.news.management.system.dao;

import com.news.management.system.model.NewsArticle;
import com.news.management.system.exception.DatabaseOperationException;

import java.util.List;
import java.util.Optional;

/**
 * Interface for NewsArticle data access operations.
 * Defines the contract for interacting with the persistent storage for NewsArticle objects.
 */
public interface NewsArticleDAO {

    /**
     * Saves a new news article to the persistent storage.
     *
     * @param newsArticle The NewsArticle object to be saved.
     * @throws DatabaseOperationException if a database error occurs during the save operation.
     */
    void save(NewsArticle newsArticle) throws DatabaseOperationException;

    /**
     * Finds a news article by its unique identifier.
     *
     * @param id The unique ID of the news article to find.
     * @return An Optional containing the NewsArticle if found, or an empty Optional if not found.
     * @throws DatabaseOperationException if a database error occurs during the find operation.
     */
    Optional<NewsArticle> findById(String id) throws DatabaseOperationException;

    /**
     * Retrieves all news articles from the persistent storage.
     *
     * @return A list of all NewsArticle objects.
     * @throws DatabaseOperationException if a database error occurs during the retrieval operation.
     */
    List<NewsArticle> findAll() throws DatabaseOperationException;

    /**
     * Updates an existing news article in the persistent storage.
     *
     * @param newsArticle The NewsArticle object with updated information.
     * @throws DatabaseOperationException if a database error occurs during the update operation.
     */
    void update(NewsArticle newsArticle) throws DatabaseOperationException;

    /**
     * Deletes a news article from the persistent storage by its unique identifier.
     *
     * @param id The unique ID of the news article to delete.
     * @throws DatabaseOperationException if a database error occurs during the delete operation.
     */
    void delete(String id) throws DatabaseOperationException;
}