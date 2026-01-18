package com.example.repository;

import com.example.entities.Site;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of SiteRepository using a Database.
 */
public class SiteRepositoryImpl implements SiteRepository {
    private Connection dataSource;

    public SiteRepositoryImpl(Connection dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Site> findAll() {
        List<Site> sites = new ArrayList<>();
        String sql = "SELECT * FROM sites";
        try (PreparedStatement stmt = dataSource.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Site site = new Site(
                    rs.getInt("id"),
                    rs.getString("name")
                );
                sites.add(site);
            }
        } catch (Exception e) {
            System.err.println("Error finding all sites: " + e.getMessage());
        }
        return sites;
    }

    @Override
    public Optional<Site> findById(int siteId) {
        String sql = "SELECT * FROM sites WHERE id = ?";
        try (PreparedStatement stmt = dataSource.prepareStatement(sql)) {
            stmt.setInt(1, siteId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Site site = new Site(
                        rs.getInt("id"),
                        rs.getString("name")
                    );
                    return Optional.of(site);
                }
            }
        } catch (Exception e) {
            System.err.println("Error finding site by id: " + e.getMessage());
        }
        return Optional.empty();
    }
}