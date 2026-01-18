package com.example.repository;

import com.example.model.Convention;
import java.util.HashMap;
import java.util.Map;

/**
 * Simple in-memory implementation of ConventionRepository.
 * In a real application, this would use a DataSource.
 */
public class ConventionRepositoryImpl implements ConventionRepository {
    // Simulating a data source with an in-memory map
    private Map<String, Convention> dataSource = new HashMap<>();

    @Override
    public Convention findById(String id) {
        // In a real application, this would query a database.
        return dataSource.get(id);
    }

    @Override
    public void save(Convention convention) {
        // In a real application, this would persist to a database.
        dataSource.put(convention.getId(), convention);
    }

    // Helper method for testing: add a convention to the data source
    public void addConvention(Convention convention) {
        dataSource.put(convention.getId(), convention);
    }
}