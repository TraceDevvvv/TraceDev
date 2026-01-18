// Simulates a connection to the ETOUR server
public class ETourServerConnection {
    private boolean connected;

    public ETourServerConnection() {
        this.connected = true; // Initially connected
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    // Simulates server interruption
    public void interruptConnection() {
        this.connected = false;
        System.out.println("Connection to the server ETOUR is interrupted.");
    }
}