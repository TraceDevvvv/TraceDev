package com.example.repository;

import com.example.entity.Banner;
import com.example.datasource.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of BannerRepository using a DataSource for database access.
 */
public class BannerRepositoryImpl implements BannerRepository {
    private DataSource dataSource;

    public BannerRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Banner> findAllByPointOfRest(String pointOfRestId) {
        List<Banner> banners = new ArrayList<>();
        String sql = "SELECT id, name, image_url, point_of_rest_id FROM banners WHERE point_of_rest_id = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, pointOfRestId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Banner banner = new Banner(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("image_url"),
                    rs.getString("point_of_rest_id")
                );
                banners.add(banner);
            }
        } catch (SQLException e) {
            System.err.println("Error querying banners: " + e.getMessage());
        }
        return banners;
    }

    @Override
    public Banner findById(String bannerId) {
        String sql = "SELECT id, name, image_url, point_of_rest_id FROM banners WHERE id = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, bannerId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return new Banner(
                    rs.getString("id"),
                    rs.getString("name"),
                    rs.getString("image_url"),
                    rs.getString("point_of_rest_id")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error finding banner: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteById(String bannerId) {
        String sql = "DELETE FROM banners WHERE id = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, bannerId);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting banner: " + e.getMessage());
            return false;
        }
    }
}