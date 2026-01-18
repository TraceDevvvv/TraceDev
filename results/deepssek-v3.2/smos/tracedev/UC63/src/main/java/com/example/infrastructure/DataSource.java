package com.example.infrastructure;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Simplified data source for database connections.
 */
public class DataSource {
    // In a real implementation, this would manage a connection pool.
    public Connection getConnection() throws SQLException {
        // Stub: return a null connection (would be a real connection in production).
        // For compilation, we throw an exception because no real driver is present.
        throw new SQLException("DataSource not configured: no real connection available.");
    }
}