import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Represents a connection to the SMOS (System Management and Operations Server).
 * Handles connection establishment, maintenance, and interruption as per use case requirements.
 * This class ensures proper resource management and state tracking.
 */
public class SMOSServerConnection {
    private String serverAddress;
    private int serverPort;
    private String connectionId;
    private ConnectionStatus status;
    private LocalDateTime connectionTime;
    private LocalDateTime lastActivity;
    private AtomicBoolean isInterrupted;
    private int timeoutSeconds;
    
    // Connection status enum
    public enum ConnectionStatus {
        DISCONNECTED,
        CONNECTING,
        CONNECTED,
        ACTIVE,
        INTERRUPTED,
        ERROR
    }
    
    // Formatter for timestamps
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = 
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    /**
     * Constructor with default server settings.
     */
    public SMOSServerConnection() {
        this("smos.example.com", 8080);
    }
    
    /**
     * Constructor with custom server settings.
     * 
     * @param serverAddress The SMOS server address
     * @param serverPort The SMOS server port
     */
    public SMOSServerConnection(String serverAddress, int serverPort) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.status = ConnectionStatus.DISCONNECTED;
        this.connectionId = null;
        this.connectionTime = null;
        this.lastActivity = null;
        this.isInterrupted = new AtomicBoolean(false);
        this.timeoutSeconds = 30; // Default timeout
    }
    
    /**
     * Establishes a connection to the SMOS server.
     * Simulates connection process with validation.
     * 
     * @return true if connection successful, false otherwise
     */
    public synchronized boolean connect() {
        if (isConnected()) {
            System.out.println("Already connected to SMOS server.");
            return true;
        }
        
        System.out.println("Connecting to SMOS server at " + serverAddress + ":" + serverPort + "...");
        
        try {
            // Simulate connection attempt
            status = ConnectionStatus.CONNECTING;
            Thread.sleep(500); // Simulate network latency
            
            // Generate connection ID
            connectionId = generateConnectionId();
            connectionTime = LocalDateTime.now();
            lastActivity = connectionTime;
            status = ConnectionStatus.CONNECTED;
            isInterrupted.set(false);
            
            System.out.println("✓ SMOS server connection established.");
            System.out.println("  Connection ID: " + connectionId);
            System.out.println("  Connection Time: " + connectionTime.format(TIMESTAMP_FORMATTER));
            
            // Simulate authentication handshake
            System.out.println("Performing authentication handshake...");
            Thread.sleep(300);
            status = ConnectionStatus.ACTIVE;
            System.out.println("✓ Authentication successful. Connection is now ACTIVE.");
            
            return true;
            
        } catch (InterruptedException e) {
            System.err.println("✗ Connection interrupted during establishment.");
            status = ConnectionStatus.INTERRUPTED;
            isInterrupted.set(true);
            return false;
        } catch (Exception e) {
            System.err.println("✗ Failed to connect to SMOS server: " + e.getMessage());
            status = ConnectionStatus.ERROR;
            return false;
        }
    }
    
    /**
     * Interrupts the connection to the SMOS server as per use case postcondition.
     * This method should be called after saving justification changes.
     * 
     * @return true if interruption was successful, false otherwise
     */
    public synchronized boolean interruptConnection() {
        if (!isConnected()) {
            System.out.println("No active SMOS connection to interrupt.");
            return true; // Nothing to interrupt
        }
        
        System.out.println("\n--- Interrupting SMOS Server Connection ---");
        System.out.println("Initiating connection interruption as per use case postcondition...");
        
        try {
            // Change status to INTERRUPTED
            status = ConnectionStatus.INTERRUPTED;
            isInterrupted.set(true);
            
            // Simulate connection teardown
            System.out.println("Sending interruption signal to server...");
            Thread.sleep(200);
            
            // Log the interruption
            LocalDateTime interruptTime = LocalDateTime.now();
            System.out.println("✓ SMOS server connection interrupted.");
            System.out.println("  Connection ID: " + connectionId);
            System.out.println("  Connection Duration: " + 
                             calculateDuration(connectionTime, interruptTime));
            System.out.println("  Interruption Time: " + interruptTime.format(TIMESTAMP_FORMATTER));
            
            // Clean up connection resources
            cleanupConnection();
            
            return true;
            
        } catch (InterruptedException e) {
            System.err.println("✗ Error during connection interruption: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.err.println("✗ Unexpected error during interruption: " + e.getMessage());
            status = ConnectionStatus.ERROR;
            return false;
        }
    }
    
    /**
     * Sends a heartbeat to keep the connection alive.
     * Updates last activity timestamp.
     * 
     * @return true if heartbeat successful, false otherwise
     */
    public synchronized boolean sendHeartbeat() {
        if (!isConnected()) {
            System.err.println("Cannot send heartbeat: Not connected to SMOS server.");
            return false;
        }
        
        if (isInterrupted.get()) {
            System.err.println("Cannot send heartbeat: Connection is interrupted.");
            return false;
        }
        
        try {
            lastActivity = LocalDateTime.now();
            // Simulate heartbeat transmission
            System.out.println("Heartbeat sent to SMOS server at " + 
                             lastActivity.format(TIMESTAMP_FORMATTER));
            return true;
        } catch (Exception e) {
            System.err.println("✗ Heartbeat failed: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Checks if the connection is still active and not timed out.
     * 
     * @return true if connection is valid and active, false otherwise
     */
    public synchronized boolean isConnectionValid() {
        if (!isConnected()) {
            return false;
        }
        
        if (isInterrupted.get()) {
            return false;
        }
        
        // Check for timeout
        if (lastActivity != null) {
            long secondsSinceLastActivity = java.time.Duration.between(
                lastActivity, LocalDateTime.now()).getSeconds();
            
            if (secondsSinceLastActivity > timeoutSeconds) {
                System.out.println("SMOS connection timed out (inactive for " + 
                                 secondsSinceLastActivity + " seconds).");
                status = ConnectionStatus.INTERRUPTED;
                isInterrupted.set(true);
                return false;
            }
        }
        
        return true;
    }
    
    /**
     * Checks if currently connected to SMOS server.
     * 
     * @return true if connected, false otherwise
     */
    public synchronized boolean isConnected() {
        return status == ConnectionStatus.CONNECTED || 
               status == ConnectionStatus.ACTIVE ||
               (status == ConnectionStatus.CONNECTING && !isInterrupted.get());
    }
    
    /**
     * Gets the current connection status.
     * 
     * @return current connection status
     */
    public synchronized ConnectionStatus getStatus() {
        return status;
    }
    
    /**
     * Gets the connection ID.
     * 
     * @return connection ID if connected, null otherwise
     */
    public synchronized String getConnectionId() {
        return connectionId;
    }
    
    /**
     * Gets the connection timestamp.
     * 
     * @return connection timestamp if connected, null otherwise
     */
    public synchronized LocalDateTime getConnectionTime() {
        return connectionTime;
    }
    
    /**
     * Gets the last activity timestamp.
     * 
     * @return last activity timestamp if connected, null otherwise
     */
    public synchronized LocalDateTime getLastActivity() {
        return lastActivity;
    }
    
    /**
     * Checks if the connection has been interrupted.
     * 
     * @return true if connection is interrupted, false otherwise
     */
    public synchronized boolean isInterrupted() {
        return isInterrupted.get();
    }
    
    /**
     * Sets the connection timeout in seconds.
     * 
     * @param timeoutSeconds Timeout value in seconds (must be positive)
     */
    public synchronized void setTimeoutSeconds(int timeoutSeconds) {
        if (timeoutSeconds <= 0) {
            throw new IllegalArgumentException("Timeout must be positive");
        }
        this.timeoutSeconds = timeoutSeconds;
    }
    
    /**
     * Disconnects from the SMOS server cleanly.
     * This is different from interruption - it's a planned disconnection.
     * 
     * @return true if disconnection successful, false otherwise
     */
    public synchronized boolean disconnect() {
        if (!isConnected() || isInterrupted.get()) {
            System.out.println("No active connection to disconnect from.");
            return true;
        }
        
        System.out.println("Disconnecting from SMOS server...");
        
        try {
            // Send disconnect notification to server
            System.out.println("Sending disconnect signal...");
            Thread.sleep(150);
            
            // Clean up resources
            cleanupConnection();
            
            status = ConnectionStatus.DISCONNECTED;
            System.out.println("✓ Disconnected from SMOS server.");
            
            return true;
        } catch (Exception e) {
            System.err.println("✗ Error during disconnection: " + e.getMessage());
            status = ConnectionStatus.ERROR;
            return false;
        }
    }
    
    /**
     * Displays current connection status information.
     */
    public synchronized void displayConnectionInfo() {
        System.out.println("\n--- SMOS Server Connection Information ---");
        System.out.println("Server Address: " + serverAddress);
        System.out.println("Server Port: " + serverPort);
        System.out.println("Connection Status: " + status);
        System.out.println("Interrupted: " + (isInterrupted.get() ? "Yes" : "No"));
        
        if (connectionId != null) {
            System.out.println("Connection ID: " + connectionId);
        }
        
        if (connectionTime != null) {
            System.out.println("Connection Time: " + connectionTime.format(TIMESTAMP_FORMATTER));
        }
        
        if (lastActivity != null) {
            System.out.println("Last Activity: " + lastActivity.format(TIMESTAMP_FORMATTER));
            
            // Calculate uptime if connected
            if (isConnected() && !isInterrupted.get() && connectionTime != null) {
                long uptimeSeconds = java.time.Duration.between(
                    connectionTime, LocalDateTime.now()).getSeconds();
                System.out.println("Uptime: " + formatDuration(uptimeSeconds));
            }
        }
        
        System.out.println("Timeout: " + timeoutSeconds + " seconds");
        System.out.println("Connection Valid: " + (isConnectionValid() ? "Yes" : "No"));
        System.out.println("------------------------------------------");
    }
    
    /**
     * Generates a unique connection ID.
     * 
     * @return generated connection ID
     */
    private String generateConnectionId() {
        long timestamp = System.currentTimeMillis();
        int random = (int)(Math.random() * 10000);
        return "SMOS-CONN-" + timestamp + "-" + random;
    }
    
    /**
     * Cleans up connection resources and resets state.
     */
    private synchronized void cleanupConnection() {
        // In a real implementation, this would close sockets, streams, etc.
        connectionId = null;
        connectionTime = null;
        lastActivity = null;
    }
    
    /**
     * Calculates duration between two timestamps.
     * 
     * @param start Start timestamp
     * @param end End timestamp
     * @return formatted duration string
     */
    private String calculateDuration(LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null) {
            return "N/A";
        }
        
        long seconds = java.time.Duration.between(start, end).getSeconds();
        return formatDuration(seconds);
    }
    
    /**
     * Formats duration in seconds to a human-readable string.
     * 
     * @param totalSeconds Total seconds
     * @return formatted duration
     */
    private String formatDuration(long totalSeconds) {
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;
        
        if (hours > 0) {
            return String.format("%d hours, %d minutes, %d seconds", hours, minutes, seconds);
        } else if (minutes > 0) {
            return String.format("%d minutes, %d seconds", minutes, seconds);
        } else {
            return String.format("%d seconds", seconds);
        }
    }
    
    /**
     * Reconnects to the SMOS server if connection was lost or interrupted.
     * 
     * @return true if reconnection successful, false otherwise
     */
    public synchronized boolean reconnect() {
        System.out.println("Attempting to reconnect to SMOS server...");
        
        // Clean up any existing connection state
        cleanupConnection();
        status = ConnectionStatus.DISCONNECTED;
        isInterrupted.set(false);
        
        // Attempt new connection
        return connect();
    }
    
    /**
     * Returns a string representation of the connection.
     */
    @Override
    public String toString() {
        return String.format("SMOSServerConnection{serverAddress='%s', serverPort=%d, status=%s, " +
                           "connectionId=%s, interrupted=%s}",
                           serverAddress, serverPort, status, 
                           connectionId, isInterrupted.get());
    }
}