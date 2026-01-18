package com.newsagency.system.repository;

import com.newsagency.system.entity.News;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Concrete implementation of NewsRepository.
 * Simulates a database with an in‑memory map.
 */
public class NewsRepositoryImpl implements NewsRepository {
    // Simulated database storage
    private Map<Long, News> newsStore = new HashMap<>();
    private AtomicLong idGenerator = new AtomicLong(1L);

    @Override
    public News save(News news) {
        if (news.getId() == null) {
            // New entity: assign an ID
            Long newId = idGenerator.getAndIncrement();
            news.setId(newId);
            // Ensure createdAt is set (if not already)
            if (news.getCreatedAt() == null) {
                news.setCreatedAt(new java.util.Date());
            }
        }
        newsStore.put(news.getId(), news);
        System.out.println("Repository: saved news with ID " + news.getId());
        return news;
    }

    @Override
    public Optional<News> findById(Long id) {
        News news = newsStore.get(id);
        return Optional.ofNullable(news);
    }
}