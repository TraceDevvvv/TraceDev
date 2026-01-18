package com.example.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import javax.sql.DataSource;

import com.example.model.News;

/**
 * Implementation of NewsRepository.
 * Simulates database operations and potential connection loss.
 */
public class NewsRepositoryImpl implements NewsRepository {
    // In-memory simulation of a database
    private static List<News> newsStore = new ArrayList<>();
    private static int nextId = 1;

    // Simulate connection loss with a probability
    private static final double CONNECTION_LOSS_PROBABILITY = 0.1;

    private DataSource dataSource;

    public NewsRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
        // Initialize with some dummy data
        if (newsStore.isEmpty()) {
            newsStore.add(new News(nextId++, "First News", "Content of first news", "op1"));
            newsStore.add(new News(nextId++, "Second News", "Content of second news", "op2"));
        }
    }

    @Override
    public List<News> findAll() {
        // Simulate potential connection loss
        if (shouldFail()) {
            throw new RuntimeException("Simulated connection loss during findAll");
        }
        return new ArrayList<>(newsStore);
    }

    @Override
    public Optional<News> findById(int id) {
        // Simulate potential connection loss
        if (shouldFail()) {
            throw new RuntimeException("Simulated connection loss during findById");
        }
        return newsStore.stream().filter(n -> n.getId() == id).findFirst();
    }

    @Override
    public News save(News news) throws DatabaseException {
        // Simulate potential connection loss
        if (shouldFail()) {
            throw new DatabaseException("Connection Lost to Server ETour", "DB-001");
        }
        if (news.getId() == 0) {
            // new entity
            news = new News(nextId++, news.getTitle(), news.getContent(), news.getAuthor());
            newsStore.add(news);
        } else {
            // update existing
            for (int i = 0; i < newsStore.size(); i++) {
                if (newsStore.get(i).getId() == news.getId()) {
                    newsStore.set(i, news);
                    break;
                }
            }
        }
        return news;
    }

    private boolean shouldFail() {
        // For simulation, randomly decide to fail
        return ThreadLocalRandom.current().nextDouble() < CONNECTION_LOSS_PROBABILITY;
    }
}