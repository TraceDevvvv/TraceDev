package com.example.repository;

import com.example.model.Refreshment;

/**
 * Concrete implementation of IRefreshmentRepository.
 * In a real application, this would interact with a DataSource (e.g., database).
 */
public class RefreshmentRepository implements IRefreshmentRepository {
    // In a real implementation, DataSource would be injected
    // private DataSource dataSource;

    @Override
    public Refreshment findById(String id) {
        // Mock implementation for demonstration
        // Assumption: A refreshment with the given id exists
        Refreshment refreshment = new Refreshment();
        refreshment.setId(id);
        refreshment.setName("Sample Refreshment");
        refreshment.setDescription("Sample description");
        refreshment.setPrice(10.5);
        refreshment.setAvailableQuantity(100);
        return refreshment;
    }

    @Override
    public Refreshment save(Refreshment refreshment) {
        // Mock implementation: just return the same object as if saved
        // In real scenario, persist to database and return with generated id/timestamp
        return refreshment;
    }
}