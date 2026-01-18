package com.newsapp.repository;

import com.newsapp.entity.News;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for News entities.
 * Corresponds to the NewsRepository interface in the class diagram.
 */
public interface NewsRepository {
    News save(News news);
    Optional<News> findById(Long id);
    List<News> findAll();
}