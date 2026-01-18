package com.example.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Simulates a data source providing database connections.
 */
public class DataSource {
    private Connection connection;
    private boolean connected = true; // Simulated connection status

    public Connection getConnection() throws SQLException {
        if (!connected) {
            throw new SQLException("Connection lost: ETOUR connection interruption.");
        }
        if (connection == null || connection.isClosed()) {
            // Simulate obtaining a real connection
            connection = DriverManager.getConnection("jdbc:mock://localhost:3306/test");
        }
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            System.err.println("Error closing connection: " + e.getMessage());
        }
    }

    public boolean isConnected() {
        return connected;
    }

    // For simulation: allow setting connection status
    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}