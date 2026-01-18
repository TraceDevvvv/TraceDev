package com.example.newsapp.repo;

import com.example.newsapp.common.ConnectionException;
import com.example.newsapp.domain.News;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Concrete implementation of INewsRepository.
 * This implementation uses an in-memory HashMap to simulate a database.
 * It also randomly throws ConnectionException to simulate connection failures
 * as per the sequence diagram requirements.
 */
public class NewsRepositoryImpl implements INewsRepository {

    // Simulates a database table using a HashMap where key is news ID and value is News object.
    // In a real application, this would be replaced by JDBC, JPA, etc.
    private final Map<String, News> dataSource;
    private final Random random;
    private static final double CONNECTION_FAILURE_RATE = 0.3; // 30% chance of connection error

    /**
     * Constructor for NewsRepositoryImpl.
     * Initializes the in-memory data store and populates it with some sample data.
     */
    public NewsRepositoryImpl() {
        this.dataSource = new HashMap<>();
        this.random = new Random();
        // Initialize with some dummy data
        save(new News("N001", "Breaking News 1", "Content of breaking news item 1.", new Date(), "Author A", "Published"));
        save(new News("N002", "Sports Highlight", "Summary of recent sports events.", new Date(), "Author B", "Draft"));
        save(new News("N003", "Tech Review", "Review of the latest gadget.", new Date(), "Author C", "Published"));
        save(new News("N004", "Local Event", "Details about an upcoming community event.", new Date(), "Author A", "Archived"));
    }

    /**
     * Simulates a connection failure by throwing a ConnectionException with a certain probability.
     *
     * @throws ConnectionException if a connection failure is simulated.
     */
    private void simulateConnectionFailure() throws ConnectionException {
        if (random.nextDouble() < CONNECTION_FAILURE_RATE) {
            System.out.println("[Repository] Simulating ConnectionException...");
            throw new ConnectionException("Simulated connection to ETOUR server lost.");
        }
    }

    @Override
    public List<News> findAll() throws ConnectionException {
        simulateConnectionFailure();
        System.out.println("[Repository] Finding all news items.");
        return new ArrayList<>(dataSource.values());
    }

    @Override
    public News findById(String id) throws ConnectionException {
        simulateConnectionFailure();
        System.out.println("[Repository] Finding news item by ID: " + id);
        return dataSource.get(id);
    }

    @Override
    public News save(News news) {
        // No ConnectionException specified for save in the diagram, but added a comment in the interface.
        System.out.println("[Repository] Saving news item: " + news.getId());
        if (news.getId() == null || news.getId().isEmpty()) {
            news.setId("N" + (dataSource.size() + 1)); // Simple ID generation
        }
        dataSource.put(news.getId(), news);
        return news;
    }

    @Override
    public News update(News news) throws ConnectionException {
        simulateConnectionFailure();
        System.out.println("[Repository] Updating news item: " + news.getId());
        if (dataSource.containsKey(news.getId())) {
            dataSource.put(news.getId(), news);
            return news;
        }
        return null; // Or throw a NotFoundException
    }
}