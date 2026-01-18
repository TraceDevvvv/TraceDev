package com.newsagency.system.repository;

import com.newsagency.system.entity.News;
import java.util.Optional;

/**
 * Repository interface for News entities.
 */
public interface NewsRepository {
    /**
     * Saves a news entity.
     * @param news the news entity to save
     * @return the saved news entity (with generated ID)
     */
    News save(News news);

    /**
     * Finds a news entity by ID.
     * @param id the news ID
     * @return Optional containing the news if found
     */
    Optional<News> findById(Long id);
}