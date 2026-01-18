package com.example.db;

import java.sql.*;

/**
 * Database connection wrapper.
 */
public class DatabaseConnection {
    private String connectionString;

    public DatabaseConnection(String connectionString) {
        this.connectionString = connectionString;
    }

    public ResultSet executeQuery(String sql) throws SQLException {
        Connection conn = DriverManager.getConnection(connectionString);
        Statement stmt = conn.createStatement();
        return stmt.executeQuery(sql);
    }

    public int executeUpdate(String sql) throws SQLException {
        Connection conn = DriverManager.getConnection(connectionString);
        Statement stmt = conn.createStatement();
        return stmt.executeUpdate(sql);
    }
}