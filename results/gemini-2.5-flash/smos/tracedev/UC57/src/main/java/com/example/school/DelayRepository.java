package com.example.school;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Concrete mock implementation of IDelayRepository.
 * Simulates data storage and retrieval for Delay entities.
 * Can simulate SMOSConnectionException to demonstrate error handling (ExC2).
 */
public class DelayRepository implements IDelayRepository {

    // In-memory store for mock data
    private final Map<String, Delay> delayStore = new ConcurrentHashMap<>();
    private static final double EXCEPTION_PROBABILITY = 0.1; // 10% chance to throw an exception

    /**
     * Initializes the repository with some sample data.
     */
    public DelayRepository() {
        // Sample Delays for C101 (Mathematics I)
        delayStore.put("D001", new Delay("D001", "S1001", "C101", new Date(System.currentTimeMillis() - 86400000 * 6), 15, "Traffic congestion"));
        delayStore.put("D002", new Delay("D002", "S1002", "C101", new Date(System.currentTimeMillis() - 86400000 * 2), 5, "Forgot books, had to return home"));

        // Sample Delays for C102 (Physics Fundamentals)
        delayStore.put("D003", new Delay("D003", "S1003", "C102", new Date(System.currentTimeMillis() - 86400000 * 1), 10, "Bus breakdown"));
    }

    /**
     * Simulates a potential connection error to the SMOS server.
     * Added to satisfy requirement ExC2.
     * @throws SMOSConnectionException if a random check fails.
     */
    private void simulateConnectionError() throws SMOSConnectionException {
        if (Math.random() < EXCEPTION_PROBABILITY) {
            throw new SMOSConnectionException("Simulated SMOS server connection interrupted for DelayRepository.");
        }
    }

    @Override
    public Delay findById(String id) throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[DelayRepository] findById(" + id + ")");
        return delayStore.get(id);
    }

    @Override
    public List<Delay> findAll() throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[DelayRepository] findAll()");
        return new ArrayList<>(delayStore.values());
    }

    @Override
    public Delay save(Delay entity) throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[DelayRepository] save(" + entity.getDelayId() + ")");
        delayStore.put(entity.getDelayId(), entity);
        return entity;
    }

    @Override
    public void delete(Delay entity) throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[DelayRepository] delete(" + entity.getDelayId() + ")");
        delayStore.remove(entity.getDelayId());
    }

    @Override
    public List<Delay> findByClassId(String classId) throws SMOSConnectionException {
        simulateConnectionError(); // Simulate connection error
        System.out.println("[DelayRepository] findByClassId(" + classId + ")");
        // QR1: This method always retrieves the most current data.
        return delayStore.values().stream()
                .filter(d -> d.getClassId().equals(classId))
                .collect(Collectors.toList());
    }
}