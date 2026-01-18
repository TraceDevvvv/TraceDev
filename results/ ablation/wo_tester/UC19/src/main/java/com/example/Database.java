package com.example;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Database participant from sequence diagram.
 * Handles direct SQL operations as per sequence messages.
 */
public class Database {
    private DataSource dataSource;

    public Database(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Executes SELECT query for banners by pointId.
     * Sequence diagram message: SELECT * FROM Banner WHERE pointId = ?
     */
    public ResultSet selectBannersByPointId(String pointId) throws SQLException {
        String sql = "SELECT * FROM Banner WHERE pointId = ?";
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, pointId);
        return ps.executeQuery();
    }

    /**
     * Executes SELECT query for banner by bannerId.
     * Sequence diagram message: SELECT * FROM Banner WHERE bannerId = ?
     */
    public ResultSet selectBannerById(String bannerId) throws SQLException {
        String sql = "SELECT * FROM Banner WHERE bannerId = ?";
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, bannerId);
        return ps.executeQuery();
    }

    /**
     * Executes DELETE query for banner by bannerId.
     * Sequence diagram message: DELETE FROM Banner WHERE bannerId = ?
     */
    public int deleteBannerById(String bannerId) throws SQLException {
        String sql = "DELETE FROM Banner WHERE bannerId = ?";
        Connection conn = dataSource.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, bannerId);
        return ps.executeUpdate();
    }
}