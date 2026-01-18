package com.example.smos.repository;

import com.example.smos.model.Teaching;
import com.example.smos.exception.SMOSConnectionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * An implementation of TeachingRepository that simulates interaction with a JPA-like backend.
 * This class uses an in-memory map to store Teaching objects.
 * Corresponds to the 'JpaTeachingRepository' class in the UML Class Diagram.
 */
public class JpaTeachingRepository implements TeachingRepository {

    // In-memory store to simulate a database. ConcurrentHashMap for thread safety in a real app.
    private final Map<String, Teaching> teachingsStore = new ConcurrentHashMap<>();

    // Flag to simulate connection failures for testing alternative flow in sequence diagram.
    private boolean simulateConnectionFailure = false;

    /**
     * Constructor for JpaTeachingRepository. Initializes with some dummy data.
     */
    public JpaTeachingRepository() {
        teachingsStore.put("T001", new Teaching("T001", "Introduction to Java", "Basic Java concepts", "Dr. Smith"));
        teachingsStore.put("T002", new Teaching("T002", "Advanced Algorithms", "Complex data structures", "Prof. Alice"));
        teachingsStore.put("T003", new Teaching("T003", "Software Engineering Principles", "SDLC methodologies", "Ms. Johnson"));
    }

    /**
     * Sets the flag to simulate connection failure.
     * If true, delete and findAll methods will throw SMOSConnectionException.
     * @param simulateConnectionFailure boolean flag
     */
    public void setSimulateConnectionFailure(boolean simulateConnectionFailure) {
        this.simulateConnectionFailure = simulateConnectionFailure;
    }

    /**
     * Deletes a teaching entry.
     * Simulates a database DELETE operation.
     * @param teachingId The ID of the teaching to delete.
     * @throws SMOSConnectionException if connection failure is simulated.
     */
    @Override
    public void delete(String teachingId) throws SMOSConnectionException {
        // Simulate DB connection check
        if (simulateConnectionFailure) {
            System.out.println("Repository: Simulating SMOSConnectionException during delete for ID: " + teachingId);
            throw new SMOSConnectionException("Failed to connect to SMOS archive database during delete operation.");
        }
        System.out.println("Repository: Attempting to delete teaching with ID: " + teachingId);
        Teaching removed = teachingsStore.remove(teachingId);
        if (removed != null) {
            System.out.println("Repository: Successfully deleted teaching: " + teachingId);
        } else {
            System.out.println("Repository: Teaching with ID " + teachingId + " not found.");
        }
    }

    /**
     * Retrieves all teaching entries.
     * Simulates a database SELECT * operation.
     * @return A list of all teachings.
     * @throws SMOSConnectionException if connection failure is simulated.
     */
    @Override
    public List<Teaching> findAll() throws SMOSConnectionException {
        // Simulate DB connection check
        if (simulateConnectionFailure) {
            System.out.println("Repository: Simulating SMOSConnectionException during findAll.");
            throw new SMOSConnectionException("Failed to connect to SMOS archive database during findAll operation.");
        }
        System.out.println("Repository: Retrieving all teachings from DB.");
        return new ArrayList<>(teachingsStore.values()); // Return a copy to prevent external modification
    }
}