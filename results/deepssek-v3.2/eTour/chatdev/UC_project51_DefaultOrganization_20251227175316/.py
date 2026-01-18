/**
 * Represents a connection to the ETOUR server.
 * This class simulates server connectivity.
 */
public class ServerConnection {
    private String serverName;
    private boolean connected;
    /**
     * Constructor for ServerConnection.
     * @param serverName The name of the server.
     */
    public ServerConnection(String serverName) {
        this.serverName = serverName;
        this.connected = true; // Initially connected
    }
    /**
     * Checks if the connection to the server is active.
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        // Simulate a random interruption for demonstration
        // In a real application, this would check actual network connectivity
        if (Math.random() < 0.2) { // 20% chance of interruption
            connected = false;
        }
        return connected;
    }
    /**
     * Sets the connection status.
     * @param connected The new connection status.
     */
    public void setConnected(boolean connected) {
        this.connected = connected;
    }
    /**
     * Gets the server name.
     * @return The server name.
     */
    public String getServerName() {
        return serverName;
    }
    /**
     * Sets the server name.
     * @param serverName The new server name.
     */
    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}