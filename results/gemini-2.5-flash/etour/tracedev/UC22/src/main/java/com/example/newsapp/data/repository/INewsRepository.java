package com.example.newsapp.data.repository;

import com.example.newsapp.domain.model.News;
import com.example.newsapp.infrastructure.exception.NetworkException;

import java.util.List;

/**
 * Interface for News data access operations.
 * Defines the contract for interacting with news data storage.
 */
public interface INewsRepository {

    /**
     * Retrieves all available news articles.
     * @return A list of all News objects.
     */
    List<News> findAll();

    /**
     * Finds a news article by its unique identifier.
     * @param id The ID of the news article to find.
     * @return The News object if found, otherwise null.
     */
    News findById(String id);

    /**
     * Deletes a news article by its unique identifier.
     * Ensures data integrity via transaction or validation (REQ-QR-1).
     * @param newsId The ID of the news article to delete.
     * @return true if the news was successfully deleted, false otherwise.
     * @throws NetworkException if a network connection error occurs during deletion (Supports REQ-EX-3).
     */
    boolean delete(String newsId) throws NetworkException;
}