package com.example.database;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Simulates a database connection and operations.
 * In a real application, this would be a wrapper for JDBC or JPA.
 */
public class Database {
    
    public ResultSet query(String sql) throws SQLException {
        // Simulated database query
        // In reality, this would execute the SQL and return a ResultSet.
        System.out.println("Executing query: " + sql);
        return null;
    }

    public int update(String sql) throws SQLException {
        // Simulated database update
        // In reality, this would execute the SQL and return the number of rows affected.
        System.out.println("Executing update: " + sql);
        return 1;
    }
}