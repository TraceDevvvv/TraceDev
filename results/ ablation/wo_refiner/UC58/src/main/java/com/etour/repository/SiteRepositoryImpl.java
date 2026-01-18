package com.etour.repository;

import com.etour.domain.Site;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of SiteRepository.
 * Assumes a relational database with a table named "sites".
 * Requirement R10: Response time < 2 seconds.
 */
public class SiteRepositoryImpl implements SiteRepository {
    private Connection connection;

    public SiteRepositoryImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Optional<Site> findById(String siteId) {
        // Requirement R10: This query should execute within 2 seconds.
        String query = "SELECT * FROM sites WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, siteId);
            ResultSet rs = executeQuery(stmt);
            if (rs.next()) {
                Site site = new Site(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("location"),
                        rs.getDouble("rating"));
                return Optional.of(site);
            }
            return Optional.empty();
        } catch (SQLException e) {
            throw new DataAccessException("Database error while finding site with ID: " + siteId, e);
        }
    }

    @Override
    public List<Site> fetchAll() {
        String query = "SELECT * FROM sites";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = executeQuery(stmt);
            List<Site> sites = new ArrayList<>();
            while (rs.next()) {
                Site site = new Site(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("description"),
                        rs.getString("location"),
                        rs.getDouble("rating"));
                sites.add(site);
            }
            return sites;
        } catch (SQLException e) {
            throw new DataAccessException("Database error while fetching all sites", e);
        }
    }

    /**
     * Executes the query and returns the ResultSet.
     * Simulates database interaction.
     *
     * @param stmt the prepared statement
     * @return the ResultSet
     * @throws SQLException if a database error occurs
     */
    private ResultSet executeQuery(PreparedStatement stmt) throws SQLException {
        // In a real scenario, this would execute the query.
        // For simulation, we assume the statement is executed here.
        return stmt.executeQuery();
    }
}

/**
 * Custom exception for data access errors.
 */
class DataAccessException extends RuntimeException {
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}