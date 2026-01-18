package com.system.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Simulated data source for database connections.
 * Note: This is a simplified implementation for demonstration.
 */
public class DataSource {
    private Connection connection;
    private String url;

    public DataSource(String url) {
        this.url = url;
        // In real application, connection would be managed via a connection pool.
    }

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url);
        }
        return connection;
    }

    public ResultSet executeQuery(String query) throws SQLException {
        Statement stmt = getConnection().createStatement();
        return stmt.executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException {
        Statement stmt = getConnection().createStatement();
        return stmt.executeUpdate(query);
    }
}