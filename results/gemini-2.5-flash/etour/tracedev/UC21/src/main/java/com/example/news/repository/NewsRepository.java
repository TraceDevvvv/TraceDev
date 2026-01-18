package com.example.news.repository;

import com.example.news.model.News;

import java.util.Optional;

/**
 * Interface for News data access operations (Repository).
 * Defines the contract for how news entities are persisted and retrieved.
 */
public interface NewsRepository {

    /**
     * Saves a News entity to the data store.
     * If the news entity has an ID, it might update an existing one; otherwise, it inserts a new one.
     *
     * @param news The News entity to save.
     * @return The saved News entity, potentially with an updated ID or other system-generated fields.
     * @throws ConnectionException if there's an issue connecting to the data store.
     */
    News save(News news) throws ConnectionException;

    /**
     * Finds a News entity by its unique identifier.
     *
     * @param id The ID of the news entity to find.
     * @return An Optional containing the News entity if found, or an empty Optional if not found.
     */
    Optional<News> findById(String id);
}