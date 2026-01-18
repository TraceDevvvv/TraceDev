package com.example.database;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DataSource class for database connections.
 */
public class DataSource {
    private ETOURServer server;
    
    public DataSource(ETOURServer server) {
        this.server = server;
    }
    
    /**
     * Gets a database connection.
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public Connection getConnection() throws SQLException {
        return server.connect();
    }
    
    /**
     * Closes a database connection.
     * @param conn the connection to close
     */
    public void closeConnection(Connection conn) {
        server.disconnect(conn);
    }
    
    // Getters and setters
    public ETOURServer getServer() {
        return server;
    }
    
    public void setServer(ETOURServer server) {
        this.server = server;
    }
}