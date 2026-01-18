package com.example.repository;

import com.example.model.Convention;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 * Database implementation of ConventionRepository.
 * Assumption: Uses a simple JDBC connection.
 */
public class ConventionDatabaseRepository implements ConventionRepository {
    private Connection databaseConnection;

    public ConventionDatabaseRepository(Connection connection) {
        this.databaseConnection = connection;
    }

    @Override
    public int save(Convention convention) {
        // Simplified implementation - in real scenario would use proper SQL with generated keys
        String sql = "INSERT INTO conventions(point_of_rest_id, agency_id, start_date, end_date, terms, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = databaseConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, convention.getPointOfRestId());
            stmt.setInt(2, convention.getAgencyId());
            stmt.setDate(3, new java.sql.Date(convention.getStartDate().getTime()));
            stmt.setDate(4, new java.sql.Date(convention.getEndDate().getTime()));
            stmt.setString(5, convention.getTerms());
            stmt.setString(6, convention.getStatus().name());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating convention failed, no rows affected.");
            }

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Creating convention failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            // In production, use proper logging and exception handling
            System.err.println("Error saving convention: " + e.getMessage());
            return -1; // Return -1 to indicate failure
        }
    }

    @Override
    public Convention findById(int conventionId) {
        String sql = "SELECT * FROM conventions WHERE id = ?";
        try (PreparedStatement stmt = databaseConnection.prepareStatement(sql)) {
            stmt.setInt(1, conventionId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Convention(
                        rs.getInt("point_of_rest_id"),
                        rs.getInt("agency_id"),
                        new Date(rs.getDate("start_date").getTime()),
                        new Date(rs.getDate("end_date").getTime()),
                        rs.getString("terms")
                );
            } else {
                return null; // Convention not found
            }
        } catch (SQLException e) {
            System.err.println("Error finding convention: " + e.getMessage());
            return null;
        }
    }
}