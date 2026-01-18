package com.example.datasource;

import com.example.exception.ConnectionException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Simple DataSource to manage database connections.
 */
public class DataSource {
    private final String connectionUrl;
    private final String username;
    private final String password;

    public DataSource(String connectionUrl, String username, String password) {
        this.connectionUrl = connectionUrl;
        this.username = username;
        this.password = password;
    }

    /**
     * Gets a database connection.
     * @throws ConnectionException if connection fails.
     */
    public Connection getConnection() throws ConnectionException {
        try {
            // Assumption: JDBC driver is already loaded
            return DriverManager.getConnection(connectionUrl, username, password);
        } catch (SQLException e) {
            throw new ConnectionException("Failed to connect to database: " + e.getMessage(), 1000);
        }
    }

    public void closeConnection(Connection conn) {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            // Log error but don't throw
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }
}