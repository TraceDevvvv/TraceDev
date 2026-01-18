package com.example.infrastructure;

import com.example.exceptions.ConnectionException;
import java.sql.*;
import java.util.Properties;

/**
 * Infrastructure class for database operations
 */
public class ETOURDataSource {
    private Connection connection;
    private String serverURL;
    
    // Assume default constructor for simplicity
    public ETOURDataSource() {
        this.serverURL = "jdbc:hsqldb:mem:testdb";
    }
    
    public ETOURDataSource(String serverURL) {
        this.serverURL = serverURL;
    }

    public boolean connect() {
        try {
            if (connection == null || connection.isClosed()) {
                // Using HSQLDB in-memory database for example
                Properties props = new Properties();
                props.setProperty("user", "SA");
                props.setProperty("password", "");
                connection = DriverManager.getConnection(serverURL, props);
                initializeDatabase();
            }
            return true;
        } catch (SQLException e) {
            throw new ConnectionException("Failed to connect to database", 1001, e);
        }
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new ConnectionException("Failed to disconnect from database", 1002, e);
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        if (!isConnected()) {
            connect();
        }
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException {
        if (!isConnected()) {
            connect();
        }
        Statement stmt = connection.createStatement();
        return stmt.executeUpdate(query);
    }

    public boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }

    /**
     * Initialize the database with sample data for testing
     */
    private void initializeDatabase() throws SQLException {
        // Create table if it doesn't exist
        String createTable = "CREATE TABLE IF NOT EXISTS cultural_heritage (" +
                            "id VARCHAR(50) PRIMARY KEY, " +
                            "name VARCHAR(100), " +
                            "description VARCHAR(500), " +
                            "location VARCHAR(100), " +
                            "historical_period VARCHAR(50), " +
                            "creation_date TIMESTAMP, " +
                            "last_modified TIMESTAMP)";
        executeUpdate(createTable);
        
        // Insert sample data if table is empty
        ResultSet rs = executeQuery("SELECT COUNT(*) FROM cultural_heritage");
        if (rs.next() && rs.getInt(1) == 0) {
            String insertData = "INSERT INTO cultural_heritage VALUES " +
                               "('1', 'Colosseum', 'Ancient Roman amphitheatre', 'Rome', 'Roman Empire', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)," +
                               "('2', 'Great Wall', 'Series of fortifications', 'China', 'Ming Dynasty', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)";
            executeUpdate(insertData);
        }
    }
}