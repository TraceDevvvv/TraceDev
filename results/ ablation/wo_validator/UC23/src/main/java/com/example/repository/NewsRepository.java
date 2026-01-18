package com.example.repository;

import com.example.model.News;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for News entities.
 */
public interface NewsRepository {
    List<News> findAll();
    Optional<News> findById(String id);
    News save(News news);
}