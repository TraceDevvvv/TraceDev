package com.culturalheritage.adapter.out.persistence;

import com.culturalheritage.domain.model.CulturalHeritage;
import com.culturalheritage.application.port.out.CulturalHeritageRepository;
import com.culturalheritage.adapter.out.external.ETOURDataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.HashMap;

/**
 * Repository implementation using ETOURDataSource.
 * Handles database operations as per sequence diagram.
 */
public class CulturalHeritageRepositoryImpl implements CulturalHeritageRepository {
    private ETOURDataSource dataSource;

    public CulturalHeritageRepositoryImpl(ETOURDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public CulturalHeritage findById(String id) {
        Connection connection = dataSource.getConnection();
        String query = "SELECT * FROM cultural_heritage WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                CulturalHeritage ch = new CulturalHeritage();
                ch.setId(rs.getString("id"));
                ch.setName(rs.getString("name"));
                ch.setDescription(rs.getString("description"));
                // Assume other attributes are stored as JSON; here simplified
                Map<String, Object> otherAttrs = new HashMap<>();
                // In real scenario, parse JSON column
                ch.setOtherAttributes(otherAttrs);
                return ch;
            }
        } catch (SQLException e) {
            System.err.println("Error finding cultural heritage: " + e.getMessage());
        } finally {
            dataSource.closeConnection(connection);
        }
        return null;
    }

    @Override
    public boolean deleteById(String id) {
        Connection connection = dataSource.getConnection();
        String deleteSQL = "DELETE FROM cultural_heritage WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deleteSQL)) {
            stmt.setString(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting cultural heritage: " + e.getMessage());
            // Simulate server interruption as per alternative flow
            if (e.getMessage().contains("ConnectionLost")) {
                throw new RuntimeException("DeletionFailedException: Server interrupted", e);
            }
            return false;
        } finally {
            dataSource.closeConnection(connection);
        }
    }

    @Override
    public boolean existsById(String id) {
        return findById(id) != null;
    }
}