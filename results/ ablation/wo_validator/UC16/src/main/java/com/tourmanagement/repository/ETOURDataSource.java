package com.tourmanagement.repository;

import java.sql.*;

public class ETOURDataSource {
    private Connection connection;
    private String serverUrl;

    // Assume we have a default constructor with some initialization
    public ETOURDataSource() {
        this.serverUrl = "jdbc:mysql://localhost:3306/etour_db"; // Example
    }

    public void connect() {
        try {
            // In a real scenario, this would load driver and establish connection
            System.out.println("Connecting to ETOUR database at: " + serverUrl);
            // connection = DriverManager.getConnection(serverUrl, "user", "password");
            // For simulation, we just mark as connected
            System.out.println("Connection established.");
        } catch (Exception e) {
            throw new ETOURConnectionException("Failed to connect to ETOUR server", e);
        }
    }

    public void disconnect() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
            System.out.println("Disconnected from ETOUR database.");
        } catch (SQLException e) {
            System.err.println("Error while disconnecting: " + e.getMessage());
        }
    }

    public ResultSet executeQuery(String query) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new ETOURConnectionException("Not connected to database");
        }
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }

    public int executeUpdate(String query) throws SQLException {
        if (connection == null || connection.isClosed()) {
            throw new ETOURConnectionException("Not connected to database");
        }
        Statement stmt = connection.createStatement();
        return stmt.executeUpdate(query);
    }
}