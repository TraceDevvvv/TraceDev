package com.example.repository;

import com.example.model.Teaching;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Concrete implementation of ITeachingRepository using an in-memory HashMap as a data store.
 * This simulates database persistence for demonstration purposes.
 */
public class TeachingRepository implements ITeachingRepository {
    // In-memory data store for teachings, mapping teachingId to Teaching object
    private final Map<String, Teaching> dataStore = new HashMap<>();

    public TeachingRepository() {
        // Populate with some initial data for demonstration
        List<Teaching> initialTeachings = Arrays.asList(
                new Teaching("t101", "Introduction to Java", "Basic Java concepts and syntax."),
                new Teaching("t102", "Advanced SQL", "Complex queries, stored procedures, and optimization."),
                new Teaching("t103", "Frontend Development Basics", "HTML, CSS, JavaScript fundamentals."),
                new Teaching("t104", "Spring Boot Microserv", "Building REST APIs with Spring Boot."),
                new Teaching("t105", "Cloud Computing Fundamentals", "Introduction to AWS, Azure, GCP.")
        );
        for (Teaching teaching : initialTeachings) {
            dataStore.put(teaching.getId(), teaching);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Teaching findById(String teachingId) {
        // Simulate a database lookup. If not found, returns null.
        System.out.println("TeachingRepo: Querying DB for Teaching ID: " + teachingId);
        return dataStore.get(teachingId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Teaching> findAll() {
        // Simulate fetching all teachings from the database.
        System.out.println("TeachingRepo: Querying DB for all teachings.");
        return new ArrayList<>(dataStore.values()); // Return a copy to prevent external modification of the internal map
    }
}