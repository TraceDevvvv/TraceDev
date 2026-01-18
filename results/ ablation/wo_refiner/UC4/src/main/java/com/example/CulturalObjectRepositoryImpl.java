package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

// Implementation of the cultural object repository
public class CulturalObjectRepositoryImpl implements CulturalObjectRepository {
    private Database database;
    private ETOURServer etourServer;

    public CulturalObjectRepositoryImpl() {
        this.database = new Database();
        this.etourServer = new ETOURServer();
    }

    public CulturalObjectRepositoryImpl(Database database, ETOURServer etourServer) {
        this.database = database;
        this.etourServer = etourServer;
    }

    public Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }

    public ETOURServer getEtourServer() {
        return etourServer;
    }

    public void setEtourServer(ETOURServer etourServer) {
        this.etourServer = etourServer;
    }

    @Override
    public List<CulturalObject> findAll(SearchCriteriaDTO criteria) {
        // Check connection to ETOUR server
        if (!checkConnection()) {
            handleConnectionError();
            throw new ETOURConnectionException("ETOUR server connection failed", "ETOUR_001");
        }

        try {
            // Build SQL query from criteria
            String sql = buildQuery(criteria);
            
            // Execute query
            ResultSet resultSet = database.query(sql);
            
            // Convert ResultSet to CulturalObject list
            return convertResultSetToCulturalObjects(resultSet);
            
        } catch (SQLException e) {
            handleConnectionError();
            throw new ETOURConnectionException("Database query failed: " + e.getMessage(), "DB_001");
        }
    }

    // Checks connection to ETOUR server
    private boolean checkConnection() {
        return etourServer.ping();
    }

    // Handles connection errors
    private void handleConnectionError() {
        // Log the error, alert administrators, etc.
        System.err.println("Connection error occurred at: " + new java.util.Date());
        // In a real system, would implement retry logic or failover
    }

    // Builds SQL query from search criteria
    private String buildQuery(SearchCriteriaDTO criteria) {
        StringBuilder sql = new StringBuilder("SELECT * FROM cultural_objects WHERE 1=1");
        
        if (criteria.getName() != null && !criteria.getName().isEmpty()) {
            sql.append(" AND name LIKE '%").append(criteria.getName()).append("%'");
        }
        
        if (criteria.getCategory() != null && !criteria.getCategory().isEmpty()) {
            sql.append(" AND category = '").append(criteria.getCategory()).append("'");
        }
        
        if (criteria.getDateRange() != null) {
            // Date range filtering would be added here
            sql.append(" /* Date range filtering would be implemented */");
        }
        
        return sql.toString();
    }

    // Converts ResultSet to list of CulturalObject (simulated for demo)
    private List<CulturalObject> convertResultSetToCulturalObjects(ResultSet resultSet) {
        List<CulturalObject> culturalObjects = new ArrayList<>();
        
        // In a real implementation, this would iterate through the ResultSet
        // For demo, create some sample data
        culturalObjects.add(new CulturalObject("1", "Ancient Temple", "A historic temple from ancient times", "Architecture"));
        culturalObjects.add(new CulturalObject("2", "Traditional Dance", "Traditional folk dance performance", "Performing Arts"));
        culturalObjects.add(new CulturalObject("3", "Historic Manuscript", "Ancient handwritten document", "Documents"));
        
        return culturalObjects;
    }
}