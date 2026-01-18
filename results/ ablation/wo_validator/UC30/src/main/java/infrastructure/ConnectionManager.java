package infrastructure;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages database connections.
 */
public class ConnectionManager {
    private String serverEndpoint;
    private boolean isConnected;

    public ConnectionManager(String serverEndpoint) {
        this.serverEndpoint = serverEndpoint;
        this.isConnected = false;
    }

    public Connection getConnection() throws SQLException {
        // Simplified: using JDBC directly; real implementation would use connection pool.
        Connection conn = DriverManager.getConnection(serverEndpoint);
        isConnected = true;
        return conn;
    }

    public void closeConnection() {
        // In a real scenario, would close any open connections.
        isConnected = false;
    }

    public boolean isServerAvailable() {
        // Simplified availability check.
        try (Connection conn = getConnection()) {
            return conn.isValid(2);
        } catch (SQLException e) {
            return false;
        }
    }
}