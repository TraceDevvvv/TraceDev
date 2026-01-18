package com.example.database;

import java.sql.*;

/**
 * Simpler database wrapper for executing queries.
 * Assumption: For demonstration, this simulates database interaction.
 * In a real application, a proper connection pool and ORM would be used.
 */
public class Database {
    
    public ResultSet executeQuery(String query) throws SQLException {
        // Assumption: Simulating database interaction.
        // In real code, this would create a connection and execute the query.
        System.out.println("Executing query: " + query);
        return null; // Simplified for demonstration.
    }

    public int executeUpdate(String query) throws SQLException {
        // Assumption: Simulating update.
        System.out.println("Executing update: " + query);
        return 1; // Simulating one row updated.
    }
}