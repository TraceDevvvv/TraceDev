package com.example.core.ports.repository;

import com.example.core.domain.News;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for News persistence operations.
 */
public interface NewsRepository {
    List<News> findAll();
    Optional<News> findById(String id);
    void delete(String id);
}