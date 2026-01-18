package com.example.data;

import com.example.domain.Site;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete repository that accesses Site data from a database.
 * Implements SiteRepository as per the Class Diagram.
 */
public class DatabaseSiteRepository implements SiteRepository {
    private DataSource dataSource;

    // Assumption: DataSource is injected via constructor (e.g., from a connection pool)
    public DatabaseSiteRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Site> findAll() {
        List<Site> sites = new ArrayList<>();
        String query = "SELECT site_id, name, address FROM site";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("site_id");
                String name = rs.getString("name");
                String address = rs.getString("address");
                sites.add(new Site(id, name, address));
            }
        } catch (SQLException e) {
            // Rethrow as a runtime exception to match sequence diagram service interruption.
            throw new ConnectionException("Failed to fetch sites from database", e);
        }
        return sites;
    }

    @Override
    public Site findById(int id) {
        String query = "SELECT site_id, name, address FROM site WHERE site_id = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String address = rs.getString("address");
                    return new Site(id, name, address);
                }
            }
        } catch (SQLException e) {
            throw new ConnectionException("Failed to fetch site by id: " + id, e);
        }
        return null;
    }
}