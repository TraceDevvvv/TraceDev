package com.etour.db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Simulates a database connection.
 * In a real system, this would be a wrapper around a JDBC connection.
 */
public class Database {
    // In a real implementation, this would hold a JDBC Connection.
    // For this example, we simulate with print statements.

    public ResultSet query(String sql) throws SQLException {
        System.out.println("Executing query: " + sql);
        // Simulate returning a result set (in reality, this would execute via JDBC).
        // Since this is a simulation, we return null; real code would use a mock or actual DB.
        return null;
    }

    public int executeUpdate(String sql) {
        System.out.println("Executing update: " + sql);
        // Simulate successful update
        return 1; // number of rows affected
    }
}