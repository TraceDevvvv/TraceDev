package com.example.agency;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;

/**
 * Implementation of NewsRepository using in-memory storage.
 * A DataSource dependency is simulated.
 */
public class NewsRepositoryImpl implements NewsRepository {
    // Simulating DataSource
    private DataSource dataSource;
    private Map<Integer, News> newsDatabase;
    private boolean connectionLost = false; // Simulating connection state

    public NewsRepositoryImpl() {
        newsDatabase = new HashMap<>();
        // Initialize with sample data
        initializeSampleData();
    }

    private void initializeSampleData() {
        News news1 = new News(1, "Tourism Booms", "Tourism industry sees record growth...", "John Doe", new Date());
        News news2 = new News(2, "New Tourist Attraction", "A new landmark opens in the city...", "Jane Smith", new Date());
        newsDatabase.put(1, news1);
        newsDatabase.put(2, news2);
    }

    @Override
    public News findById(int newsId) {
        // Simulating connection interruption
        if (connectionLost) {
            throw new RuntimeException("Connection lost to server ETOUR");
        }
        return newsDatabase.get(newsId);
    }

    @Override
    public News save(News news) {
        // Simulating connection interruption
        if (connectionLost) {
            throw new RuntimeException("Connection lost to server ETOUR");
        }
        news.updateLastModified();
        newsDatabase.put(news.getId(), news);
        return news;
    }

    @Override
    public List<News> findAll() {
        // Simulating connection interruption
        if (connectionLost) {
            throw new RuntimeException("Connection lost to server ETOUR");
        }
        return new ArrayList<>(newsDatabase.values());
    }

    /**
     * Simulate connection loss for testing exit condition.
     */
    public void setConnectionLost(boolean connectionLost) {
        this.connectionLost = connectionLost;
    }
}