package com.example.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Represents a data source for database connections.
 * Maps to the DataSource class in the UML diagram.
 * Note: This is a simplified implementation for the example.
 */
public class DataSource {
    private String serverUrl;
    private int connectionTimeout;
    private Connection connection;

    public DataSource(String serverUrl, int connectionTimeout) {
        this.serverUrl = serverUrl;
        this.connectionTimeout = connectionTimeout;
        this.connection = null;
    }

    public Connection connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
            // Simplified connection - in a real system use connection pooling
            connection = DriverManager.getConnection(serverUrl);
        }
        return connection;
    }

    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Executes a query and returns a ResultSet.
     * Simplified for example - does not actually execute the provided string directly.
     */
    public ResultSet executeQuery(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }

    /**
     * Executes an update query and returns the number of rows affected.
     * Simplified for example.
     */
    public int executeUpdate(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeUpdate(query);
    }

    // Getter and setter for serverUrl and connectionTimeout if needed
    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }
}