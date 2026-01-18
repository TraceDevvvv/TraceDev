package hec.adapters;

/**
 * Manages database connections and transactions.
 * Simulates connection status and rollback functionality.
 */
public class ConnectionManager {
    private boolean isConnected;

    /**
     * Constructor initializes connection as connected.
     */
    public ConnectionManager() {
        this.isConnected = true; // Assume initially connected.
    }

    /**
     * Checks the current connection status.
     *
     * @return true if connected, false otherwise
     */
    public boolean checkConnection() {
        // Simulate occasional disconnection (e.g., 10% chance).
        if (Math.random() < 0.1) {
            isConnected = false;
        }
        return isConnected;
    }

    /**
     * Attempts to reconnect.
     *
     * @return true if reconnection succeeded
     */
    public boolean reconnect() {
        isConnected = true; // Simulate successful reconnect.
        return true;
    }

    /**
     * Gets the current connection status.
     *
     * @return the connection status
     */
    public boolean getConnectionStatus() {
        return isConnected;
    }

    /**
     * Rolls back the current transaction.
     */
    public void rollbackTransaction() {
        // Simulate rollback operation as per sequence diagram message "rollback transaction"
        System.out.println("Transaction rolled back.");
    }

    /**
     * Returns connection status as per sequence diagram message "connectionStatus".
     *
     * @return the connection status
     */
    public boolean connectionStatus() {
        return getConnectionStatus();
    }
}