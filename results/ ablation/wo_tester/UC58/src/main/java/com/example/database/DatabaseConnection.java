package com.example.database;

import java.sql.ResultSet;

/**
 * Simulated database connection.
 */
public class DatabaseConnection {
    public void connect() {
        System.out.println("Database connected.");
    }

    public void disconnect() {
        System.out.println("Database disconnected.");
    }

    public ResultSet executeQuery(String query) {
        System.out.println("Executing query: " + query);
        // Return null as a simulation; in real code, return a ResultSet.
        return null;
    }

    public boolean isConnected() {
        // Simulated connection status
        return true;
    }
}