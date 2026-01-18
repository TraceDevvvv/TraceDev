package com.culturalheritage.adapter.out.external;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Simulated external data source for ETOUR system.
 * In reality, this would manage connection pooling, configuration, etc.
 */
public class ETOURDataSource {
    private String connectionUrl;
    // Simulating connection pool with a simple connection
    private Connection connectionPool;

    public ETOURDataSource(String url) {
        this.connectionUrl = url;
    }

    public Connection getConnection() {
        try {
            // In real app, get from pool
            if (connectionPool == null || connectionPool.isClosed()) {
                connectionPool = DriverManager.getConnection(connectionUrl);
            }
            return connectionPool;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get connection", e);
        }
    }

    public void closeConnection(Connection connection) {
        // In real pool, release connection instead of closing
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}