package com.example.repository;

import com.example.model.AgencyOperator;
import com.example.exception.DatabaseConnectionException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

/**
 * Implementation of AgencyRepository using a DataSource.
 * Simulates database operations.
 * Traceability: Repository stereotype from UML.
 */
public class AgencyRepositoryImpl implements AgencyRepository {
    private DataSource dataSource;
    
    public AgencyRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    
    @Override
    public Optional<AgencyOperator> findById(String agencyId) {
        // Simulate database query - in real scenario this would use dataSource
        // For demonstration, we'll simulate potential connection failure
        if (Math.random() < 0.1) { // 10% chance to simulate connection failure
            throw new DatabaseConnectionException("Connection to database lost while querying agency: " + agencyId);
        }
        
        // Mock implementation: would normally query database
        // Here we simulate finding an agency with id "agency123"
        if ("agency123".equals(agencyId)) {
            AgencyOperator operator = new AgencyOperator("agency123", "john_doe", "$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHs8GO7Y9nL8aG9cF4JjWq");
            return Optional.of(operator);
        }
        return Optional.empty();
    }
    
    @Override
    public void save(AgencyOperator agency) {
        // Simulate database update - in real scenario this would use dataSource
        // For demonstration, we'll simulate potential connection failure
        if (Math.random() < 0.1) { // 10% chance to simulate connection failure
            throw new DatabaseConnectionException("Connection to database lost while saving agency: " + agency.getId());
        }
        
        // Mock implementation: would normally update database
        System.out.println("Saved agency operator: " + agency.getId() + " with updated password.");
    }

    /**
     * Query Database - corresponds to message m9 in sequence diagram.
     * This is a self-message on AR (AgencyRepository).
     * In our implementation, the query is performed in findById and save.
     * We add this method for traceability.
     */
    public void queryDatabase() {
        // This method could log or perform a query operation.
        // For now, we leave it empty as the actual queries are in findById and save.
        System.out.println("Querying database...");
    }

    /**
     * Update Database - corresponds to message m19 in sequence diagram.
     * This is a self-message on AR (AgencyRepository).
     * In our implementation, the update is performed in save.
     * We add this method for traceability.
     */
    public void updateDatabase() {
        // This method could log or perform an update operation.
        // For now, we leave it empty as the actual update is in save.
        System.out.println("Updating database...");
    }
}