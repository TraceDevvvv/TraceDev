/**
 * Simulated database connection class
 * Handles connection to the server (simulation for this example)
 */
public class DatabaseConnection {
    private boolean connected;
    private int interruptionCounter;
    public DatabaseConnection() {
        // Simulate connection status (in real app, would connect to actual DB)
        connected = true;
        interruptionCounter = 0;
    }
    /**
     * Check if connection to server is available
     * @return true if connected
     */
    public boolean isConnected() {
        // Simulate occasional connection interruptions with more deterministic behavior
        // Every 10th call returns false to simulate interruption
        interruptionCounter++;
        if (interruptionCounter % 10 == 0) {
            connected = false;
        } else {
            connected = true;
        }
        return connected;
    }
    /**
     * Connect to the database server
     */
    public void connect() {
        // Simulate connection attempt
        System.out.println("Connecting to server ETOUR...");
        connected = true;
    }
    /**
     * Disconnect from the database server
     */
    public void disconnect() {
        System.out.println("Disconnecting from server ETOUR...");
        connected = false;
    }
}