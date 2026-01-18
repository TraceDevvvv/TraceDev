package com.news.repository;

import com.news.entity.News;
import com.news.exception.ConnectionException;
import java.util.List;
import java.util.Optional;

/**
 * Repository interface for News entities as per the class diagram.
 * Provides methods to find and delete news.
 */
public interface NewsRepository {
    List<News> findAll();
    Optional<News> findById(Long id);
    boolean delete(Long newsId) throws ConnectionException;
}