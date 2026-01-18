package com.example.data;

import com.example.error.ConnectionErrorHandler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Implementation of ETOUR server data source.
 * Stereotype <<R12>>: Implements reliability measures for server connectivity.
 */
public class ETourServerDataSourceImpl implements IETourServerDataSource, IDataSource {
    private Connection connection;
    private ConnectionErrorHandler errorHandler;

    public ETourServerDataSourceImpl(ConnectionErrorHandler errorHandler) {
        this.errorHandler = errorHandler;
    }

    @Override
    public Connection connect() {
        try {
            // Simplified connection; real implementation would use configuration.
            connection = DriverManager.getConnection("jdbc:mock://etour-server:3306/tourdb", "user", "pass");
            System.out.println("Connected to ETOUR server.");
        } catch (SQLException e) {
            errorHandler.handleConnectionError(e);
        }
        return connection;
    }

    @Override
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Disconnected from ETOUR server.");
            } catch (SQLException e) {
                System.err.println("Error disconnecting: " + e.getMessage());
            }
        }
    }

    @Override
    public ResultSet executeQuery(String sql) {
        ResultSet rs = null;
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            errorHandler.handleConnectionError(e);
        }
        return rs;
    }

    @Override
    public void handleConnectionError() {
        // Delegate to error handler.
        errorHandler.handleConnectionError(new SQLException("ETOUR server connection error"));
    }

    // IDataSource methods
    @Override
    public java.util.List<com.example.domain.TouristAccount> findAllTourists() {
        // Not implemented for this use case.
        return new java.util.ArrayList<>();
    }

    @Override
    public java.util.List<com.example.domain.TouristAccount> findTouristsByCriteria(java.util.Map<String, Object> criteria) {
        // Simulate database query: return a dummy list.
        java.util.List<com.example.domain.TouristAccount> accounts = new java.util.ArrayList<>();
        // Add a dummy account for demonstration.
        accounts.add(new com.example.domain.TouristAccount("1", "John Doe", "john@example.com", "123456789", "USA", new java.util.Date(), "P1234567"));
        return accounts;
    }
}