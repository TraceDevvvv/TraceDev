package infrastructure;

import java.util.List;
import java.util.Map;

/**
 * Represents a connection to the ETOUR server/database.
 * Simulates database operations.
 */
public class ETOURServerConnection {
    private String connectionString;
    private boolean isConnected;

    public ETOURServerConnection(String connectionString) {
        this.connectionString = connectionString;
        this.isConnected = true; // assume connected initially
    }

    /**
     * Fetches data from the database using a query and parameters.
     * Returns a list of rows (each row as a map).
     */
    public List<Map<String, Object>> fetchData(String query, Map<String, Object> parameters) {
        if (!isConnected) {
            throw new RuntimeException("Not connected to ETOUR server");
        }
        // For simulation, return an empty list.
        // In a real implementation, this would execute the query via JDBC.
        return List.of();
    }

    /**
     * Executes an update command (INSERT, UPDATE, DELETE).
     * Returns true if successful.
     */
    public boolean executeUpdate(String command, Map<String, Object> parameters) {
        if (!isConnected) {
            throw new RuntimeException("Not connected to ETOUR server");
        }
        // Simulate successful update
        return true;
    }

    public boolean isConnected() {
        return isConnected;
    }

    /**
     * Checks the connection status.
     * Could attempt a ping/heartbeat.
     */
    public boolean checkConnection() {
        // Simulate always connected for demo
        return isConnected;
    }

    /**
     * Simulate a connection loss for testing alternative flows.
     */
    public void simulateConnectionLoss() {
        isConnected = false;
    }
}