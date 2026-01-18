package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;

// Database abstraction layer
public class Database {
    private boolean isConnected;

    public Database() {
        this.isConnected = true; // Assume initially connected
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    // Executes a SQL query and returns a ResultSet
    public ResultSet query(String sql) throws SQLException {
        if (!isConnected) {
            throw new SQLException("Database connection failed");
        }
        
        // In a real implementation, this would execute actual SQL
        // For demo purposes, return null to indicate no real database
        System.out.println("Executing query: " + sql);
        return null;
    }
}