package com.example.adapter;

import com.example.datasource.DataSource;
import com.example.exceptions.ConnectionException;
import com.example.model.Teaching;
import com.example.repo.TeachingRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Adapter that implements the TeachingRepository interface,
 * simulating interaction with an archive data source.
 * This adapter handles specific connection-related exceptions.
 */
public class TeachingArchiveAdapter implements TeachingRepository {
    private final DataSource archiveDataSource;
    // Simulating a database for demonstration purposes.
    private final Map<String, Teaching> mockDatabase = new HashMap<>();

    // Static flag to simulate connection interruption for testing purposes.
    // Use an AtomicBoolean for thread-safe modification in a multi-threaded context,
    // though for this single-threaded example, a simple boolean would suffice.
    public static AtomicBoolean simulateConnectionInterruption = new AtomicBoolean(false);

    /**
     * Constructs a TeachingArchiveAdapter.
     * @param dataSource The data source used for archive operations.
     */
    public TeachingArchiveAdapter(DataSource dataSource) {
        this.archiveDataSource = dataSource;
        // Initialize mock data
        mockDatabase.put("T001", new Teaching("T001", "Introduction to Java", "Basic programming concepts in Java."));
        mockDatabase.put("T002", new Teaching("T002", "Advanced Algorithms", "Complex data structures and algorithms."));
    }

    /**
     * Throws a ConnectionException if simulation is active.
     * @throws ConnectionException if simulateConnectionInterruption is true.
     */
    private void checkIfConnectionInterrupted() throws ConnectionException {
        if (simulateConnectionInterruption.get()) {
            throw new ConnectionException("SMOS server connection interrupted. (Simulated)");
        }
    }

    @Override
    public void update(Teaching teaching) throws ConnectionException {
        checkIfConnectionInterrupted();
        // Simulate update operation
        if (mockDatabase.containsKey(teaching.getId())) {
            mockDatabase.put(teaching.getId(), teaching);
            System.out.println("ADAPTER: Teaching with ID " + teaching.getId() + " updated in mock database.");
        } else {
            // In a real scenario, this might throw a "not found" exception
            System.err.println("ADAPTER: Teaching with ID " + teaching.getId() + " not found for update.");
            throw new ConnectionException("Teaching not found for update: " + teaching.getId());
        }
    }

    @Override
    public Teaching findById(String id) throws ConnectionException {
        checkIfConnectionInterrupted();
        // Simulate find operation
        return Optional.ofNullable(mockDatabase.get(id))
                       .orElseThrow(() -> new ConnectionException("Teaching not found by ID: " + id));
    }

    @Override
    public List<Teaching> findAll() throws ConnectionException {
        checkIfConnectionInterrupted();
        // Simulate find all operation
        return new ArrayList<>(mockDatabase.values());
    }

    /**
     * Sets the simulation flag for connection interruption.
     * @param interrupted True to simulate interruption, false otherwise.
     */
    public static void setSimulateConnectionInterruption(boolean interrupted) {
        simulateConnectionInterruption.set(interrupted);
    }
}