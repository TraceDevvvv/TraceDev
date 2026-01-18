package com.example.repository;

import com.example.dto.UserDto;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Concrete implementation of IUserRepository.
 * Assumption: DataSource is injected via constructor; for simplicity, we use a placeholder.
 */
public class UserRepository implements IUserRepository {
    private DataSource dataSource;

    public UserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public UserDto findByUsername(String username) {
        // Execute SELECT query to get user from database
        String sql = "SELECT user_id, username, password_hash FROM users WHERE username = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String userId = rs.getString("user_id");
                String name = rs.getString("username");
                String passwordHash = rs.getString("password_hash");
                return new UserDto(userId, name, passwordHash);
            }
        } catch (SQLException e) {
            // In a real application, we might log this and rethrow a custom exception
            e.printStackTrace();
        }
        return null; // User not found
    }

    @Override
    public boolean updatePassword(String username, String newPasswordHash) throws DatabaseException {
        // Execute UPDATE query to change password
        String sql = "UPDATE users SET password_hash = ? WHERE username = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newPasswordHash);
            pstmt.setString(2, username);
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0; // true if update successful
        } catch (SQLException e) {
            // Simulating connection interruption as per sequence diagram
            throw new DatabaseException("Connection to server interrupted", e);
        }
    }
}