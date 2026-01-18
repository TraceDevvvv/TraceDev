package com.example;

import java.sql.*;

/**
 * Manages connection to the server and executes SQL queries.
 * Assumption: Using JDBC for database connectivity.
 */
public class ServerConnectionManager {
    private Connection connection;
    private boolean connected = false;

    public boolean isConnected() {
        return connected;
    }

    /**
     * Connects to the server (database).
     * @return true if connection successful, false otherwise.
     */
    public boolean connectToServer() {
        try {
            // Assumption: Using a dummy URL, user, password for illustration.
            // In real implementation, these would be configured properly.
            String url = "jdbc:mysql://localhost:3306/mydb";
            String user = "user";
            String password = "password";
            connection = DriverManager.getConnection(url, user, password);
            connected = true;
            return true;
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
            connected = false;
            return false;
        }
    }

    public void disconnectFromServer() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error disconnecting: " + e.getMessage());
            }
        }
        connected = false;
    }

    /**
     * Executes a SQL query and returns the ResultSet.
     * @param query the SQL query string
     * @return ResultSet of the query, or null if error
     */
    public ResultSet executeQuery(String query) {
        if (!connected) {
            System.err.println("Not connected to server.");
            return null;
        }
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("Query execution failed: " + e.getMessage());
            return null;
        }
    }

    /**
     * Executes a search query based on criteria.
     * Assumption: Builds a SQL query from search criteria.
     * @param criteria the search criteria
     * @return ResultSet of the search query
     */
    public ResultSet executeSearchQuery(SearchCriteria criteria) {
        // Build query from criteria (simplified for illustration)
        String query = "SELECT * FROM sites WHERE name LIKE '%" + criteria.getSearchTerm() + "%'";
        return executeQuery(query);
    }
}