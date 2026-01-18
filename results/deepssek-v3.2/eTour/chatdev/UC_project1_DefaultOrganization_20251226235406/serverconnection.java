'''
ServerConnection simulates connection to the ETOUR server.
Includes methods to check connection status and simulate interruptions.
'''
import java.util.Random;
public class ServerConnection {
    private boolean connected;
    private Random random;
    public ServerConnection() {
        random = new Random();
        // Start with a connected state
        connected = true;
    }
    /**
     * Checks if connected to the ETOUR server.
     * Simulates occasional connection interruptions.
     */
    public boolean isConnected() {
        // Simulate 10% chance of connection interruption for demonstration
        if (random.nextInt(100) < 10) {
            connected = false;
            System.out.println("WARNING: Connection to ETOUR server interrupted.");
        } else {
            connected = true;
        }
        return connected;
    }
    /**
     * Manually set connection status for testing.
     */
    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}