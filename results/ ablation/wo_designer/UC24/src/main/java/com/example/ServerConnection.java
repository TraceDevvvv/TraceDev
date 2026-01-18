public class ServerConnection {
    private boolean connected;
    
    public ServerConnection() {
        // Simulate connection status (90% chance of being connected)
        this.connected = Math.random() > 0.1;
    }
    
    public boolean isConnected() {
        return connected;
    }
    
    public void setConnected(boolean connected) {
        this.connected = connected;
    }
}