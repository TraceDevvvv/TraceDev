package com.example.banner.infrastructure.persistence;

import com.example.banner.domain.model.Banner;
import com.example.banner.domain.port.BannerRepository;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of BannerRepository using a relational database.
 */
public class BannerRepositoryImpl implements BannerRepository {
    private DataSource dataSource;

    public BannerRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Banner> findByPointOfRestId(String pointOfRestId) {
        List<Banner> banners = new ArrayList<>();
        String query = "SELECT * FROM banner WHERE point_of_rest_id = ? AND active = true";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, pointOfRestId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Banner banner = mapResultSetToBanner(rs);
                banners.add(banner);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error while fetching banners by pointOfRestId", e);
        }
        return banners;
    }

    @Override
    public Optional<Banner> findById(String id) {
        String query = "SELECT * FROM banner WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return Optional.of(mapResultSetToBanner(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Database error while fetching banner by id", e);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Banner banner) {
        deleteById(banner.getId());
    }

    @Override
    public void deleteById(String id) {
        // Permanent deletion (or logical delete if needed).
        String query = "DELETE FROM banner WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Database error while deleting banner", e);
        }
    }

    private Banner mapResultSetToBanner(ResultSet rs) throws SQLException {
        String id = rs.getString("id");
        String pointOfRestId = rs.getString("point_of_rest_id");
        String imageUrl = rs.getString("image_url");
        boolean active = rs.getBoolean("active");
        Date createdAt = rs.getTimestamp("created_at");
        return new Banner(id, pointOfRestId, imageUrl, active, createdAt);
    }
}