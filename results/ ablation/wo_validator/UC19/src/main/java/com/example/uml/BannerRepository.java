package com.example.uml;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * BannerRepository implements IBannerRepository and uses ETOURDataSource for data access.
 */
public class BannerRepository implements IBannerRepository {
    private ETOURDataSource dataSource;

    public BannerRepository() {
        this.dataSource = new ETOURDataSource();
        this.dataSource.connect();
    }

    @Override
    public Banner findById(String bannerId) {
        try {
            ResultSet rs = dataSource.executeQuery("SELECT * FROM banners WHERE id = '" + bannerId + "'");
            // Simulate mapping ResultSet to Banner object.
            if (rs != null) {
                System.out.println("Banner found: " + bannerId);
                return new Banner(bannerId, "http://example.com/banner.png");
            }
        } catch (SQLException e) {
            System.err.println("Database error in findById: " + e.getMessage());
            throw new RuntimeException("Connection to ETOUR interrupted", e);
        }
        return null;
    }

    @Override
    public List<Banner> findByRefreshmentPointId(String refreshmentPointId) {
        List<Banner> banners = new ArrayList<>();
        try {
            ResultSet rs = dataSource.executeQuery("SELECT * FROM banners WHERE refreshmentPointId = '" + refreshmentPointId + "'");
            // Simulate reading multiple rows.
            System.out.println("Found banners for refreshment point: " + refreshmentPointId);
            // For demo, add a dummy banner.
            banners.add(new Banner("B001", "http://example.com/banner1.png"));
            banners.add(new Banner("B002", "http://example.com/banner2.png"));
        } catch (SQLException e) {
            System.err.println("Database error in findByRefreshmentPointId: " + e.getMessage());
            throw new RuntimeException("Connection to ETOUR interrupted", e);
        }
        return banners;
    }

    @Override
    public boolean delete(String bannerId) {
        try {
            int rows = dataSource.executeUpdate("DELETE FROM banners WHERE id = '" + bannerId + "'");
            System.out.println("Deleted banner: " + bannerId);
            return rows > 0;
        } catch (SQLException e) {
            System.err.println("Database error in delete: " + e.getMessage());
            throw new RuntimeException("Connection to ETOUR interrupted", e);
        }
    }
}