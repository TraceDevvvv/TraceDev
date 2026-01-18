package com.example.repository;

import com.example.model.Site;
import com.example.model.SiteFeatures;
import com.example.serv.IConnectionMonitor;
import com.example.serv.IDataSource;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Concrete repository implementing bookmark operations with data source.
 */
public class BookmarkRepository implements IBookmarkRepository {
    private IDataSource dataSource;
    private IConnectionMonitor connectionMonitor;

    public BookmarkRepository(IDataSource dataSource, IConnectionMonitor connectionMonitor) {
        this.dataSource = dataSource;
        this.connectionMonitor = connectionMonitor;
    }

    /**
     * Validates connection before operations (REQ-012).
     */
    private void validateConnection() {
        if (!connectionMonitor.checkConnection()) {
            connectionMonitor.logConnectionIssue();
            throw new RuntimeException("Database connection not available.");
        }
        // Also check data source connectivity.
        if (!dataSource.isConnected()) {
            throw new RuntimeException("DataSource is not connected.");
        }
    }

    @Override
    public boolean removeSiteFromBookmarks(String touristId, String siteId) {
        validateConnection();
        String query = "DELETE FROM bookmarks WHERE tourist_id = '" + touristId + "' AND site_id = '" + siteId + "'";
        try {
            dataSource.executeQuery(query);
            return true;
        } catch (Exception e) {
            System.err.println("Error removing site: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String findSiteIdByFeatures(String touristId, SiteFeatures features) {
        validateConnection();
        // Simulate querying by features.
        // In reality, this would be a more complex query.
        String query = "SELECT site_id FROM bookmarks WHERE tourist_id = '" + touristId + "'";
        try {
            ResultSet rs = dataSource.executeQuery(query);
            // For simplicity, assume first result matches.
            if (rs.next()) {
                return rs.getString("site_id");
            }
        } catch (SQLException e) {
            System.err.println("Error finding site by features: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Site> getBookmarkedSites(String touristId) {
        validateConnection();
        List<Site> sites = new ArrayList<>();
        String query = "SELECT * FROM bookmarks WHERE tourist_id = '" + touristId + "'";
        try {
            ResultSet rs = dataSource.executeQuery(query);
            while (rs.next()) {
                String siteId = rs.getString("site_id");
                String name = rs.getString("name");
                String desc = rs.getString("description");
                // Assume features are stored separately; we create dummy.
                SiteFeatures features = new SiteFeatures("cultural", List.of("historic"), 5);
                Site site = new Site(siteId, name, desc, features);
                sites.add(site);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching bookmarked sites: " + e.getMessage());
        }
        return sites;
    }
}