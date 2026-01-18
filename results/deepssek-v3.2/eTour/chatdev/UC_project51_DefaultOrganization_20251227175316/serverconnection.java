/**
 * Represents a connection to the ETOUR server.
 * Handles server connectivity status and interruptions.
 */
import java.util.Random;
public class ServerConnection {
    private String serverName;
    private boolean connected;
    private Random rand;
    /**
     * Constructor initializes server connection.
     * @param serverName The name of the server (e.g., "ETOUR").
     */
    public ServerConnection(String serverName) {
        this.serverName = serverName;
        this.connected = true;
        this.rand = new Random();
    }
    /**
     * Checks if the connection to the server is active.
     * @return true if connected, false if interrupted.
     */
    public boolean isConnected() {
        // Simulate random interruption (20% chance)
        if (rand.nextDouble() < 0.2) {
            connected = false;
        }
        return connected;
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
    /**
     * Manually set connection status for testing.
     * @param status The new connection status.
     */
    public void setConnected(boolean status) {
        this.connected = status;
    }
}