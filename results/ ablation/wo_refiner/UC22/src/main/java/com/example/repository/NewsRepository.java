package com.example.repository;

import com.example.model.NewsEntity;
import java.util.List;

/**
 * Repository interface for news data access.
 */
public interface NewsRepository {
    // Added to satisfy requirement REQ-007
    List<NewsEntity> findAll();
    NewsEntity findById(int newsId);
    void delete(int newsId);
}