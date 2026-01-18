package com.example.infrastructure;

import com.example.domain.User;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of UserRepository using SQL database.
 * Assumes a table named "users" with columns matching User attributes.
 */
public class SqlUserRepository implements UserRepository {
    private DataSource dataSource;

    public SqlUserRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public User findById(String id) throws RepositoryException {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToUser(rs);
            } else {
                throw new RepositoryException("User not found with id: " + id);
            }
        } catch (SQLException e) {
            // Simulating connection failure as per sequence diagram alternative flow
            if (e.getMessage().contains("Connection") || e.getMessage().contains("SMOS")) {
                throw new SMOSConnectionException("Connection to the SMOS server interrupted.");
            }
            throw new RepositoryException("Database error: " + e.getMessage());
        }
    }

    @Override
    public List<User> findAll() throws RepositoryException {
        String sql = "SELECT * FROM users";
        List<User> users = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                users.add(mapResultSetToUser(rs));
            }
            return users;
        } catch (SQLException e) {
            throw new RepositoryException("Database error: " + e.getMessage());
        }
    }

    private User mapResultSetToUser(ResultSet rs) throws SQLException {
        return new User(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("surname"),
                rs.getString("email"),
                rs.getString("cell"),
                rs.getString("login"),
                rs.getString("password"),
                rs.getString("role"));
    }
}