import java.util.Random;

/**
 * SMOSConnection class that simulates connection to SMOS server.
 * This class handles the connection lifecycle as mentioned in the postconditions:
 * "the administrator interrupts the connection to the SMOS server interrupted"
 */
public class SMOSConnection {
    private boolean connected;
    private String connectionId;
    private final Random random;
    private static final String[] SERVER_IPS = {
        "192.168.1.100",
        "192.168.1.101", 
        "192.168.1.102",
        "10.0.0.50",
        "10.0.0.51"
    };
    private static final int DEFAULT_PORT = 8080;
    
    /**
     * Constructor initializes the connection state
     */
    public SMOSConnection() {
        this.connected = false;
        this.connectionId = null;
        this.random = new Random();
    }
    
    /**
     * Attempts to establish a connection to the SMOS server
     * Simulates network connection with random success/failure
     * @return true if connection successful, false otherwise
     */
    public boolean connect() {
        if (connected) {
            System.out.println("Already connected to SMOS server.");
            return true;
        }
        
        System.out.println("Attempting to connect to SMOS server...");
        
        // Simulate connection attempt with 80% success rate
        boolean success = random.nextDouble() < 0.8;
        
        if (success) {
            connected = true;
            connectionId = generateConnectionId();
            
            // Randomly select a server IP
            String serverIp = SERVER_IPS[random.nextInt(SERVER_IPS.length)];
            
            System.out.println("Connected to SMOS server at " + serverIp + ":" + DEFAULT_PORT);
            System.out.println("Connection ID: " + connectionId);
            System.out.println("SMOS Protocol v2.1 - Session established");
            
            return true;
        } else {
            System.out.println("Connection failed: SMOS server unavailable");
            System.out.println("Possible reasons:");
            System.out.println("  - Server is down for maintenance");
            System.out.println("  - Network connectivity issues");
            System.out.println("  - Firewall restrictions");
            
            return false;
        }
    }
    
    /**
     * Disconnects from the SMOS server
     * This corresponds to the postcondition: "interrupts the connection to the SMOS server"
     * @return true if disconnection successful, false if not connected
     */
    public boolean disconnect() {
        if (!connected) {
            System.out.println("Not connected to SMOS server.");
            return false;
        }
        
        System.out.println("Interrupting connection to SMOS server...");
        
        // Simulate disconnection delay
        try {
            Thread.sleep(500); // Simulate network delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        System.out.println("Disconnected from SMOS server.");
        System.out.println("Connection ID " + connectionId + " terminated.");
        
        connected = false;
        connectionId = null;
        
        return true;
    }
    
    /**
     * Forcefully interrupts the connection (simulating unexpected disconnection)
     * This simulates the postcondition scenario
     */
    public void interruptConnection() {
        if (connected) {
            System.out.println("FORCE INTERRUPT: SMOS server connection interrupted!");
            System.out.println("Emergency disconnect initiated...");
            
            connected = false;
            String interruptedId = connectionId;
            connectionId = null;
            
            System.out.println("Connection " + interruptedId + " was forcibly interrupted.");
            System.out.println("Postcondition satisfied: Connection to SMOS server interrupted.");
        } else {
            System.out.println("No active connection to interrupt.");
        }
    }
    
    /**
     * Checks if currently connected to SMOS server
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        return connected;
    }
    
    /**
     * Gets the current connection ID
     * @return connection ID if connected, null otherwise
     */
    public String getConnectionId() {
        return connectionId;
    }
    
    /**
     * Sends a heartbeat/ping to the SMOS server to check connectivity
     * @return true if server responds, false otherwise
     */
    public boolean sendHeartbeat() {
        if (!connected) {
            System.out.println("Cannot send heartbeat: Not connected to SMOS server.");
            return false;
        }
        
        // Simulate heartbeat with 90% success rate
        boolean success = random.nextDouble() < 0.9;
        
        if (success) {
            System.out.println("SMOS server heartbeat: OK (Connection stable)");
            return true;
        } else {
            System.out.println("SMOS server heartbeat: FAILED (Connection unstable)");
            
            // If heartbeat fails, connection might be lost
            if (random.nextDouble() < 0.3) { // 30% chance connection drops after failed heartbeat
                System.out.println("Connection lost due to heartbeat failure.");
                connected = false;
                connectionId = null;
            }
            
            return false;
        }
    }
    
    /**
     * Simulates sending data to SMOS server
     * @param data the data to send
     * @return true if data sent successfully, false otherwise
     */
    public boolean sendData(String data) {
        if (!connected) {
            System.out.println("Cannot send data: Not connected to SMOS server.");
            return false;
        }
        
        if (data == null || data.trim().isEmpty()) {
            System.out.println("Cannot send empty data to SMOS server.");
            return false;
        }
        
        System.out.println("Sending data to SMOS server...");
        
        // Simulate data transmission with random success
        boolean success = random.nextDouble() < 0.85;
        
        if (success) {
            System.out.println("Data sent successfully to SMOS server.");
            System.out.println("Data length: " + data.length() + " bytes");
            System.out.println("Connection ID: " + connectionId);
            return true;
        } else {
            System.out.println("Failed to send data to SMOS server.");
            System.out.println("Possible network issue or server timeout.");
            return false;
        }
    }
    
    /**
     * Simulates receiving data from SMOS server
     * @return simulated server response
     */
    public String receiveData() {
        if (!connected) {
            return "ERROR: Not connected to SMOS server.";
        }
        
        // Simulate receiving data with random content
        String[] responses = {
            "SMOS Server: User data synchronized successfully.",
            "SMOS Server: Archive updated. Transaction ID: TX" + (1000 + random.nextInt(9000)),
            "SMOS Server: Connection stable. Bandwidth: " + (10 + random.nextInt(90)) + " Mbps",
            "SMOS Server: System time: " + System.currentTimeMillis(),
            "SMOS Server: Ready for next operation."
        };
        
        String response = responses[random.nextInt(responses.length)];
        System.out.println("Received from SMOS: " + response);
        
        return response;
    }
    
    /**
     * Gets server status information
     * @return formatted server status string
     */
    public String getServerStatus() {
        if (!connected) {
            return "SMOS Server Status: DISCONNECTED";
        }
        
        StringBuilder status = new StringBuilder();
        status.append("SMOS Server Status: CONNECTED\n");
        status.append("Connection ID: ").append(connectionId).append("\n");
        status.append("Server IP: ").append(SERVER_IPS[connectionId.hashCode() % SERVER_IPS.length]).append("\n");
        status.append("Port: ").append(DEFAULT_PORT).append("\n");
        status.append("Protocol: SMOS v2.1\n");
        status.append("Connection Time: ").append(System.currentTimeMillis()).append(" ms");
        
        return status.toString();
    }
    
    /**
     * Simulates a server error scenario
     * This can be used to test error handling
     */
    public void simulateServerError() {
        System.out.println("Simulating SMOS server error...");
        
        String[] errors = {
            "SMOS Error 500: Internal Server Error",
            "SMOS Error 503: Service Unavailable",
            "SMOS Error 408: Request Timeout",
            "SMOS Error 401: Authentication Failed",
            "SMOS Error 423: Server Maintenance"
        };
        
        String error = errors[random.nextInt(errors.length)];
        System.out.println("SMOS Server Error: " + error);
        
        // 50% chance connection drops after error
        if (random.nextBoolean() && connected) {
            System.out.println("Connection lost due to server error.");
            connected = false;
            connectionId = null;
        }
    }
    
    /**
     * Generates a unique connection ID
     * @return generated connection ID
     */
    private String generateConnectionId() {
        long timestamp = System.currentTimeMillis();
        int randomNum = random.nextInt(10000);
        return "SMOS-CONN-" + timestamp + "-" + randomNum;
    }
    
    /**
     * Resets the connection state (for testing purposes)
     */
    public void reset() {
        connected = false;
        connectionId = null;
        System.out.println("SMOS connection reset.");
    }
    
    /**
     * Displays connection statistics (simulated)
     */
    public void displayStatistics() {
        System.out.println("\n======= SMOS CONNECTION STATISTICS =======");
        System.out.println("Connection State: " + (connected ? "CONNECTED" : "DISCONNECTED"));
        
        if (connected) {
            System.out.println("Connection ID: " + connectionId);
            System.out.println("Uptime: " + (random.nextInt(60) + 1) + " minutes");
            System.out.println("Data Sent: " + (random.nextInt(1000) + 100) + " KB");
            System.out.println("Data Received: " + (random.nextInt(800) + 80) + " KB");
            System.out.println("Success Rate: " + (85 + random.nextInt(15)) + "%");
        }
        
        System.out.println("Server Pool: " + SERVER_IPS.length + " available servers");
        System.out.println("Default Port: " + DEFAULT_PORT);
        System.out.println("===========================================");
    }
}