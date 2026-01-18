package com.example.etour.interfaceadapters;

import com.example.etour.entities.RefreshmentPoint;
import com.example.etour.framework.ETOURDataSource;
import com.example.etour.interfaces.RefreshmentPointRepository;
import com.example.etour.usecases.DataAccessException;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Repository implementation that uses a data source to access the database.
 */
public class RefreshmentPointRepositoryImpl implements RefreshmentPointRepository {
    private ETOURDataSource dataSource;

    public RefreshmentPointRepositoryImpl(ETOURDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public RefreshmentPoint findById(String id) {
        try {
            ResultSet rs = dataSource.executeQuery("SELECT * FROM refreshment_points WHERE id = ?", id);
            if (rs.next()) {
                String name = rs.getString("name");
                String location = rs.getString("location");
                return new RefreshmentPoint(id, name, location);
            } else {
                return null;
            }
        } catch (SQLException e) {
            // Wrap SQL exception in a domain-specific exception
            throw new DataAccessException("Error finding refreshment point by id", e);
        }
    }

    @Override
    public boolean delete(String id) {
        try {
            int rowsAffected = dataSource.executeUpdate("DELETE FROM refreshment_points WHERE id = ?", id);
            return rowsAffected > 0;
        } catch (SQLException e) {
            // Attempt to handle connection error as per exceptional flow
            dataSource.handleConnectionError();
            throw new DataAccessException("Error deleting refreshment point", e);
        }
    }
}