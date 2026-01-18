import java.time.LocalDateTime;

/**
 * SMOSServerConnection class for handling connection to the SMOS server.
 * Simulates server connection and interruption as per the use case postconditions.
 * 
 * This class manages the connection to the SMOS (Student Management Operations System) server
 * and handles the connection interruption specified in the postconditions.
 */
public class SMOSServerConnection {
    private ConnectionStatus status;
    private String serverUrl;
    private LocalDateTime connectionTime;
    private LocalDateTime disconnectTime;
    private int connectionAttempts;
    private boolean autoReconnect;
    
    /**
     * Enumeration defining possible connection statuses.
     */
    public enum ConnectionStatus {
        DISCONNECTED("Disconnected"),
        CONNECTING("Connecting"),
        CONNECTED("Connected"),
        INTERRUPTED("Interrupted"),
        ERROR("Error");
        
        private final String displayName;
        
        ConnectionStatus(String displayName) {
            this.displayName = displayName;
        }
        
        @Override
        public String toString() {
            return displayName;
        }
    }
    
    /**
     * Constructor with default server URL.
     */
    public SMOSServerConnection() {
        this("smos://server.schoolsystem.edu:8080");
    }
    
    /**
     * Constructor with custom server URL.
     * 
     * @param serverUrl The URL of the SMOS server
     * @throws IllegalArgumentException if serverUrl is null or empty
     */
    public SMOSServerConnection(String serverUrl) {
        if (serverUrl == null || serverUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Server URL cannot be null or empty");
        }
        
        this.serverUrl = serverUrl.trim();
        this.status = ConnectionStatus.DISCONNECTED;
        this.connectionAttempts = 0;
        this.autoReconnect = true;
        
        System.out.println("SMOS Server Connection initialized for: " + this.serverUrl);
    }
    
    /**
     * Attempts to connect to the SMOS server.
     * 
     * @return true if connection successful, false otherwise
     */
    public boolean connect() {
        if (status == ConnectionStatus.CONNECTED) {
            System.out.println("Already connected to SMOS server.");
            return true;
        }
        
        System.out.println("Attempting to connect to SMOS server: " + serverUrl);
        status = ConnectionStatus.CONNECTING;
        connectionAttempts++;
        
        try {
            // Simulate connection process with delay
            Thread.sleep(500); // Simulate network latency
            
            // In a real system, this would contain actual connection logic
            // For simulation, we'll randomly fail 10% of connection attempts
            if (Math.random() < 0.1) {
                status = ConnectionStatus.ERROR;
                System.out.println("Failed to connect to SMOS server after " + connectionAttempts + " attempt(s).");
                return false;
            }
            
            // Successful connection
            status = ConnectionStatus.CONNECTED;
            connectionTime = LocalDateTime.now();
            System.out.println("Successfully connected to SMOS server at " + connectionTime);
            
            return true;
            
        } catch (InterruptedException e) {
            status = ConnectionStatus.INTERRUPTED;
            System.out.println("Connection to SMOS server was interrupted.");
            Thread.currentThread().interrupt(); // Restore interrupted status
            return false;
        } catch (Exception e) {
            status = ConnectionStatus.ERROR;
            System.out.println("Error connecting to SMOS server: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Disconnects from the SMOS server gracefully.
     */
    public void disconnect() {
        if (status == ConnectionStatus.DISCONNECTED) {
            System.out.println("Already disconnected from SMOS server.");
            return;
        }
        
        System.out.println("Disconnecting from SMOS server...");
        
        try {
            // Simulate graceful disconnection
            Thread.sleep(200);
            
            status = ConnectionStatus.DISCONNECTED;
            disconnectTime = LocalDateTime.now();
            
            System.out.println("Disconnected from SMOS server at " + disconnectTime);
            
        } catch (InterruptedException e) {
            status = ConnectionStatus.INTERRUPTED;
            System.out.println("Disconnection was interrupted.");
            Thread.currentThread().interrupt();
        }
    }
    
    /**
     * Interrupts the connection to SMOS server as per postconditions.
     * This simulates the connection interruption specified in the use case.
     */
    public void interrupt() {
        System.out.println("Interrupting connection to SMOS server (as per postconditions)...");
        
        if (status == ConnectionStatus.CONNECTED || status == ConnectionStatus.CONNECTING) {
            status = ConnectionStatus.INTERRUPTED;
            disconnectTime = LocalDateTime.now();
            
            System.out.println("Connection to SMOS server interrupted at " + disconnectTime);
            
            // Simulate interruption effects
            if (autoReconnect) {
                System.out.println("Auto-reconnect initiated...");
                attemptReconnect();
            }
        } else {
            System.out.println("No active connection to interrupt. Current status: " + status);
        }
    }
    
    /**
     * Attempts to reconnect to the SMOS server after interruption.
     * 
     * @return true if reconnection successful, false otherwise
     */
    private boolean attemptReconnect() {
        System.out.println("Attempting to reconnect to SMOS server...");
        
        // Simulate reconnection delay
        try {
            Thread.sleep(1000);
            
            // Random chance of successful reconnection
            if (Math.random() < 0.7) {
                status = ConnectionStatus.CONNECTED;
                connectionTime = LocalDateTime.now();
                System.out.println("Reconnected to SMOS server at " + connectionTime);
                return true;
            } else {
                status = ConnectionStatus.ERROR;
                System.out.println("Failed to reconnect to SMOS server.");
                return false;
            }
            
        } catch (InterruptedException e) {
            status = ConnectionStatus.INTERRUPTED;
            System.out.println("Reconnection attempt was interrupted.");
            Thread.currentThread().interrupt();
            return false;
        }
    }
    
    /**
     * Checks if currently connected to the SMOS server.
     * 
     * @return true if connected, false otherwise
     */
    public boolean isConnected() {
        return status == ConnectionStatus.CONNECTED;
    }
    
    /**
     * Gets the current connection status.
     * 
     * @return The current connection status
     */
    public ConnectionStatus getStatus() {
        return status;
    }
    
    /**
     * Gets the server URL.
     * 
     * @return The SMOS server URL
     */
    public String getServerUrl() {
        return serverUrl;
    }
    
    /**
     * Gets the time when the connection was established.
     * 
     * @return The connection time, or null if not connected
     */
    public LocalDateTime getConnectionTime() {
        return connectionTime;
    }
    
    /**
     * Gets the time when the connection was disconnected or interrupted.
     * 
     * @return The disconnect time, or null if still connected
     */
    public LocalDateTime getDisconnectTime() {
        return disconnectTime;
    }
    
    /**
     * Gets the number of connection attempts made.
     * 
     * @return The number of connection attempts
     */
    public int getConnectionAttempts() {
        return connectionAttempts;
    }
    
    /**
     * Checks if auto-reconnect is enabled.
     * 
     * @return true if auto-reconnect is enabled, false otherwise
     */
    public boolean isAutoReconnectEnabled() {
        return autoReconnect;
    }
    
    /**
     * Sets auto-reconnect option.
     * 
     * @param autoReconnect true to enable auto-reconnect, false to disable
     */
    public void setAutoReconnect(boolean autoReconnect) {
        this.autoReconnect = autoReconnect;
        System.out.println("Auto-reconnect " + (autoReconnect ? "enabled" : "disabled"));
    }
    
    /**
     * Sends data to the SMOS server (simulated).
     * 
     * @param data The data to send
     * @return true if data sent successfully, false otherwise
     */
    public boolean sendData(String data) {
        if (!isConnected()) {
            System.out.println("Cannot send data: Not connected to SMOS server");
            return false;
        }
        
        if (data == null || data.trim().isEmpty()) {
            throw new IllegalArgumentException("Data cannot be null or empty");
        }
        
        System.out.println("Sending data to SMOS server: " + data.substring(0, Math.min(data.length(), 50)) + "...");
        
        // Simulate data transmission
        try {
            Thread.sleep(100);
            
            // Random chance of transmission failure (10%)
            if (Math.random() < 0.1) {
                System.out.println("Data transmission failed.");
                return false;
            }
            
            System.out.println("Data sent successfully to SMOS server.");
            return true;
            
        } catch (InterruptedException e) {
            status = ConnectionStatus.INTERRUPTED;
            System.out.println("Data transmission interrupted.");
            Thread.currentThread().interrupt();
            return false;
        }
    }
    
    /**
     * Receives data from the SMOS server (simulated).
     * 
     * @return The received data as a string
     */
    public String receiveData() {
        if (!isConnected()) {
            System.out.println("Cannot receive data: Not connected to SMOS server");
            return "ERROR: Not connected";
        }
        
        System.out.println("Receiving data from SMOS server...");
        
        // Simulate data reception
        try {
            Thread.sleep(150);
            
            // Simulate receiving sample data
            String sampleData = "SMOS Server Response: Student data synchronized at " + LocalDateTime.now();
            System.out.println("Data received from SMOS server.");
            
            return sampleData;
            
        } catch (InterruptedException e) {
            status = ConnectionStatus.INTERRUPTED;
            System.out.println("Data reception interrupted.");
            Thread.currentThread().interrupt();
            return "ERROR: Reception interrupted";
        }
    }
    
    /**
     * Gets the connection duration if currently connected.
     * 
     * @return Connection duration in seconds, or -1 if not connected
     */
    public long getConnectionDuration() {
        if (connectionTime == null || !isConnected()) {
            return -1;
        }
        
        return java.time.Duration.between(connectionTime, LocalDateTime.now()).getSeconds();
    }
    
    /**
     * Gets a summary of the SMOS server connection.
     * 
     * @return Formatted summary string
     */
    public String getConnectionSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("=== SMOS Server Connection Summary ===\n");
        summary.append("Server URL: ").append(serverUrl).append("\n");
        summary.append("Status: ").append(status.toString()).append("\n");
        summary.append("Connection Attempts: ").append(connectionAttempts).append("\n");
        summary.append("Auto-reconnect: ").append(autoReconnect ? "Enabled" : "Disabled").append("\n");
        
        if (connectionTime != null) {
            summary.append("Connection Time: ").append(connectionTime).append("\n");
        }
        
        if (disconnectTime != null) {
            summary.append("Disconnect Time: ").append(disconnectTime).append("\n");
        }
        
        if (isConnected()) {
            long duration = getConnectionDuration();
            summary.append("Connection Duration: ").append(duration).append(" seconds\n");
        }
        
        return summary.toString();
    }
    
    /**
     * Resets the connection statistics.
     */
    public void resetStatistics() {
        connectionAttempts = 0;
        connectionTime = null;
        disconnectTime = null;
        System.out.println("SMOS server connection statistics reset.");
    }
    
    /**
     * Returns a string representation of the SMOS server connection.
     * 
     * @return String containing connection information
     */
    @Override
    public String toString() {
        return String.format("SMOSServerConnection{url=%s, status=%s, attempts=%d}", 
            serverUrl, status, connectionAttempts);
    }
}