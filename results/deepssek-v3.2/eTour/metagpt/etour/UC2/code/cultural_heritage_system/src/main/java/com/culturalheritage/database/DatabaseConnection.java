package com.culturalheritage.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DatabaseConnection class manages H2 in-memory database connections.
 * This class follows the Singleton pattern to ensure only one database
 * connection is used throughout the application.
 * It handles connection establishment and closing with proper error handling.
 */
public class DatabaseConnection {
    
    // Database connection parameters
    private static final String JDBC_URL = "jdbc:h2:mem:cultural_heritage_db";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";
    
    // Singleton instance
    private static DatabaseConnection instance;
    private Connection connection;
    
    /**
     * Private constructor to prevent instantiation from outside.
     * Initializes the database connection when the class is instantiated.
     * 
     * @throws SQLException if database connection fails
     */
    private DatabaseConnection() throws SQLException {
        try {
            // Load H2 JDBC driver
            Class.forName("org.h2.Driver");
            
            // Establish connection to H2 in-memory database
            this.connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            
            System.out.println("Database connection established successfully.");
        } catch (ClassNotFoundException e) {
            throw new SQLException("H2 JDBC Driver not found. Make sure H2 dependency is included.", e);
        } catch (SQLException e) {
            throw new SQLException("Failed to establish database connection: " + e.getMessage(), e);
        }
    }
    
    /**
     * Returns the singleton instance of DatabaseConnection.
     * Creates a new instance if one doesn't exist.
     * 
     * @return Singleton instance of DatabaseConnection
     * @throws SQLException if database connection fails
     */
    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    
    /**
     * Returns the database connection.
     * 
     * @return The SQL Connection object
     */
    public Connection getConnection() {
        return connection;
    }
    
    /**
     * Closes the database connection.
     * This method handles the "Interruption of the connection to the server ETOUR"
     * requirement by providing a clean way to close connections.
     * 
     * @throws SQLException if closing the connection fails
     */
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Database connection closed successfully.");
            
            // Clear the singleton instance since connection is closed
            instance = null;
        }
    }
    
    /**
     * Checks if the database connection is valid and open.
     * 
     * @return true if connection is open and valid, false otherwise
     */
    public boolean isConnectionValid() {
        if (connection == null) {
            return false;
        }
        
        try {
            return !connection.isClosed() && connection.isValid(5); // 5-second timeout
        } catch (SQLException e) {
            System.err.println("Error checking connection validity: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Creates a new connection if the current one is closed or invalid.
     * This handles connection interruptions gracefully.
     * 
     * @throws SQLException if reconnection fails
     */
    public void reconnectIfNeeded() throws SQLException {
        if (!isConnectionValid()) {
            System.out.println("Database connection lost. Attempting to reconnect...");
            
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            
            // Create new connection
            this.connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);
            System.out.println("Database reconnection successful.");
        }
    }
}