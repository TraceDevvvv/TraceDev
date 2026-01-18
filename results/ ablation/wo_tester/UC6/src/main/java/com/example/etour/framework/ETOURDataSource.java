package com.example.etour.framework;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Data source that provides database connectivity.
 * In a real application, this would be configured with connection pooling, etc.
 */
public class ETOURDataSource {
    private Connection connection;

    public ETOURDataSource(String url, String user, String password) throws SQLException {
        this.connection = DriverManager.getConnection(url, user, password);
    }

    /**
     * Executes a query with parameters.
     * @param query the SQL query with placeholders.
     * @param params the parameters to set.
     * @return the ResultSet.
     * @throws SQLException if a database error occurs.
     */
    public ResultSet executeQuery(String query, Object... params) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(query);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        return stmt.executeQuery();
    }

    /**
     * Executes an update command with parameters.
     * @param command the SQL update command with placeholders.
     * @param params the parameters to set.
     * @return the number of rows affected.
     * @throws SQLException if a database error occurs.
     */
    public int executeUpdate(String command, Object... params) throws SQLException {
        PreparedStatement stmt = connection.prepareStatement(command);
        for (int i = 0; i < params.length; i++) {
            stmt.setObject(i + 1, params[i]);
        }
        return stmt.executeUpdate();
    }

    /**
     * Handles a connection error (e.g., logging, reconnection attempt).
     */
    public void handleConnectionError() {
        // In a real application, this might attempt to reconnect or log the error.
        System.err.println("Connection error handled. Consider reconnecting.");
    }

    /**
     * Closes the connection.
     */
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }
}