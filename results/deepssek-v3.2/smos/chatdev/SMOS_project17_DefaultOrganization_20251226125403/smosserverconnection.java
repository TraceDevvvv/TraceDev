/*
 Simulates connection to SMOS server with interruption capability
 Manages the server state and connection status
 */
public class SMOSServerConnection {
    private boolean connected;
    private String serverName;
    public SMOSServerConnection() {
        this.serverName = "SMOS_Server_01";
        this.connected = true; // Start connected
    }
    public boolean connect() {
        connected = true;
        System.out.println("Connected to SMOS server: " + serverName);
        return connected;
    }
    public void interruptConnection() {
        if (connected) {
            connected = false;
            System.out.println("Connection to SMOS server interrupted");
        }
    }
    public boolean isConnected() {
        return connected;
    }
    public String getServerStatus() {
        return connected ? "Connected to " + serverName : "Disconnected from " + serverName;
    }
}