package com.example.repository;

import java.util.List;
import java.util.Optional;

import com.example.model.News;

/**
 * Repository interface for News persistence operations.
 */
public interface NewsRepository {
    List<News> findAll();
    Optional<News> findById(int id);
    News save(News news) throws DatabaseException;
}