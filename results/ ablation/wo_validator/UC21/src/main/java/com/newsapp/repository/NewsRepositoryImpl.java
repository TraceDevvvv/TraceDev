package com.newsapp.repository;

import com.newsapp.entity.News;
import java.util.*;
import javax.sql.DataSource;

/**
 * Implementation of NewsRepository.
 * Corresponds to the NewsRepositoryImpl class in the class diagram.
 * Note: This is a simple in‑memory implementation for demonstration.
 * In a real application, it would use the DataSource to persist to a database.
 */
public class NewsRepositoryImpl implements NewsRepository {
    private DataSource dataSource;
    private Map<Long, News> storage = new HashMap<>();
    private Long nextId = 1L;

    // Constructor injecting the DataSource (assumed for infrastructure)
    public NewsRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public News save(News news) {
        // Simulate persistence: assign an ID if new, otherwise update
        if (news.getId() == null) {
            news.setId(nextId++);
        }
        storage.put(news.getId(), news);
        // In a real implementation, we would use dataSource to persist to database
        // For this example, we just store in memory
        System.out.println("News saved with ID: " + news.getId());
        return news;
    }

    @Override
    public Optional<News> findById(Long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<News> findAll() {
        return new ArrayList<>(storage.values());
    }
}