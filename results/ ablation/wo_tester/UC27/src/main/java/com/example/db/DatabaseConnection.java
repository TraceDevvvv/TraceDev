package com.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages database connections.
 */
public class DatabaseConnection {
    private String url;
    private int timeout;

    public DatabaseConnection(String url, int timeout) {
        this.url = url;
        this.timeout = timeout;
    }

    /**
     * Gets a database connection.
     *
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public Connection getConnection() throws SQLException {
        // Simplified: actual implementation would use connection pooling, credentials, etc.
        return DriverManager.getConnection(url);
    }

    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Log error
                e.printStackTrace();
            }
        }
    }

    // Private method for internal use (as per class diagram)
    private Connection establishConnection() throws SQLException {
        return getConnection();
    }
}