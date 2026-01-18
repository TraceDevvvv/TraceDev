package smos.system;

import java.sql.*;

/**
 * Simulated database connection wrapper.
 */
public class Database {
    // In a real implementation, this would manage actual JDBC connections
    public void executeQuery(String query) {
        System.out.println("Executing query: " + query);
    }

    public void executeUpdate(String query) {
        System.out.println("Executing update: " + query);
    }

    public void close() {
        System.out.println("Database connection closed.");
    }
}