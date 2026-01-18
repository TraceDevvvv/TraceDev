package com.example.repository;

import com.example.model.NewsEntity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implementation of NewsRepository.
 * Assumption: In a real application, this would use a database connection.
 */
public class NewsRepositoryImpl implements NewsRepository {
    // Simulating a database with an in-memory list
    private List<NewsEntity> newsDatabase;

    public NewsRepositoryImpl() {
        // Initialize with some dummy data for demonstration
        newsDatabase = new ArrayList<>();
        newsDatabase.add(new NewsEntity(1, "News 1", "Content 1", new Date(), new Date()));
        newsDatabase.add(new NewsEntity(2, "News 2", "Content 2", new Date(), new Date()));
        newsDatabase.add(new NewsEntity(3, "News 3", "Content 3", new Date(), new Date()));
    }

    @Override
    public List<NewsEntity> findAll() {
        // Simulate SELECT * FROM news
        return new ArrayList<>(newsDatabase);
    }

    @Override
    public NewsEntity findById(int newsId) {
        // Simulate SELECT * FROM news WHERE id = newsId
        return newsDatabase.stream()
                .filter(news -> news.getId() == newsId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void delete(int newsId) {
        // Simulate DELETE FROM news WHERE id = newsId
        // In the sequence diagram, a database exception can be thrown for robustness demonstration
        if (newsId == 999) {
            // Simulate connection lost scenario for REQ-015 demonstration
            throw new RuntimeException("DatabaseException: Connection Lost");
        }
        newsDatabase.removeIf(news -> news.getId() == newsId);
    }
    
    // New method to simulate database participant
    public List<NewsEntity> SELECT_ALL_FROM_news() {
        return findAll();
    }
    
    // New method to simulate database participant
    public NewsEntity SELECT_ALL_FROM_news_WHERE_id(int newsId) {
        return findById(newsId);
    }
    
    // New method to simulate database participant
    public void DELETE_FROM_news_WHERE_id(int newsId) {
        delete(newsId);
    }
}