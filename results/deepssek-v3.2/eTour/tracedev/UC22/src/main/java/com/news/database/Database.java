package com.news.database;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Simple database helper class to execute SQL updates.
 * Used by NewsRepositoryImpl as per class diagram.
 */
public class Database {
    private DataSource dataSource;

    public Database(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public int executeUpdate(String sql, Object... params) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < params.length; i++) {
                stmt.setObject(i + 1, params[i]);
            }
            return stmt.executeUpdate();
        }
    }

    // Additional method for queries if needed (not used in given diagrams)
    // public ResultSet executeQuery(String sql, Object... params) throws SQLException { ... }
}