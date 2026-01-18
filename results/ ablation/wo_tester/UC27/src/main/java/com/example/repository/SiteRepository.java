package com.example.repository;

import com.example.db.DatabaseConnection;
import com.example.model.SearchCriteria;
import com.example.model.Site;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for accessing Site data from the database.
 */
public class SiteRepository {
    private DatabaseConnection databaseConnection;

    public SiteRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public DatabaseConnection getDatabaseConnection() {
        return databaseConnection;
    }

    /**
     * Finds all sites matching the given criteria.
     *
     * @param searchCriteria the search criteria
     * @return list of matching sites
     * @throws SQLException if database operation fails
     */
    public List<Site> findAllByCriteria(SearchCriteria searchCriteria) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            connection = handleServerConnection();
            String query = buildQuery(searchCriteria);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            return createSiteObjects(resultSet);
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                databaseConnection.closeConnection(connection);
            }
        }
    }

    /**
     * Builds SQL query based on search criteria.
     *
     * @param searchCriteria the search criteria
     * @return SQL query string
     */
    protected String buildQuery(SearchCriteria searchCriteria) {
        // Simplified: in a real application, this would build a dynamic SQL query
        StringBuilder query = new StringBuilder("SELECT * FROM sites WHERE 1=1");
        if (searchCriteria.getSiteName() != null && !searchCriteria.getSiteName().isEmpty()) {
            query.append(" AND site_name LIKE '%").append(searchCriteria.getSiteName()).append("%'");
        }
        if (searchCriteria.getLocation() != null && !searchCriteria.getLocation().isEmpty()) {
            query.append(" AND location = '").append(searchCriteria.getLocation()).append("'");
        }
        // Metadata filters omitted for simplicity
        return query.toString();
    }

    /**
     * Handles server connection (protected as per class diagram).
     *
     * @return Connection object
     * @throws SQLException if connection fails
     */
    protected Connection handleServerConnection() throws SQLException {
        return databaseConnection.getConnection();
    }

    /**
     * Creates Site objects from a ResultSet.
     *
     * @param resultSet the result set from database query
     * @return list of Site objects
     * @throws SQLException if processing fails
     */
    public List<Site> createSiteObjects(ResultSet resultSet) throws SQLException {
        List<Site> sites = new ArrayList<>();
        while (resultSet.next()) {
            String siteId = resultSet.getString("site_id");
            String siteName = resultSet.getString("site_name");
            String filePath = resultSet.getString("file_path");
            Date lastModified = resultSet.getDate("last_modified");
            sites.add(new Site(siteId, siteName, filePath, lastModified));
        }
        return sites;
    }
}