package com.example.repository;

import com.example.model.CulturalHeritage;
import com.example.datasource.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Repository implementation for Cultural Heritage.
 * Maps to the CulturalHeritageRepository class in the UML diagram.
 */
public class CulturalHeritageRepository implements CulturalHeritageRepositoryInterface {
    private DataSource dataSource;

    public CulturalHeritageRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public CulturalHeritage findById(String id) {
        // Implementation as per sequence diagram: executeQuery to select by id
        String query = "SELECT * FROM cultural_heritage WHERE id = ?";
        try (Connection conn = dataSource.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            ResultSet rs = dataSource.executeQuery(stmt.toString()); // Simplified for example
            return mapResultSetToCulturalHeritage(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<CulturalHeritage> findAll() {
        // Not used in sequence diagram, but required by interface
        List<CulturalHeritage> list = new ArrayList<>();
        String query = "SELECT * FROM cultural_heritage";
        try (Connection conn = dataSource.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = dataSource.executeQuery(stmt.toString());
            while (rs.next()) {
                list.add(mapResultSetToCulturalHeritage(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void save(CulturalHeritage culturalHeritage) {
        // Not used in sequence diagram, but required by interface
        String query = "INSERT INTO cultural_heritage (id, title, description, creationDate, location, category) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, culturalHeritage.getId());
            stmt.setString(2, culturalHeritage.getTitle());
            stmt.setString(3, culturalHeritage.getDescription());
            stmt.setDate(4, new java.sql.Date(culturalHeritage.getCreationDate().getTime()));
            stmt.setString(5, culturalHeritage.getLocation());
            stmt.setString(6, culturalHeritage.getCategory());
            dataSource.executeUpdate(stmt.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(CulturalHeritage culturalHeritage) {
        // Implementation as per sequence diagram: executeUpdate to update cultural_heritage
        String query = "UPDATE cultural_heritage SET title = ?, description = ?, creationDate = ?, location = ?, category = ? WHERE id = ?";
        try (Connection conn = dataSource.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, culturalHeritage.getTitle());
            stmt.setString(2, culturalHeritage.getDescription());
            stmt.setDate(3, new java.sql.Date(culturalHeritage.getCreationDate().getTime()));
            stmt.setString(4, culturalHeritage.getLocation());
            stmt.setString(5, culturalHeritage.getCategory());
            stmt.setString(6, culturalHeritage.getId());
            dataSource.executeUpdate(stmt.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String id) {
        // Not used in sequence diagram, but required by interface
        String query = "DELETE FROM cultural_heritage WHERE id = ?";
        try (Connection conn = dataSource.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, id);
            dataSource.executeUpdate(stmt.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Helper method to map a ResultSet to a CulturalHeritage object.
     * Corresponds to the step "mapResultSetToCulturalHeritage()" in the sequence diagram.
     */
    private CulturalHeritage mapResultSetToCulturalHeritage(ResultSet rs) throws SQLException {
        if (rs.next()) {
            String id = rs.getString("id");
            String title = rs.getString("title");
            String description = rs.getString("description");
            Date creationDate = rs.getDate("creationDate");
            String location = rs.getString("location");
            String category = rs.getString("category");
            return new CulturalHeritage(id, title, description, creationDate, location, category);
        }
        return null;
    }
}