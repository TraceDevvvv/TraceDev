package com.example.infrastructure;

import com.example.domain.Banner;
import com.example.repository.IBannerRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of IBannerRepository.
 */
public class BannerRepositoryImpl implements IBannerRepository {
    private BannerDatabase dataSource;

    public BannerRepositoryImpl(BannerDatabase dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Banner> findByPointOfRestaurant(String pointOfRestaurantId) {
        List<Banner> banners = new ArrayList<>();
        String query = "SELECT * FROM banners WHERE point_of_restaurant_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, pointOfRestaurantId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Banner banner = new Banner(
                    rs.getString("id"),
                    rs.getString("point_of_restaurant_id"),
                    rs.getString("image_key"),
                    rs.getInt("display_order"),
                    rs.getBoolean("is_active")
                );
                banners.add(banner);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return banners;
    }

    @Override
    public Banner save(Banner banner) {
        String update = "UPDATE banners SET image_key = ? WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(update, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, banner.getImageKey());
            stmt.setString(2, banner.getId());
            int affected = stmt.executeUpdate();
            if (affected > 0) {
                return banner;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}