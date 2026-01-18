package com.example.infrastructure;

import com.example.application.ITouristRepository;
import com.example.application.SearchCriteria;
import com.example.domain.Tourist;
import java.util.ArrayList;
import java.util.List;

/**
 * Infrastructure implementation of tourist repository.
 */
public class TouristRepositoryImpl implements ITouristRepository {
    private Object dataSource; // placeholder for actual data source
    private DatabaseConnection connection;

    public TouristRepositoryImpl() {
        // For simulation, we create a default connection
        this.connection = new DatabaseConnection("jdbc:etour://server:3306/db");
        this.connection.connect();
    }

    public TouristRepositoryImpl(DatabaseConnection connection) {
        this.connection = connection;
    }

    @Override
    public List<Tourist> findByCriteria(SearchCriteria criteria) {
        // Check connection as per sequence diagram
        if (!checkConnection()) {
            return handleConnectionError();
        }

        // Simulate database query and mapping
        List<Tourist> results = new ArrayList<>();
        // Dummy data for demonstration
        if (criteria.getNameFilter() == null || criteria.getNameFilter().isEmpty() ||
                "John".contains(criteria.getNameFilter())) {
            results.add(new Tourist("T001", "John Doe", "john@example.com", "active"));
        }
        if (criteria.getNameFilter() == null || criteria.getNameFilter().isEmpty() ||
                "Jane".contains(criteria.getNameFilter())) {
            results.add(new Tourist("T002", "Jane Smith", "jane@example.com", "inactive"));
        }
        return results;
    }

    /**
     * Handles connection error as per alternative flow.
     */
    public List<Tourist> handleConnectionError() {
        System.err.println("Connection to ETOUR server interrupted. Returning empty list.");
        return new ArrayList<>();
    }

    public boolean checkConnection() {
        return connection != null && connection.checkConnection();
    }
}