package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Represents a data source for database connectivity.
 */
public class DataSource {
    private String connectionString;
    private Connection connection;

    /**
     * Constructor with default connection string.
     */
    public DataSource() {
        // Default connection string; in a real application, this would be configurable.
        this.connectionString = "jdbc:sqlite:sample.db";
    }

    /**
     * Constructor with custom connection string.
     * @param connectionString the JDBC connection string
     */
    public DataSource(String connectionString) {
        this.connectionString = connectionString;
    }

    /**
     * Establishes a connection to the database.
     * @return a Connection object
     * @throws SQLException if connection fails
     */
    public Connection connect() throws SQLException {
        // For simplicity, we assume a SQLite database.
        // In a real application, you would use connection pooling and proper configuration.
        connection = DriverManager.getConnection(connectionString);
        return connection;
    }

    /**
     * Disconnects from the database.
     */
    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Log error
                e.printStackTrace();
            }
        }
    }

    /**
     * Executes a SQL query.
     * @param query the SQL query string
     * @return a ResultSet containing the query results
     * @throws SQLException if a database access error occurs
     */
    public ResultSet executeQuery(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }

    public String getConnectionString() {
        return connectionString;
    }

    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
}