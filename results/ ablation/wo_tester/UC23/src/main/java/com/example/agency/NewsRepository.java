package com.example.agency;

import java.util.List;

/**
 * Interface for news data access operations.
 */
public interface NewsRepository {
    News findById(int newsId);
    News save(News news);
    List<News> findAll();
}