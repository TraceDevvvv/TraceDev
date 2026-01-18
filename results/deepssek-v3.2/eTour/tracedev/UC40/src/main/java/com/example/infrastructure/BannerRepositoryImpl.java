
package com.example.infrastructure;

import com.example.domain.Banner;
import com.example.domain.BannerRepository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * JDBC implementation of BannerRepository.
 */
public class BannerRepositoryImpl implements BannerRepository {
    private final DataSource dataSource;

    public BannerRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Banner> findByPointOfRest(String pointOfRestId) {
        List<Banner> banners = new ArrayList<>();
        String sql = "SELECT * FROM banners WHERE point_of_rest_id = ? AND deleted_at IS NULL";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, pointOfRestId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                banners.add(mapRowToBanner(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error while fetching banners", e);
        }
        return banners;
    }

    @Override
    public Optional<Banner> findById(String bannerId) {
        String sql = "SELECT * FROM banners WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bannerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return Optional.of(mapRowToBanner(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error while finding banner", e);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Banner banner) {
        String sql = "DELETE FROM banners WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, banner.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error while deleting banner", e);
        }
    }

    @Override
    public Banner save(Banner banner) {
        if (banner.getId() == null) {
            throw new UnsupportedOperationException("Insert not implemented");
        }
        String sql = "UPDATE banners SET is_active = ?, deleted_at = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, banner.isActive());
            if (banner.getDeletedAt() != null) {
                stmt.setTimestamp(2, new Timestamp(banner.getDeletedAt().getTime()));
            } else {
                stmt.setNull(2, Types.TIMESTAMP);
            }
            stmt.setString(3, banner.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error while updating banner", e);
        }
        return banner;
    }

    private Banner mapRowToBanner(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String pointOfRestId = rs.getString("point_of_rest_id");
        String imageUrl = rs.getString("image_url");
        boolean isActive = rs.getBoolean("is_active");
        Timestamp createdAt = rs.getTimestamp("created_at");
        Timestamp deletedAt = rs.getTimestamp("deleted_at");
        return new Banner(id, pointOfRestId, imageUrl, isActive, createdAt, deletedAt);
    }

    /**
     * New method to find by point of rest (sequence diagram message m4/m5).
     * This is already implemented as findByPointOfRest, but added to ensure visibility.
     */
    public List<Banner> findByPointOfRestId(String pointOfRestId) {
        return findByPointOfRest(pointOfRestId);
    }
}
