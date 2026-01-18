import java.time.LocalDateTime;

/**
 * SMOSServer class simulates connection to the SMOS (School Management Operating System) server.
 * This class handles server connection, status tracking, and interruption as required
 * by the postconditions of the delete teaching use case.
 */
public class SMOSServer {
    private boolean isConnected;
    private String serverAddress;
    private LocalDateTime connectionTime;
    private int connectionAttempts;
    private String sessionId;
    
    // Default server configuration
    private static final String DEFAULT_SERVER_ADDRESS = "smos.education.gov:8443";
    private static final int MAX_RECONNECTION_ATTEMPTS = 3;
    
    /**
     * Constructs a new SMOSServer instance with default server address.
     */
    public SMOSServer() {
        this.serverAddress = DEFAULT_SERVER_ADDRESS;
        this.isConnected = false;
        this.connectionAttempts = 0;
        this.sessionId = null;
        this.connectionTime = null;
    }
    
    /**
     * Constructs a new SMOSServer instance with custom server address.
     * 
     * @param serverAddress The address of the SMOS server
     * @throws IllegalArgumentException if serverAddress is null or empty
     */
    public SMOSServer(String serverAddress) {
        if (serverAddress == null || serverAddress.trim().isEmpty()) {
            throw new IllegalArgumentException("Server address cannot be null or empty.");
        }
        this.serverAddress = serverAddress.trim();
        this.isConnected = false;
        this.connectionAttempts = 0;
        this.sessionId = null;
        this.connectionTime = null;
    }
    
    /**
     * Attempts to connect to the SMOS server.
     * Simulates server connection with authentication and session creation.
     * 
     * @return true if connection is successful, false otherwise
     */
    public boolean connect() {
        if (isConnected) {
            System.out.println("Already connected to SMOS server at " + serverAddress);
            return true;
        }
        
        System.out.println("Attempting to connect to SMOS server at " + serverAddress + "...");
        
        // Simulate connection attempt
        connectionAttempts++;
        
        // In a real system, this would involve network calls, authentication, etc.
        // For simulation, assume connection is usually successful
        boolean success = simulateConnection();
        
        if (success) {
            isConnected = true;
            connectionTime = LocalDateTime.now();
            sessionId = generateSessionId();
            
            System.out.println("Successfully connected to SMOS server.");
            System.out.println("Session ID: " + sessionId);
            System.out.println("Connection time: " + connectionTime);
        } else {
            System.out.println("Failed to connect to SMOS server.");
            
            // Attempt reconnection if under maximum attempts
            if (connectionAttempts < MAX_RECONNECTION_ATTEMPTS) {
                System.out.println("Will attempt reconnection... (" + 
                                 connectionAttempts + "/" + MAX_RECONNECTION_ATTEMPTS + ")");
            }
        }
        
        return success;
    }
    
    /**
     * Simulates a server connection attempt.
     * In a real implementation, this would contain actual network and authentication logic.
     * 
     * @return true if simulated connection succeeds, false otherwise
     */
    private boolean simulateConnection() {
        // For simulation purposes, assume 90% success rate
        // In production, this would involve actual network connectivity checks
        // and authentication with the SMOS server
        double successProbability = 0.9;
        return Math.random() < successProbability;
    }
    
    /**
     * Generates a unique session ID for the server connection.
     * In a real system, this would be provided by the server.
     * 
     * @return A simulated session ID
     */
    private String generateSessionId() {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String random = String.valueOf((int)(Math.random() * 10000));
        return "SMOS_SESSION_" + timestamp + "_" + random;
    }
    
    /**
     * Disconnects from the SMOS server gracefully.
     * This is a normal disconnection initiated by the user/system.
     * 
     * @return true if disconnection is successful, false otherwise
     */
    public boolean disconnect() {
        if (!isConnected) {
            System.out.println("Not connected to SMOS server. Nothing to disconnect.");
            return false;
        }
        
        System.out.println("Disconnecting from SMOS server at " + serverAddress + "...");
        
        // Simulate sending logout/termination request to server
        boolean success = simulateDisconnection();
        
        if (success) {
            System.out.println("Successfully disconnected from SMOS server.");
            System.out.println("Session " + sessionId + " terminated.");
            
            // Log connection duration
            if (connectionTime != null) {
                LocalDateTime disconnectTime = LocalDateTime.now();
                long minutes = java.time.Duration.between(connectionTime, disconnectTime).toMinutes();
                System.out.println("Connection duration: " + minutes + " minutes");
            }
            
            // Reset connection state
            resetConnectionState();
        } else {
            System.out.println("Failed to disconnect gracefully. Forcing disconnection...");
            forceDisconnect();
        }
        
        return success;
    }
    
    /**
     * Interrupts the connection to the SMOS server.
     * This simulates the postcondition "Connection to the SMOS server interrupted"
     * after a teaching deletion. It's an abrupt termination rather than graceful disconnection.
     * 
     * @return true if interruption is successful, false otherwise
     */
    public boolean interruptConnection() {
        if (!isConnected) {
            System.out.println("Not connected to SMOS server. Cannot interrupt.");
            return false;
        }
        
        System.out.println("INTERRUPTING connection to SMOS server...");
        System.out.println("This simulates the postcondition after teaching deletion.");
        
        // Force immediate disconnection without graceful shutdown
        forceDisconnect();
        
        System.out.println("Connection to SMOS server has been INTERRUPTED.");
        System.out.println("Postcondition satisfied: Connection to the SMOS server interrupted");
        
        return true;
    }
    
    /**
     * Simulates a graceful disconnection from the server.
     * 
     * @return true if simulated disconnection succeeds, false otherwise
     */
    private boolean simulateDisconnection() {
        // For simulation purposes, assume 95% success rate for graceful disconnection
        // In production, this would involve sending termination requests to the server
        double successProbability = 0.95;
        return Math.random() < successProbability;
    }
    
    /**
     * Forces immediate disconnection without server coordination.
     * Used for interruption and when graceful disconnection fails.
     */
    private void forceDisconnect() {
        // Force immediate disconnection
        resetConnectionState();
        System.out.println("Forced disconnection complete.");
    }
    
    /**
     * Resets all connection-related state variables.
     */
    private void resetConnectionState() {
        isConnected = false;
        // Don't reset connectionAttempts to maintain attempt count
        sessionId = null;
        connectionTime = null;
    }
    
    /**
     * Checks if currently connected to the SMOS server.
     * 
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        return isConnected;
    }
    
    /**
     * Gets the current server address.
     * 
     * @return The SMOS server address
     */
    public String getServerAddress() {
        return serverAddress;
    }
    
    /**
     * Changes the server address.
     * Note: Will disconnect if currently connected.
     * 
     * @param newAddress The new server address
     * @return true if address was changed successfully, false otherwise
     */
    public boolean changeServerAddress(String newAddress) {
        if (newAddress == null || newAddress.trim().isEmpty()) {
            System.out.println("Error: New server address cannot be null or empty.");
            return false;
        }
        
        // Disconnect from current server if connected
        if (isConnected) {
            System.out.println("Changing server address while connected. Disconnecting first...");
            disconnect();
        }
        
        String oldAddress = this.serverAddress;
        this.serverAddress = newAddress.trim();
        System.out.println("Server address changed from " + oldAddress + " to " + this.serverAddress);
        
        return true;
    }
    
    /**
     * Sends a test ping to the SMOS server to check connectivity.
     * 
     * @return true if server responds to ping, false otherwise
     */
    public boolean pingServer() {
        if (!isConnected) {
            System.out.println("Cannot ping server: Not connected.");
            return false;
        }
        
        System.out.println("Pinging SMOS server at " + serverAddress + "...");
        
        // Simulate network latency and response
        boolean success = simulatePing();
        
        if (success) {
            System.out.println("Ping successful. Server is responsive.");
        } else {
            System.out.println("Ping failed. Server may be unreachable.");
        }
        
        return success;
    }
    
    /**
     * Simulates a ping to the server.
     * 
     * @return true if simulated ping succeeds, false otherwise
     */
    private boolean simulatePing() {
        // For simulation, assume 98% success rate when connected
        // In production, this would involve actual network ping or heartbeat
        double successProbability = 0.98;
        return Math.random() < successProbability;
    }
    
    /**
     * Gets the number of connection attempts made.
     * 
     * @return The total connection attempts count
     */
    public int getConnectionAttempts() {
        return connectionAttempts;
    }
    
    /**
     * Gets the current session ID if connected.
     * 
     * @return The session ID, or null if not connected
     */
    public String getSessionId() {
        return sessionId;
    }
    
    /**
     * Gets the connection timestamp if connected.
     * 
     * @return The connection time, or null if not connected
     */
    public LocalDateTime getConnectionTime() {
        return connectionTime;
    }
    
    /**
     * Returns a string representation of the server connection status.
     * 
     * @return String containing server connection information
     */
    @Override
    public String toString() {
        return String.format("SMOSServer{address='%s', connected=%s, sessionId=%s}",
                            serverAddress, isConnected, 
                            sessionId != null ? "'" + sessionId + "'" : "null");
    }
}