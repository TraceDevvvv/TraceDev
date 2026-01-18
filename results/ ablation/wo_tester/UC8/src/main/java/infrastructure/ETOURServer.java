package infrastructure;

/**
 * Represents the ETOUR server (Exit Conditions).
 */
public class ETOURServer {
    private boolean isConnected;

    public ETOURServer() {
        this.isConnected = true; // Assume connected by default
    }

    /**
     * Connects to the server.
     * @return true if connected successfully, false otherwise.
     */
    public boolean connect() {
        isConnected = true;
        return isConnected;
    }

    /**
     * Disconnects from the server.
     */
    public void disconnect() {
        isConnected = false;
    }

    /**
     * Checks if the server is available.
     * @return true if available, false otherwise.
     */
    public boolean isAvailable() {
        return isConnected;
    }
}