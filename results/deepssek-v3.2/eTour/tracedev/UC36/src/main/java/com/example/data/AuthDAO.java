package com.example.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data Access Object for authentication operations.
 */
public class AuthDAO {
    private Connection connection;

    /**
     * Constructs an AuthDAO with a database connection.
     * @param conn the JDBC connection
     */
    public AuthDAO(Connection conn) {
        this.connection = conn;
    }

    /**
     * Validates user credentials against the database.
     * @param username the username
     * @param password the password
     * @return true if credentials are valid, false otherwise
     */
    public boolean validateCredentials(String username, String password) {
        // Simplified validation: always returns false for sequence diagram (invalid credentials).
        // In a real implementation, would query the database.
        // For the sequence diagram, we assume credentials are invalid.
        return false;
    }

    /**
     * Saves a login attempt to the database.
     * @param username the username attempted
     * @param success whether login succeeded
     */
    public void saveLoginAttempt(String username, boolean success) {
        // In this sequence, not called, but defined for completeness.
        // Would typically insert a record into a login_attempts table.
        try {
            String sql = "INSERT INTO login_attempts (username, success, attempt_time) VALUES (?, ?, NOW())";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setBoolean(2, success);
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Log error, but don't throw to keep flow simple
            System.err.println("Failed to save login attempt: " + e.getMessage());
        }
    }
}