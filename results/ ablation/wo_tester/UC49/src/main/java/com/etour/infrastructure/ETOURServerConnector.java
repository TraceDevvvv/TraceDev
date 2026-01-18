package com.etour.infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connector to the ETOUR server. Satisfies REQ-Connection.
 * Simulates connection status.
 */
public class ETOURServerConnector {
    private boolean connected = true; // Simulated connection status

    public Connection connect() throws SQLException {
        // Simulated connection - in real scenario would use actual DB URL, user, password
        // For simplicity, we return null and assume connection is established.
        return null;
    }

    public boolean isConnected() {
        return connected;
    }

    // Method to simulate connection interruption (for testing)
    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}