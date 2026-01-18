package com.tourmanagement.repository;

import com.tourmanagement.domain.Tourist;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TouristRepositoryImpl implements TouristRepository {
    private ETOURDataSource dataSource;

    public TouristRepositoryImpl(ETOURDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Tourist findById(String touristId) {
        // In a real implementation, this would query the database
        // For simulation, return a dummy Tourist
        System.out.println("Finding tourist with ID: " + touristId);
        // Assume we found a tourist
        return new Tourist(touristId, "John Doe", "john@example.com", "1234567890", new Date());
    }

    @Override
    public void delete(String touristId) {
        dataSource.connect();
        try {
            String query = "DELETE FROM tourists WHERE tourist_id = '" + touristId + "'";
            int rowsAffected = dataSource.executeUpdate(query);
            System.out.println("Rows affected: " + rowsAffected);
            if (rowsAffected == 0) {
                // No tourist found with that ID, but we still consider it successful
                // because the end state (tourist not present) is achieved
                System.out.println("No tourist found with ID " + touristId + ", but deletion is considered successful.");
            }
        } catch (SQLException e) {
            throw new ETOURConnectionException("Database error during deletion", e);
        } finally {
            dataSource.disconnect();
        }
    }

    @Override
    public List<Tourist> findAll() {
        // For simulation, return an empty list or some dummy data
        System.out.println("Retrieving all tourists from database.");
        List<Tourist> tourists = new ArrayList<>();
        // Add dummy data for illustration
        tourists.add(new Tourist("T001", "Alice Smith", "alice@example.com", "555-0101", new Date()));
        tourists.add(new Tourist("T002", "Bob Johnson", "bob@example.com", "555-0102", new Date()));
        return tourists;
    }
}