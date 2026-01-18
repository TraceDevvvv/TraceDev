/**
 * SMOSConnection simulates a connection to the SMOS server.
 * According to the postcondition, the connection is interrupted after deleting a teaching.
 */
public class SMOSConnection {
    private boolean connected;
    /**
     * Constructor initializes the connection as connected.
     */
    public SMOSConnection() {
        connected = true; // Simulate initial connection
    }
    /**
     * Interrupt the connection to the SMOS server.
     * This is called after a teaching is deleted.
     */
    public void interruptConnection() {
        connected = false;
        // In a real application, this would close network connections, release resources, etc.
        System.out.println("Connection to SMOS server interrupted.");
    }
    /**
     * Check if the connection is currently active.
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        return connected;
    }
}