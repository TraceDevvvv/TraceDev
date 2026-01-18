package com.example.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Represents the SMOS database server connection.
 */
public class SMOSDatabase {
    private Connection connection;
    private boolean isConnectionInterrupted;

    public SMOSDatabase(String url, String user, String password) {
        try {
            this.connection = DriverManager.getConnection(url, user, password);
            this.isConnectionInterrupted = false;
        } catch (SQLException e) {
            this.isConnectionInterrupted = true;
            System.err.println("Initial connection failed: " + e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public ResultSet query(String sql) {
        try {
            Statement stmt = connection.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.err.println("Query failed: " + e.getMessage());
            this.isConnectionInterrupted = true;
            return null;
        }
    }

    public boolean reconnect() {
        // In a real implementation, attempt to re-establish the connection.
        System.out.println("Attempting to reconnect to SMOS server...");
        // Simulate reconnection success.
        isConnectionInterrupted = false;
        return true;
    }

    public boolean isConnectionInterrupted() {
        return isConnectionInterrupted;
    }
}