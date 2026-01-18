package com.news.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Simple DataSource to manage database connections.
 * For simplicity, we use a basic implementation.
 * In a real application, this would be a connection pool or a more sophisticated datasource.
 */
public class DataSource {
    private String connectionUrl;

    public DataSource(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }

    /**
     * Gets a database connection.
     * @return a Connection object
     * @throws SQLException if connection fails
     */
    public Connection getConnection() throws SQLException {
        // Assuming a simple JDBC connection for demonstration.
        // In production, use proper connection pooling.
        return DriverManager.getConnection(connectionUrl);
    }

    public String getConnectionUrl() {
        return connectionUrl;
    }

    public void setConnectionUrl(String connectionUrl) {
        this.connectionUrl = connectionUrl;
    }
}