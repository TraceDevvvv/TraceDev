package com.example.infrastructure.database;

import com.example.core.domain.News;
import com.example.core.ports.repository.NewsRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Repository implementation using a simulated database connection.
 */
public class DatabaseNewsRepository implements NewsRepository {
    private final DatabaseConnection connection;
    // In-memory simulation for demonstration.
    private final List<News> newsStore = new ArrayList<>();

    public DatabaseNewsRepository(DatabaseConnection connection) {
        this.connection = connection;
        // Populate with some sample data.
        newsStore.add(new News("1", "Title 1", "Content 1", java.time.LocalDateTime.now()));
        newsStore.add(new News("2", "Title 2", "Content 2", java.time.LocalDateTime.now().minusDays(1)));
    }

    @Override
    public List<News> findAll() {
        return new ArrayList<>(newsStore);
    }

    @Override
    public Optional<News> findById(String id) {
        return newsStore.stream().filter(news -> news.getId().equals(id)).findFirst();
    }

    @Override
    public void delete(String id) {
        // Validate news exists (as per sequence diagram step).
        validateNewsExists(id);
        Optional<News> toDelete = findById(id);
        if (toDelete.isPresent()) {
            newsStore.remove(toDelete.get());
            System.out.println("Deleted news with id: " + id);
        } else {
            throw new IllegalArgumentException("News not found for deletion: " + id);
        }
    }

    /**
     * Sequence diagram step: validate news exists.
     */
    private void validateNewsExists(String id) {
        if (findById(id).isEmpty()) {
            throw new IllegalArgumentException("News to delete does not exist: " + id);
        }
    }
}