package com.example.infrastructure;

import com.example.application.interfaces.ISiteRepository;
import com.example.domain.Site;
import com.example.domain.SearchCriteria;
import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import javax.sql.DataSource;

/**
 * Implementation of site repository that uses a DataSource.
 */
public class SiteRepositoryImpl implements ISiteRepository {
    private DataSource dataSource;

    public SiteRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Site> findByCriteria(SearchCriteria criteria) {
        List<Site> results = new ArrayList<>();
        String sql = "SELECT * FROM sites WHERE name LIKE ? AND category = ? AND rating >= ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, criteria.getName() != null ? "%" + criteria.getName() + "%" : "%");
            stmt.setString(2, criteria.getCategory() != null ? criteria.getCategory() : "");
            stmt.setDouble(3, criteria.getMinRating());
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(convertToSite(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public List<Site> findByLocation(double lat, double lon, double radius) {
        List<Site> results = new ArrayList<>();
        String sql = "SELECT * FROM sites WHERE "
                + "(6371 * acos(cos(radians(?)) * cos(radians(latitude)) * cos(radians(longitude) - radians(?)) + sin(radians(?)) * sin(radians(latitude)))) < ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, lat);
            stmt.setDouble(2, lon);
            stmt.setDouble(3, lat);
            stmt.setDouble(4, radius);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                results.add(convertToSite(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public Site save(Site site) {
        String sql = "INSERT INTO sites (id, name, category, latitude, longitude, rating, amenities, created_date) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, site.getId());
            stmt.setString(2, site.getName());
            stmt.setString(3, site.getCategory());
            stmt.setDouble(4, site.getLatitude());
            stmt.setDouble(5, site.getLongitude());
            stmt.setDouble(6, site.getRating());
            stmt.setString(7, String.join(",", site.getAmenities()));
            stmt.setDate(8, new java.sql.Date(site.getCreatedDate().getTime()));
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                return site;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void delete(String id) {
        String sql = "DELETE FROM sites WHERE id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Site convertToSite(ResultSet record) throws SQLException {
        Site site = new Site(
                record.getString("id"),
                record.getString("name"),
                record.getString("category"),
                record.getDouble("latitude"),
                record.getDouble("longitude"),
                record.getDouble("rating"));
        String amenitiesStr = record.getString("amenities");
        if (amenitiesStr != null) {
            String[] items = amenitiesStr.split(",");
            for (String item : items) {
                site.getAmenities().add(item.trim());
            }
        }
        site.setCreatedDate(new Date(record.getDate("created_date").getTime()));
        return site;
    }
}