package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repository for accessing site data from the database.
 */
public class SiteRepository {
    private ServerConnectionManager connectionManager;

    public SiteRepository() {
        this.connectionManager = new ServerConnectionManager();
    }

    /**
     * Finds sites matching the given search criteria.
     * @param searchCriteria the criteria for search
     * @return list of matching sites, empty list if none or error
     */
    public List<Site> findByCriteria(SearchCriteria searchCriteria) {
        List<Site> sites = new ArrayList<>();

        if (connectionManager.isConnected()) {
            ResultSet rs = connectionManager.executeSearchQuery(searchCriteria);
            if (rs != null) {
                sites = convertToSiteList(rs);
            }
        } else {
            // Try to connect if not connected
            if (connectionManager.connectToServer()) {
                ResultSet rs = connectionManager.executeSearchQuery(searchCriteria);
                if (rs != null) {
                    sites = convertToSiteList(rs);
                }
            } else {
                System.err.println("Connection to server failed.");
            }
        }
        return sites;
    }

    /**
     * Converts a ResultSet to a list of Site objects.
     * Assumption: ResultSet columns match Site fields.
     * @param resultSet the SQL ResultSet
     * @return list of Site objects
     */
    private List<Site> convertToSiteList(ResultSet resultSet) {
        List<Site> sites = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String location = resultSet.getString("location");
                String details = resultSet.getString("details");
                sites.add(new Site(id, name, location, details));
            }
        } catch (SQLException e) {
            System.err.println("Error converting ResultSet: " + e.getMessage());
        }
        return sites;
    }

    /**
     * Disconnects from server (called on exit).
     */
    public void disconnect() {
        connectionManager.disconnectFromServer();
    }
}