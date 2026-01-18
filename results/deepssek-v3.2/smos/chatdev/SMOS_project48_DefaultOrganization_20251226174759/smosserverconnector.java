/**
 * Simulates connection to an SMOS server.
 * Handles connecting and disconnecting from the server.
 */
public class SMOSServerConnector {
    private boolean connected;
    /**
     * Constructs an SMOSServerConnector with initial disconnected state.
     */
    public SMOSServerConnector() {
        this.connected = false;
    }
    /**
     * Simulates connecting to the SMOS server.
     * @return true if connection is successful, false otherwise.
     */
    public boolean connect() {
        if (!connected) {
            connected = true;
            System.out.println("Connected to SMOS server.");
            return true;
        }
        System.out.println("Already connected to SMOS server.");
        return false;
    }
    /**
     * Simulates disconnecting from the SMOS server.
     * @return true if disconnection is successful, false otherwise.
     */
    public boolean disconnect() {
        if (connected) {
            connected = false;
            System.out.println("Disconnected from SMOS server.");
            return true;
        }
        System.out.println("Already disconnected from SMOS server.");
        return false;
    }
    /**
     * Checks the current connection status.
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return connected;
    }
}