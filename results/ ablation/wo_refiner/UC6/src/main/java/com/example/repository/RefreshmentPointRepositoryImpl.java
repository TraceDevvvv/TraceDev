package com.example.repository;

import com.example.data.DataSource;
import com.example.domain.RefreshmentPoint;
import com.example.error.ErrorHandler;

/**
 * Implementation of the refreshment point repository.
 * Integrates with DataSource and ErrorHandler.
 */
public class RefreshmentPointRepositoryImpl implements IRefreshmentPointRepository {
    private DataSource dataSource;
    private ErrorHandler errorHandler;

    public RefreshmentPointRepositoryImpl() {
        // In a real app, these would be injected
        this.dataSource = new DataSource();
        this.errorHandler = new ErrorHandler();
    }

    @Override
    public RefreshmentPoint findById(int id) {
        try {
            if (!dataSource.isConnected()) {
                throw new RuntimeException("Database connection not available.");
            }
            System.out.println("Finding refreshment point with ID: " + id);
            // Simulate database query
            if (id == 1) {
                return new RefreshmentPoint(1, "Cafe Central", "Main Square", "Cafe");
            } else if (id == 2) {
                return new RefreshmentPoint(2, "Water Fountain", "Park Entrance", "Fountain");
            }
            return null;
        } catch (Exception e) {
            errorHandler.handleDatabaseError(e);
            throw new RuntimeException("Repository error: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(int id) {
        try {
            System.out.println("Deleting refreshment point with ID: " + id);
            // Simulate delete operation
            return true; // Assume success
        } catch (Exception e) {
            errorHandler.handleDatabaseError(e);
            return false;
        }
    }

    @Override
    public void save(RefreshmentPoint entity) {
        try {
            System.out.println("Saving refreshment point: " + entity);
            // Simulate save operation
        } catch (Exception e) {
            errorHandler.handleDatabaseError(e);
        }
    }
}