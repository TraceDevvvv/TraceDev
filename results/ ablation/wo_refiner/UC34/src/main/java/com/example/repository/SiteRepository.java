
package com.example.repository;

import com.example.dto.SearchCriteriaDTO;
import com.example.exception.ETOURConnectionException;
import com.example.model.Site;
import com.example.server.ETOURServer;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

/**
 * Concrete repository implementing ISiteRepository.
 * Handles database operations and connection errors (REQ-011).
 */
public class SiteRepository implements ISiteRepository {
    private DataSource dataSource;
    private ETOURServer etourServer; // Using ETOURServer as per diagram

    public SiteRepository(DataSource ds) throws SQLException {
        this.dataSource = ds;
        this.etourServer = new ETOURServer(ds); // Assuming ETOURServer wraps DataSource
    }

    /**
     * Finds sites matching the given criteria.
     * Throws ETOURConnectionException on connection failure.
     */
    @Override
    public List<Site> findByCriteria(SearchCriteriaDTO criteria) {
        // Build query from criteria (simplified)
        String query = buildQuery(criteria);
        try {
            ResultSet resultSet = etourServer.executeQuery(query);
            return convertResultSetToSites(resultSet);
        } catch (SQLException e) {
            handleDatabaseError();
            throw new RuntimeException("Failed to connect to ETOUR server", e);
        }
    }

    /**
     * Converts a ResultSet to a list of Site objects.
     */
    public List<Site> convertResultSetToSites(ResultSet resultSet) throws SQLException {
        List<Site> sites = new ArrayList<>();
        while (resultSet.next()) {
            Site site = new Site(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getString("location"),
                resultSet.getString("heritage_type"),
                resultSet.getString("historical_period")
            );
            sites.add(site);
        }
        return sites;
    }

    /**
     * Handles database errors (REQ-011).
     */
    public void handleDatabaseError() {
        // Log error, notify monitoring, etc.
        System.err.println("Database connection error occurred.");
    }

    private String buildQuery(SearchCriteriaDTO criteria) {
        // Simplified query building; real implementation would use parameterized queries.
        StringBuilder sb = new StringBuilder("SELECT * FROM sites WHERE 1=1");
        if (criteria.getKeywords() != null && !criteria.getKeywords().isEmpty()) {
            sb.append(" AND (name LIKE '%").append(criteria.getKeywords()).append("%' OR description LIKE '%").append(criteria.getKeywords()).append("%')");
        }
        if (criteria.getLocation() != null && !criteria.getLocation().isEmpty()) {
            sb.append(" AND location LIKE '%").append(criteria.getLocation()).append("%'");
        }
        if (criteria.getHeritageType() != null && !criteria.getHeritageType().isEmpty()) {
            sb.append(" AND heritage_type = '").append(criteria.getHeritageType()).append("'");
        }
        // Note: dateRange and userLocation not included in query for simplicity.
        return sb.toString();
    }
}
