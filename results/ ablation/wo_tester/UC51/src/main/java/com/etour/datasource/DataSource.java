package com.etour.datasource;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * <<trace(R-NFR-1)>> Connection management with timeout constraint
 * Manages database connections.
 */
public class DataSource {
    private Connection connection;
    private String serverUrl; // Added to satisfy requirement Exit Conditions

    public DataSource(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public Connection getConnection() {
        return connection;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Connects to the database.
     * Quality Requirement: {time ≤ 5s}
     * @return true if connection successful, false otherwise
     */
    public boolean connect() {
        long start = System.currentTimeMillis();
        try {
            // Simulate connection
            Thread.sleep(100); // simulated delay
            connection = DriverManager.getConnection(serverUrl);
            long elapsed = System.currentTimeMillis() - start;
            if (elapsed > 5000) {
                System.out.println("Connection exceeded 5 seconds.");
                return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}