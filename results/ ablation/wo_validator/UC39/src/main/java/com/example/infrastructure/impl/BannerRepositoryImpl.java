package com.example.infrastructure.impl;

import com.example.infrastructure.BannerRepository;
import com.example.domain.Banner;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Adapter implementing BannerRepository using a relational database.
 */
public class BannerRepositoryImpl implements BannerRepository {
    private final DataSource dataSource;

    public BannerRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Banner findById(String bannerId) {
        String sql = "SELECT id, imageUrl, pointOfRestaurantId, isActive FROM banner WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, bannerId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Banner(
                        rs.getString("id"),
                        rs.getString("imageUrl"),
                        rs.getString("pointOfRestaurantId"),
                        rs.getBoolean("isActive")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Banner banner) {
        String sql = "UPDATE banner SET imageUrl = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, banner.getImageUrl());
            stmt.setString(2, banner.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}