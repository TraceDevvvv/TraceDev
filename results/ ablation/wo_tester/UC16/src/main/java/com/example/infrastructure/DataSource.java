package com.example.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Simple DataSource implementation for demonstration.
 * In a real application, this would be a connection pool.
 */
public class DataSource {
    private String url;
    private String user;
    private String password;

    public DataSource(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public Connection getConnection() throws SQLException {
        // In a real system, this would return a connection from a pool.
        return DriverManager.getConnection(url, user, password);
    }

    public void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}