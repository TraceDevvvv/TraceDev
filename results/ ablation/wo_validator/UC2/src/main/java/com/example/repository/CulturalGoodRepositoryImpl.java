
package com.example.repository;

import com.example.domain.CulturalGood;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of CulturalGoodRepository using a relational database via DataSource.
 */
public class CulturalGoodRepositoryImpl implements CulturalGoodRepository {
    private javax.sql.DataSource dataSource;

    /**
     * Constructor with dependency injection.
     * @param dataSource the data source providing database connections
     */
    public CulturalGoodRepositoryImpl(javax.sql.DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public CulturalGood findById(String id) {
        String sql = "SELECT * FROM cultural_goods WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCulturalGood(rs);
            }
        } catch (SQLException e) {
            // Log exception (assumed logging framework available)
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CulturalGood findByName(String name) {
        String sql = "SELECT * FROM cultural_goods WHERE name = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapResultSetToCulturalGood(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(CulturalGood culturalGood) {
        String sql = "INSERT INTO cultural_goods (id, name, description, location, type, date_added) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, culturalGood.getId());
            stmt.setString(2, culturalGood.getName());
            stmt.setString(3, culturalGood.getDescription());
            stmt.setString(4, culturalGood.getLocation());
            stmt.setString(5, culturalGood.getType());
            stmt.setTimestamp(6, new Timestamp(culturalGood.getDateAdded().getTime()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Re-throw as runtime exception to be handled upstream
            throw new RuntimeException("Failed to save cultural good", e);
        }
    }

    @Override
    public boolean existsByName(String name) {
        String sql = "SELECT COUNT(*) FROM cultural_goods WHERE name = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<CulturalGood> findAll() {
        List<CulturalGood> goods = new ArrayList<>();
        String sql = "SELECT * FROM cultural_goods";
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                goods.add(mapResultSetToCulturalGood(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return goods;
    }

    /**
     * Helper method to map a ResultSet row to a CulturalGood object.
     * @param rs the ResultSet
     * @return a CulturalGood
     * @throws SQLException if mapping fails
     */
    private CulturalGood mapResultSetToCulturalGood(ResultSet rs) throws SQLException {
        return new CulturalGood(
                rs.getString("id"),
                rs.getString("name"),
                rs.getString("description"),
                rs.getString("location"),
                rs.getString("type")
        );
    }
}
