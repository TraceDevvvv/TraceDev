'''
Simulates a connection manager for an SMOS server.
This class is used to abstract the server connection logic,
specifically for simulating connection interruption as per the use case.
'''
public class SMOSServerConnectionManager {
    private boolean isConnected; // Tracks the simulated connection status
    /**
     * Constructs a new SMOSServerConnectionManager.
     * Initially, the connection is assumed to be established.
     */
    public SMOSServerConnectionManager() {
        this.isConnected = true; // Assume connected initially.
        System.out.println("SMOS server connection established.");
    }
    /**
     * Simulates the interruption of the connection to the SMOS server.
     * This method fulfills the postcondition requirement: "Connection to the SMOS server interrupted".
     */
    public void interruptConnection() {
        if (isConnected) {
            isConnected = false;
            System.out.println(">>> SMOS server connection interrupted. <<<");
            // In a real application, this would involve closing network sockets,
            // releasing resources, and potentially notifying other components.
        } else {
            System.out.println("SMOS server connection was already interrupted.");
        }
    }
    /**
     * Checks if the SMOS server is currently connected.
     *
     * @return true if connected, false otherwise.
     */
    public boolean isConnected() {
        return isConnected;
    }
    /**
     * Simulates establishing a connection to the SMOS server.
     * (Not explicitly required by the use case, but good for completeness).
     */
    public void establishConnection() {
        if (!isConnected) {
            isConnected = true;
            System.out.println("SMOS server connection re-established.");
        } else {
            System.out.println("SMOS server connection is already established.");
        }
    }
}