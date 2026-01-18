package com.newsagency.repository;

import com.newsagency.model.News;
import java.util.List;

/**
 * Repository interface for News entity operations.
 */
public interface NewsRepository {
    News save(News news);
    News findById(String id);
    List<News> findAll();
    boolean delete(String id);
}