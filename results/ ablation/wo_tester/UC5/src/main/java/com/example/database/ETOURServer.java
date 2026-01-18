package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Represents the ETOUR database server.
 * Database representation added to satisfy consistency requirements.
 * <<exit condition: connection interrupted>> - Added traceability to Exit Conditions
 */
public class ETOURServer {
    private String host;
    private int port;
    private String databaseName;
    
    public ETOURServer(String host, int port, String databaseName) {
        this.host = host;
        this.port = port;
        this.databaseName = databaseName;
    }
    
    /**
     * Establishes connection to the database.
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public Connection connect() throws SQLException {
        String url = "jdbc:mysql://" + host + ":" + port + "/" + databaseName;
        // In real implementation, would use proper credentials
        return DriverManager.getConnection(url, "username", "password");
    }
    
    /**
     * Disconnects from the database.
     * @param connection the connection to close
     */
    public void disconnect(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Error disconnecting: " + e.getMessage());
            }
        }
    }
    
    // Getters and setters
    public String getHost() {
        return host;
    }
    
    public void setHost(String host) {
        this.host = host;
    }
    
    public int getPort() {
        return port;
    }
    
    public void setPort(int port) {
        this.port = port;
    }
    
    public String getDatabaseName() {
        return databaseName;
    }
    
    public void setDatabaseName(String databaseName) {
        this.databaseName = databaseName;
    }
}