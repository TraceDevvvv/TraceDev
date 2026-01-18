package com.example.infrastructure.repository;

import com.example.application.repository.TouristRepository;
import com.example.core.domain.Tourist;
import com.example.infrastructure.database.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation of TouristRepository using a DatabaseConnection.
 * Handles actual database operations.
 */
public class TouristRepositoryImpl implements TouristRepository {
    private DatabaseConnection connection;

    public TouristRepositoryImpl(DatabaseConnection connection) {
        this.connection = connection;
    }

    @Override
    public Tourist findById(Long id) {
        // Execute query to fetch tourist by ID
        String query = "SELECT * FROM tourists WHERE id = " + id;
        try {
            ResultSet rs = connection.executeQuery(query);
            if (rs.next()) {
                // Create Tourist object from ResultSet
                Tourist tourist = new Tourist(
                    rs.getLong("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getString("phoneNumber"),
                    rs.getString("nationality")
                );
                return tourist;
            }
        } catch (SQLException e) {
            // In a real application, handle or rethrow as a custom exception
            throw new RuntimeException("Database error while finding tourist by id: " + id, e);
        }
        return null; // Tourist not found
    }

    @Override
    public Tourist save(Tourist tourist) {
        // Execute update to save/update tourist in database
        String query = String.format(
            "UPDATE tourists SET name = '%s', email = '%s', phoneNumber = '%s', nationality = '%s' WHERE id = %d",
            tourist.getName(), tourist.getEmail(), tourist.getPhoneNumber(), tourist.getNationality(), tourist.getId()
        );
        try {
            int rowsAffected = connection.executeUpdate(query);
            if (rowsAffected > 0) {
                // Update the tourist object (if needed) and return
                tourist.updateProfile(tourist.getName(), tourist.getEmail(), tourist.getPhoneNumber(), tourist.getNationality());
                return tourist;
            } else {
                throw new RuntimeException("Update failed for tourist id: " + tourist.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error while saving tourist: " + tourist.getId(), e);
        }
    }
}