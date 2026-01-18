package com.example.repository;

import com.example.entity.NewsEntity;

/**
 * Repository interface for news persistence.
 * Stereotype <<reliable>> - must maintain 99.9% availability and max response time < 2s.
 */
public interface NewsRepository {
    boolean save(NewsEntity news);
}