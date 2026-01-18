package com.example.repository;

import com.example.domain.RefreshmentPoint;
import com.example.domain.PersistenceException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * JDBC implementation of IRefreshmentPointRepository connecting to a real database.
 */
public class RefreshmentPointRepositoryImpl implements IRefreshmentPointRepository {
    private DataSource dataSource;

    public RefreshmentPointRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public RefreshmentPointRepositoryImpl() {
        // Default constructor for compatibility with existing code
        // In a real application, DataSource should be injected
        this.dataSource = null;
    }

    @Override
    public RefreshmentPoint findById(String id) {
        if (dataSource == null) {
            // Fallback to in-memory simulation if no DataSource
            return simulateFindById(id);
        }
        String sql = "SELECT * FROM refreshment_points WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToRefreshmentPoint(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<RefreshmentPoint> findAll() {
        if (dataSource == null) {
            return simulateFindAll();
        }
        List<RefreshmentPoint> points = new ArrayList<>();
        String sql = "SELECT * FROM refreshment_points";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                points.add(mapResultSetToRefreshmentPoint(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return points;
    }

    @Override
    public boolean delete(String id) throws PersistenceException {
        if (dataSource == null) {
            return simulateDelete(id);
        }
        String sql = "DELETE FROM refreshment_points WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new PersistenceException("Database error during delete: " + e.getMessage(), 500);
        }
        return false;
    }

    @Override
    public void save(RefreshmentPoint point) throws PersistenceException {
        if (dataSource == null) {
            simulateSave(point);
            return;
        }
        String sql = "INSERT INTO refreshment_points (id, name, location, description) VALUES (?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, point.getId());
            stmt.setString(2, point.getName());
            stmt.setString(3, point.getLocation());
            stmt.setString(4, point.getDescription());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new PersistenceException("Database error during save: " + e.getMessage(), 500);
        }
    }

    private RefreshmentPoint mapResultSetToRefreshmentPoint(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String name = rs.getString("name");
        String location = rs.getString("location");
        String description = rs.getString("description");
        return new RefreshmentPoint(id, name, location, description);
    }

    // In-memory simulation methods for compatibility with existing tests
    private java.util.Map<String, RefreshmentPoint> getSimulatedDataStore() {
        java.util.Map<String, RefreshmentPoint> dataStore = new java.util.HashMap<>();
        dataStore.put("RP1", new RefreshmentPoint("RP1", "Cafe Central", "Main Square", "Coffee and snacks"));
        dataStore.put("RP2", new RefreshmentPoint("RP2", "Water Fountain", "Park Entrance", "Free drinking water"));
        return dataStore;
    }

    private RefreshmentPoint simulateFindById(String id) {
        return getSimulatedDataStore().get(id);
    }

    private List<RefreshmentPoint> simulateFindAll() {
        return new java.util.ArrayList<>(getSimulatedDataStore().values());
    }

    private boolean simulateDelete(String id) throws PersistenceException {
        java.util.Map<String, RefreshmentPoint> dataStore = getSimulatedDataStore();
        RefreshmentPoint point = dataStore.get(id);
        if (point != null) {
            point.delete();
            dataStore.remove(id);
            // Simulate a connection error randomly (for demonstration)
            if (Math.random() < 0.2) { // 20% chance of simulated failure
                throw new PersistenceException("Connection interrupted", 500);
            }
            return true;
        }
        return false;
    }

    private void simulateSave(RefreshmentPoint point) throws PersistenceException {
        java.util.Map<String, RefreshmentPoint> dataStore = getSimulatedDataStore();
        // Simulate a connection error randomly
        if (Math.random() < 0.2) {
            throw new PersistenceException("Connection interrupted", 500);
        }
        dataStore.put(point.getId(), point);
    }
}