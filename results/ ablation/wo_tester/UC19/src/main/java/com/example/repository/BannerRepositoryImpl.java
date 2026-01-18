package com.example.repository;

import com.example.model.Banner;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of BannerRepository that performs actual database operations.
 * Handles SELECT and DELETE queries as per sequence diagram.
 */
public class BannerRepositoryImpl implements BannerRepository {
    private DataSource dataSource;

    public BannerRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Banner> findByRefreshmentPointId(String pointId) {
        // Sequence diagram message: SELECT * FROM Banner WHERE pointId = ?
        List<Banner> banners = new ArrayList<>();
        String sql = "SELECT * FROM Banner WHERE pointId = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, pointId);
            try (ResultSet rs = ps.executeQuery()) {
                // Sequence diagram message: ResultSet (return)
                while (rs.next()) {
                    Banner banner = mapResultSetToBanner(rs);
                    banners.add(banner);
                }
            }
            System.out.println("Executing: SELECT * FROM Banner WHERE pointId = " + pointId);
        } catch (SQLException e) {
            System.err.println("Error fetching banners by pointId: " + e.getMessage());
        }
        return banners;
    }

    @Override
    public boolean deleteById(String bannerId) {
        // Sequence diagram message: DELETE FROM Banner WHERE bannerId = ?
        String sql = "DELETE FROM Banner WHERE bannerId = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, bannerId);
            int rowsAffected = ps.executeUpdate();
            System.out.println("Executing: DELETE FROM Banner WHERE bannerId = " + bannerId);
            // Simulate ≤2 sec delay as per Quality Requirement
            try {
                Thread.sleep(100); // Simulated short delay
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // Sequence diagram message: Success flag (return)
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting banner: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<Banner> findById(String bannerId) {
        // Sequence diagram message: SELECT * FROM Banner WHERE bannerId = ?
        String sql = "SELECT * FROM Banner WHERE bannerId = ?";
        
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, bannerId);
            try (ResultSet rs = ps.executeQuery()) {
                // Sequence diagram message: ResultSet (return)
                if (rs.next()) {
                    Banner banner = mapResultSetToBanner(rs);
                    System.out.println("Executing: SELECT * FROM Banner WHERE bannerId = " + bannerId);
                    return Optional.of(banner);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching banner by id: " + e.getMessage());
        }
        System.out.println("Executing: SELECT * FROM Banner WHERE bannerId = " + bannerId);
        return Optional.empty();
    }

    private Banner mapResultSetToBanner(ResultSet rs) throws SQLException {
        Banner banner = new Banner();
        banner.setBannerId(rs.getString("bannerId"));
        banner.setContentUrl(rs.getString("contentUrl"));
        banner.setStartDate(rs.getDate("startDate"));
        banner.setEndDate(rs.getDate("endDate"));
        banner.setIsActive(rs.getBoolean("isActive"));
        // Note: refreshmentPoint and refreshmentPointId would need additional joins/queries
        return banner;
    }
}