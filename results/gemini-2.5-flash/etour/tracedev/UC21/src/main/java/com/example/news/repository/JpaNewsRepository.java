package com.example.news.repository;

import com.example.news.model.News;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Concrete implementation of NewsRepository, simulating a JPA-like persistence layer.
 * Uses an in-memory map for storage.
 */
public class JpaNewsRepository implements NewsRepository {

    // Simulates a database table in memory
    private final Map<String, News> newsStore = new HashMap<>();

    // Flag to simulate connection interruption for ETOUR scenario
    private boolean simulateConnectionInterruption = false;

    /**
     * Saves a News entity.
     * If news.id is null or empty, a new ID is generated.
     *
     * @param news The News entity to save.
     * @return The saved News entity.
     * @throws ConnectionException if simulateConnectionInterruption is true.
     */
    @Override
    public News save(News news) throws ConnectionException {
        // Step 8: System stores data - Simulate Connection Interruption
        if (simulateConnectionInterruption) {
            throw new ConnectionException("Simulated connection interruption to the database.");
        }

        // Directly access public fields
        if (news.id == null || news.id.isEmpty()) {
            news.id = UUID.randomUUID().toString(); // Assign a new ID for new news
        }
        newsStore.put(news.id, news);
        System.out.println("[Repository] News saved: " + news.id);
        return news;
    }

    /**
     * Finds a News entity by its unique identifier.\n     *\n     * @param id The ID of the news.\n     * @return An Optional containing the News entity if found, otherwise empty.\n     */
    @Override
    public Optional<News> findById(String id) {
        return Optional.ofNullable(newsStore.get(id));
    }

    /**
     * Setter to control the simulation of connection interruptions.
     * @param simulateConnectionInterruption If true, subsequent save calls will throw ConnectionException.
     */
    public void setSimulateConnectionInterruption(boolean simulateConnectionInterruption) {
        this.simulateConnectionInterruption = simulateConnectionInterruption;
        System.out.println("[Repository] Connection interruption simulation set to: " + simulateConnectionInterruption);
    }
}